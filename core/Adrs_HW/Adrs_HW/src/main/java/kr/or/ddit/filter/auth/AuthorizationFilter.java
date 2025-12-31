package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.login.MemberDTOWrapper;

public class AuthorizationFilter extends HttpFilter{
	private Map<String, List<String>> securedResources;
	private String securedInfoPath;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		super.init(config);
		securedInfoPath = Optional.ofNullable(config.getInitParameter("securedInfoPath"))
									.filter(sip->!sip.isBlank())
									.orElseThrow(() -> new RuntimeException("보호자원에 대한 설정이 필요함."));
//		Properties(RW) / ResourceBundle(read-only)
		ResourceBundle bundle = ResourceBundle.getBundle(securedInfoPath);
		securedResources = new LinkedHashMap<>();
		for(String url : bundle.keySet()) {
			List<String> roles = Arrays.asList(bundle.getString(url).split(","));
			securedResources.put(url, roles);
		}
		System.out.println("=========보호자원 정보==============");
		securedResources.forEach((k,v)->System.out.printf("%s : %s\n", k, v));
		System.out.println("=================================");
	}
	
	private boolean isSecuredRequest(HttpServletRequest req) {
		String requestURI = RequestURIProcessing.requestURIProcess(req);
		return securedResources.containsKey(requestURI);
	}
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		int status = 200;
		
		String requestURI = RequestURIProcessing.requestURIProcess(req);
		
		if(isSecuredRequest(req)) {
			// 보호자원 요청 : 
			MemberDTOWrapper principal = (MemberDTOWrapper) req.getUserPrincipal();
			boolean isAuthenticated = principal != null;
			if(isAuthenticated) {
				MemberDTO realUser = principal.getRealUser();
				String userRole = realUser.getMemRole();
				List<String> allowedRoles = securedResources.get(requestURI);
				boolean granted = allowedRoles.contains(userRole);
				if(granted) {
					// 3. 인증된 상태이고 권한이 부여된 허가된 사용자라면: 통과
					status = 200;
				}else {
					// 2. 인증된 상태이고 권한이 없는 사용자라면: 403 상태코드 전송
					status = 403;
				}
			}else {
				// 1. 로그인 전 : 401 상태코드 전송
				status = 401;
			}			
		}else {
			// 비보호자원 요청 : 통과
			status = 200;
		}
		
		
		if(status==401) {
//			res.sendError(401); // response 가 커밋되어버림.
			res.setStatus(401);
			
		}else if(status==403) {
			res.sendError(403);
		}else {
			chain.doFilter(req, res);
		}
	}

	
}






























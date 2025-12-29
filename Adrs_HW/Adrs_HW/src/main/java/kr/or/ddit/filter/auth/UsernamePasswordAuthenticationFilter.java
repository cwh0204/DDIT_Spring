package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.commons.exception.AuthenticateException;
import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.login.MemberDTOWrapper;
import kr.or.ddit.service.AuthenticateService;
import kr.or.ddit.service.AuthenticateServiceImpl;

public class UsernamePasswordAuthenticationFilter extends HttpFilter{
	public static final String ATTRNAME = "authMember";
	private AuthenticateService service = new AuthenticateServiceImpl();
	
	private String loginPage; // loginPage 라는 파라미터 전달시, 해당 파라미터로 설정하고, 생략된 경우, 기본값 /login
	private String loginProcessingUrl; // loginProcessingUrl 라는 파라미터 전달시, 해당 파라미터로 설정하고, 생략된 경우, 기본값 /login
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		super.init(config);
		loginPage = Optional.ofNullable(config.getInitParameter("loginPage"))
							.filter(lp->!lp.isBlank())
							.orElse("/login");
		loginProcessingUrl = Optional.ofNullable(config.getInitParameter("loginProcessingUrl"))
							.filter(lp->!lp.isBlank())
							.orElse("/login");
	}
	
	private boolean isLoginRequest(HttpServletRequest req) {
		// /login;jsessionid=세션아이디
		String requestURI = RequestURIProcessing.requestURIProcess(req);
		boolean isLoginReq = requestURI.equals(loginProcessingUrl)
						&& req.getMethod().equalsIgnoreCase("post");
		return isLoginReq;
	}
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		if(isLoginRequest(req)) {
			HttpSession session = req.getSession();
			if(session==null || session.isNew()) {
				res.sendError(400, "정상적이지 않은 로그인 요청");
				return;
			}
			
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			String location = null;
			// 요청 검증
			if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
				session.setAttribute("message", "아이디나 비밀번호 누락");
				location = loginPage;
			}else {
				try {
					MemberDTO realUser = service.authenticate(username, password);
					Principal principal = new MemberDTOWrapper(realUser);
					session.setAttribute(ATTRNAME, principal);
					location = "/";
				} catch (AuthenticateException e) {
					session.setAttribute("message", e.getMessage());
					location = loginPage;
					e.printStackTrace();
				} // try end
			} // if end
			
			res.sendRedirect(req.getContextPath() + location);
		}else {
			chain.doFilter(req, res);
			if(res.getStatus()==401 && !res.isCommitted()) {
				res.sendRedirect(req.getContextPath() + loginPage);
			}
		}
	}

	
}













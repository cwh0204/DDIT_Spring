package kr.or.ddit.filter.auth;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * 로그아웃을 처리할 필터
 * 
 */
public class LogoutFilter extends HttpFilter{
	private String logoutUrl; //logoutUrl 파라미터가 있으면 사용하고, 없으면 기본 주소로 /logout 을 사용함.
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		super.init(config);
		logoutUrl = config.getInitParameter("logoutUrl");
		if(logoutUrl==null || logoutUrl.isBlank()) {
			logoutUrl = "/logout";
		}
	}
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// 로그아웃 요청 여부 판단
		String requestURI = RequestURIProcessing.requestURIProcess(req);
		boolean isLogoutRequest = logoutUrl.equals(requestURI);
		if(isLogoutRequest) {
			HttpSession session = req.getSession();
			session.invalidate(); 
			res.sendRedirect(req.getContextPath()+"/");
		}else {
			chain.doFilter(req, res);
		}
	}
}

















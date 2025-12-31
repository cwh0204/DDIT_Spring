package kr.or.ddit.filter.auth;

import jakarta.servlet.http.HttpServletRequest;

public class RequestURIProcessing {
	public static String requestURIProcess(HttpServletRequest req) {
		String requestURI = req.getRequestURI().replace(req.getContextPath(), "").split(";")[0];
		return requestURI;
	}
}

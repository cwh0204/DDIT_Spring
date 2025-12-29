package kr.or.ddit.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StaticResourceFilter extends HttpFilter{
	@Override
	public void init(FilterConfig config) throws ServletException {
		super.init(config);
		System.out.printf("%s 필터 초기화\n", this.getClass().getSimpleName());
	}
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// 요청 필터링
//		res.setHeader("Cache-Control", "public, max-age=3000");
//		res.setHeader("Pragma", "public, max-age=3000");
		res.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		res.setHeader("Pragma", "no-cache, no-store, must-revalidate");
		res.setDateHeader("Expires", 0);
		System.out.println("---------------static resource filter-------------------");
		super.doFilter(req, res, chain); // chain.doFilter 내부 실행 : 필터 체인 내의 다음 필터나 최종 자원쪽으로 제어를 이동시킴.
		System.out.println("===============static resource filter===================");
		// 응답 필터링
	}
}


















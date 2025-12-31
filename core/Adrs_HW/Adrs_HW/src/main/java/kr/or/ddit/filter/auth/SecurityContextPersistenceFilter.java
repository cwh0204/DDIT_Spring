package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.login.MemberDTOWrapper;

/**
 *  User Principal(MemberDTOWrapper) 를 request 단위로 접근할 수 있도록 인터페이스를 정의한
 *  PrincipalRequestWrapper 를 생성하고, 원본 request 를 대체하고 있는 Filter.
 */
public class SecurityContextPersistenceFilter extends HttpFilter{
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		MemberDTOWrapper principal = Optional.ofNullable(req.getSession(false))
									.map(session-> 
										(MemberDTOWrapper) session.getAttribute(UsernamePasswordAuthenticationFilter.ATTRNAME))
									.orElse(null);
		
		if(principal!=null) {
			PrincipalRequestWrapper wrapper = new PrincipalRequestWrapper(req, principal);
			chain.doFilter(wrapper, res);
		}else {
			chain.doFilter(req, res);
		}
	}
}












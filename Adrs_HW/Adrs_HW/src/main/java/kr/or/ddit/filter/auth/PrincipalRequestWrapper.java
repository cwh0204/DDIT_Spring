package kr.or.ddit.filter.auth;

import java.security.Principal;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import kr.or.ddit.login.MemberDTOWrapper;

public class PrincipalRequestWrapper extends HttpServletRequestWrapper {
	
	private final MemberDTOWrapper principal;

	public PrincipalRequestWrapper(HttpServletRequest request, MemberDTOWrapper principal) {
		super(request);
		this.principal = principal;
	}

	@Override
	public Principal getUserPrincipal() {
		return this.principal;
	}
	
	@Override
	public boolean isUserInRole(String role) {
		return Optional.ofNullable(this.principal)
					.map(MemberDTOWrapper::getRealUser)
					.filter(ru->ru.getMemRole().contains(role))
					.isPresent();
	}
}
















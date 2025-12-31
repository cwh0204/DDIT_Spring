package kr.or.ddit.commons.exception;

public class BadCredentialException extends AuthenticateException{

	public BadCredentialException() {
		this("비밀번호 오류");
	}

	public BadCredentialException(String message) {
		super(message);
	}
	
}

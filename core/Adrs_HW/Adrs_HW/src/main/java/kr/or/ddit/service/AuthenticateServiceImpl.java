package kr.or.ddit.service;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.or.ddit.commons.exception.AuthenticateException;
import kr.or.ddit.commons.exception.BadCredentialException;
import kr.or.ddit.commons.exception.UserNotFoundException;
import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.mapper.MemberMapper;
import kr.or.ddit.mybatis.MapperProxyFactory;

public class AuthenticateServiceImpl implements AuthenticateService {

	private MemberMapper mapper = MapperProxyFactory.generateProxy(MemberMapper.class);
	private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	
	@Override
	public MemberDTO authenticate(String username, String password) throws AuthenticateException{
		
		MemberDTO saved = mapper.selectMember(username);
		
		if(saved!=null) {
			String savedPass = saved.getMemPass();
			
			if(passwordEncoder.matches(password, savedPass)) {
				return saved;
			}else {
				// 비밀번호 오류.
				throw new BadCredentialException("비밀번호 오류");
			}
		}else {
			throw new UserNotFoundException(username);
		}
	}
}

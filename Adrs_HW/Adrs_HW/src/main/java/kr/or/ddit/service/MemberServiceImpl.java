package kr.or.ddit.service;

import java.util.List;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.exception.AuthenticateException;
import kr.or.ddit.commons.exception.DataReferencedException;
import kr.or.ddit.commons.exception.PkNotFoundException;
import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.mapper.MemberMapper;
import kr.or.ddit.mybatis.MapperProxyFactory;

public class MemberServiceImpl implements MemberService {
	
	private MemberMapper mapper = MapperProxyFactory.generateProxy(MemberMapper.class);
	private AuthenticateService authenticateService = new AuthenticateServiceImpl();
	private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	
	
	@Override
	public ServiceResult createMember(MemberDTO member) {
		
		if(isExist(member.getMemId())) {
			return ServiceResult.PKDUPLICATED;
		}else {
			String encoded = passwordEncoder.encode(member.getMemPass());
			member.setMemPass(encoded);
			return mapper.insertMember(member) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}
	}

	@Override
	public MemberDTO readMember(String memId) throws PkNotFoundException{
		MemberDTO member = mapper.selectMember(memId);
		if(member == null) {
			throw new PkNotFoundException("%s 사용자가 없음".formatted(memId));
		}
		return member;
	}

	@Override
	public List<MemberDTO> readMemberList() {
		List<MemberDTO> memberList = mapper.selectMemberList();
		return memberList;
	}

	@Override
	public boolean modifyMember(MemberDTO member) throws AuthenticateException {
		
		authenticateService.authenticate(member.getMemId(), member.getMemPass());
		
		return mapper.updateMember(member) > 0;
	}

	@Override
	public boolean removeMember(MemberDTO member) throws AuthenticateException {
		
		authenticateService.authenticate(member.getMemId(), member.getMemPass());
		
		return mapper.deleteMember(member.getMemId()) > 0;
	}

	@Override
	public boolean changePassword(String username, String currentPassword, String newPassword)
			throws AuthenticateException {

		authenticateService.authenticate(username, currentPassword);
		//필요한 모든 요청 데이터에 대한 검증과, 2차 인증에 모두 성공한 이후에
		//변경할 새 비밀번호를 암호화 시킴.
		
		String encodedNewPassword = passwordEncoder.encode(newPassword);
		
		return mapper.updatePassword(MemberDTO.builder()
						.memId(username)
						.memPass(encodedNewPassword)
						.build()) > 0;
	}

	@Override
	public boolean isExist(String memId) {
		return mapper.selectMember(memId) != null;
	}

}

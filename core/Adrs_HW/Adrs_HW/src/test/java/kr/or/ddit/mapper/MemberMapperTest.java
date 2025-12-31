package kr.or.ddit.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.mybatis.MapperProxyFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class MemberMapperTest {
	
	MemberMapper mapper = MapperProxyFactory.generateProxy(MemberMapper.class);
	
	@Test
	void insertMember() {
		MemberDTO member = new MemberDTO();
		member.setMemId("a002");
		member.setMemPass("java");
		member.setMemName("테스트Name");
		member.setMemHp("010-0000-0000");
		member.setMemMail("test@naver.com");
		member.setMemAdd1("청주시");
		int result = mapper.insertMember(member);
		assertEquals(1, result);
	}
	
	@Test
	void selectMember() {
		MemberDTO member = mapper.selectMember("a001");
	}
	
	@Test
	void selectMemberList() {
		List<MemberDTO> memberList = mapper.selectMemberList();
	}
	
	@Test
	void updateMember() {
		MemberDTO member = new MemberDTO();
		member.setMemId("a001");
		member.setMemPass("java");
		member.setMemName("수정테스트Name");
		member.setMemHp("010-1111-1111");
		member.setMemMail("수정test@naver.com");
		member.setMemAdd1("청주시 흥덕구");
		int result = mapper.updateMember(member);
		assertEquals(1, result);
	}
	
}

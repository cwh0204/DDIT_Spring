package kr.or.ddit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.dto.MemberDTO;

/**
 * 회원관리(CRUD)와 인증시스템에서 사용할 Persistence Layer
 */
public interface MemberMapper {
	
	/**
	 * 한사람의 사용자 정보 조회(인증 처리용)
	 * @param username 식별자
	 * @return 조회된 사용자 정보를 가진 DTO, 존재하지 않는 경우, null 반환
	 */
	MemberDTO selectMemberForAuth(String username);
	
	/**
	 * 한사람의 사용자 정보 조회
	 * @param username 식별자
	 * @return 조회된 사용자 정보를 가진 DTO, 존재하지 않는 경우, null 반환
	 */
	MemberDTO selectMember(String username);
	
	/**
	 * 비밀번호 수정
	 * @param member 수정 대상의 id와 수정할 새 비밀번호 
	 * @return 
	 */
	int updatePassword(MemberDTO member);
	
	/**
	 * 회원 목록 조회
	 * @return 조회 대상이 없으면, empty list 반환
	 */
	List<MemberDTO> selectMemberList();
	
	/**
	 * 회원 정보 등록
	 * @param member
	 * @return 등록된 rowcount
	 */
	int insertMember(MemberDTO member);
	/**
	 * 회원 정보 수정
	 * @param member
	 * @return 수정된 rowcount
	 */
	int updateMember(MemberDTO member);
	/**
	 * 회원 정보 삭제
	 * @param username
	 * @return 삭제된 rowcount
	 */
	int deleteMember(@Param("memId") String username);
}

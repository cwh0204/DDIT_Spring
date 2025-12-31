package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.exception.AuthenticateException;
import kr.or.ddit.commons.exception.DataReferencedException;
import kr.or.ddit.commons.exception.PkNotFoundException;
import kr.or.ddit.dto.MemberDTO;

/**
 * 회원 관리(CRUD)용 business logic layer
 */
public interface MemberService {

	/**
	 * 회원 가입 처리
	 * 
	 * @param member
	 * @return PKDULICATED, OK, FAIL(에외로 던져지는게 일반적임)
	 */
	ServiceResult createMember(MemberDTO member);

	/**
	 * 회원 상세 조회 ex) 마이 페이지
	 * 
	 * @param memId
	 * @return
	 * @throws PkNotFoundException
	 */
	MemberDTO readMember(String memId);

	/**
	 * 회원 목록 조회, 추후 검색과 페이징 지원 예정
	 * 
	 * @return
	 */
	List<MemberDTO> readMemberList();

	/**
	 * 회원 정보 수정
	 * 
	 * @param member
	 * @return
	 * @throws AuthenticateException 2차 인증 실패
	 */
	boolean modifyMember(MemberDTO member) throws AuthenticateException;

	/**
	 * @param member 탈퇴한 회원의 username/password
	 * @return
	 * @throws AuthenticateException 2차 인증 실패
	 */
	boolean removeMember(MemberDTO member) throws AuthenticateException;

	/**
	 * 비밀번호 변경
	 * @param username
	 * @param currentPassword 인증을 위한 기존 비밀번호
	 * @param newPassword 변경할 새 비밀번호
	 * @return
	 * @throws AuthenticateException 인증 실패
	 */
	boolean changePassword(String username, String currentPassword, String newPassword) throws AuthenticateException;
	/**
	 * 회원 아이디 사용 여부 확인
	 * @param memId
	 * @return
	 */
	boolean isExist(String memId);

}

package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.dto.AdrsDTO;

public interface AdrsMapper {
	
	/**
	 * 주소 추가 처리
	 * 
	 * @param adrs
	 * @return 추가된 rowcount
	 */
	int insertAdrs(AdrsDTO adrs);
	
	/**
	 * 주소 상세 조회
	 * 
	 * @param memId
	 * @return 조회된 사용자 정보를 가진 DTO, 존재하지 않는 경우, null 반환
	 */
	AdrsDTO selectAdrs(AdrsDTO adrs);
	
	/**
	 * 주소 목록 조회
	 * 
	 * @return 조회된 사용자 정보를 가진 List, 존재하지 않는 경우, null 반환
	 */
	List<AdrsDTO> selectAdrsList(String memId);
	
	/**
	 * 주소 정보 수정
	 * 
	 * @param adrs
	 * @return 수정된 rowcount
	 */
	int updateAdrs(AdrsDTO adrs);
	
	/**
	 * 주소 삭제
	 * 
	 * @param i
	 * @return 삭제된 rowcount
	 */
	int deleteAdrs(int addrsNo);
}

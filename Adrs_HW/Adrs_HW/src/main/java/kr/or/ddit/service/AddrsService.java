package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.dto.AdrsDTO;

public interface AddrsService {
	
	/**
	 * 주소 추가 처리
	 * 
	 * @param adrs
	 * @return
	 */
	int createAdrs(AdrsDTO adrs);
	
	/**
	 * 주소 상세 조회
	 * 
	 * @param memId
	 * @return
	 */
	AdrsDTO readAdrs(AdrsDTO adrs);
	
	/**
	 * 주소 목록 조회
	 * 
	 * @return
	 */
	List<AdrsDTO> readAdrsList(String memId);
	
	/**
	 * 주소 정보 수정
	 * 
	 * @param adrs
	 * @return
	 */
	int modifyAdrs(AdrsDTO adrs);
	
	/**
	 * 주소 삭제
	 * 
	 * @param memId
	 * @return
	 */
	int removeAdrs(int addrsNo);
}

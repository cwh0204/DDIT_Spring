package kr.or.ddit.mbti.service;

import java.util.List;

import kr.or.ddit.mbti.dto.MbtiDTO;

public interface MbtiService {
	
	MbtiDTO selectOne(String mbti);
	List<MbtiDTO> selectList();
	int insert(MbtiDTO dto);
	int update(MbtiDTO dto);
	int delete(String mtType);
	
}

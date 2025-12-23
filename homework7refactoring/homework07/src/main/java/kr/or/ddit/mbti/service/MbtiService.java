package kr.or.ddit.mbti.service;

import java.util.List;

import kr.or.ddit.exception.DuplicatePkException;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.mbti.dto.MbtiDTO;

public interface MbtiService {
	
	MbtiDTO selectOne(String mbti);
	List<MbtiDTO> selectList();
	int insert(MbtiDTO dto) throws DuplicatePkException;
	int update(MbtiDTO dto) throws PkNotFoundException;
	int delete(String mtType) throws PkNotFoundException;
	
}

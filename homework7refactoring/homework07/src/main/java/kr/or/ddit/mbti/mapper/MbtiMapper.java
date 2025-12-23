package kr.or.ddit.mbti.mapper;

import java.util.List;

import kr.or.ddit.mbti.dto.MbtiDTO;

public interface MbtiMapper {
	
	MbtiDTO selectOne(String mbti);
	List<MbtiDTO> selectList();
	//성공 여부를 받아오기 위해 리턴을 int로 설정해둠
	int insert(MbtiDTO dto);
	int update(MbtiDTO dto);
	int delete(String mtType);
}

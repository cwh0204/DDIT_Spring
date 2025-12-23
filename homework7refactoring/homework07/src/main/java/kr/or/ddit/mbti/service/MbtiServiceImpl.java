package kr.or.ddit.mbti.service;

import java.util.List;

import kr.or.ddit.exception.DuplicatePkException;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.mbti.config.MbtiServiceConfig;
import kr.or.ddit.mbti.dto.MbtiDTO;
import kr.or.ddit.mbti.mapper.MbtiMapper;

public class MbtiServiceImpl implements MbtiService {
	
	// 여기서도 책임분리를 위헤 confing로 의존성을 주입해줌
	MbtiServiceConfig config = new MbtiServiceConfig();
	private MbtiMapper mapper = config.mbtiMapper();

	@Override
	public MbtiDTO selectOne(String mtType) {
		return mapper.selectOne(mtType);
	}

	@Override
	public List<MbtiDTO> selectList() {
		return mapper.selectList();
	}

	@Override
	public int insert(MbtiDTO mbti) {
		
		try {
			int result = mapper.insert(mbti);
			return result;
		}catch (Exception e) {
			throw new DuplicatePkException(e);
		}
	}

	@Override
	public int update(MbtiDTO mbti) {
		
		try {
			int result = mapper.update(mbti);
			return result;
		}catch (Exception e) {
			throw new PkNotFoundException(e);
		}
	}

	@Override
	public int delete(String mtType) {
		
		try {
			int result = mapper.delete(mtType);
			return result;
		}catch (Exception e) {
			throw new PkNotFoundException(e);
		}
	}

}

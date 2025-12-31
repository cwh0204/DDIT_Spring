package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.dto.AdrsDTO;
import kr.or.ddit.mapper.AdrsMapper;
import kr.or.ddit.mybatis.MapperProxyFactory;

/**
 * 딱히 예외가 나올 상황이 안보임
 * pk가 중복될일 없고 pk를 제외하면 null도 허용함
 */
public class AddrsServiceImpl implements AddrsService {
	
	AdrsMapper mapper = MapperProxyFactory.generateProxy(AdrsMapper.class);
	
	@Override
	public int createAdrs(AdrsDTO adrs) {
		return mapper.insertAdrs(adrs);
	}

	@Override
	public AdrsDTO readAdrs(AdrsDTO adrs) {
		return mapper.selectAdrs(adrs);
	}

	@Override
	public List<AdrsDTO> readAdrsList(String memId) {
		return mapper.selectAdrsList(memId);
	}

	@Override
	public int modifyAdrs(AdrsDTO adrs) {
		return mapper.updateAdrs(adrs);
	}

	@Override
	public int removeAdrs(int addrsNo) {
		return mapper.deleteAdrs(addrsNo);
	}

}

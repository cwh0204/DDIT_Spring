package kr.or.ddit.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.dto.AdrsDTO;
import kr.or.ddit.mybatis.MapperProxyFactory;

class AdrsMapperTest {
	
	AdrsMapper mapper = MapperProxyFactory.generateProxy(AdrsMapper.class);
	
	@Test
	void insertAdrs() {
		
		AdrsDTO adrs = new AdrsDTO();
		adrs.setMemId("a002");
		adrs.setAdrsName("테스트name");
		adrs.setAdrsTel("010-0000-0000");
		adrs.setAdrsAdd("청주시");
		adrs.setAdrsMail("test@nate.com");
		int result = mapper.insertAdrs(adrs);
		assertEquals(1, result);
	}
	
	@Test
	void selectAdrs() {
		AdrsDTO adrs = new AdrsDTO();
		adrs.setMemId("a002");
		adrs.setAdrsName("테스트");
		AdrsDTO result = mapper.selectAdrs(adrs);
	}
	
	@Test
	void selectAdrsList() {
		List<AdrsDTO> result = mapper.selectAdrsList("a002");
	}
	
	@Test
	void updateAdrs() {
		AdrsDTO adrs = new AdrsDTO();
		adrs.setAdrsNo(3);
		adrs.setAdrsName("수정name");
		adrs.setAdrsTel("010-1111-1111");
		adrs.setAdrsAdd("청주시 흥덕구");
		adrs.setAdrsMail("update@nate.com");
		int result = mapper.updateAdrs(adrs);
		assertEquals(1, result);
	}
	
	@Test
	void deleteAdrs() {
		int result = mapper.deleteAdrs(3);
		assertEquals(1, result);
	}

}

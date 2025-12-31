package kr.or.ddit.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class AdrsDTOTest {
	
	@Test
	void test() {
		AdrsDTO target = AdrsDTO.builder().build();
		Map<String,String> errors =  ValidateUtils.validate(target, UpdateGroup.class); //2
		
		boolean valid = errors.isEmpty();
		log.info("검증 통과 여부 : {}",valid);
		log.info("검증 실패한 property 개수 : {}",errors.size());
	}

}

package kr.or.ddit.mbti.config;

import kr.or.ddit.mbti.mapper.MbtiMapper;
import kr.or.ddit.mbti.mybatis.MybatisTemplate;

public class MbtiServiceConfig {
	
	public MbtiMapper mbtiMapper() {
		return MybatisTemplate.generateProxy(MbtiMapper.class);
	}
}

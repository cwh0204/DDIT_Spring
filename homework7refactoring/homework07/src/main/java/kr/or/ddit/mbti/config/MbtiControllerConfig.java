package kr.or.ddit.mbti.config;

import kr.or.ddit.mbti.service.MbtiService;
import kr.or.ddit.mbti.service.MbtiServiceImpl;
import kr.or.ddit.util.MbtiControllerUtil;

public class MbtiControllerConfig {
		
	public MbtiService mbtiService() {
		return new MbtiServiceImpl();
	}
	public MbtiControllerUtil mbtiControllerUtil(){
		return new MbtiControllerUtil();
		
	}
}

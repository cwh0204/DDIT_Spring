package kr.or.ddit.filter;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *  Filter
 *  : 컨테이너에 의해 관리되는 웹 객체의 형태.
 *  : 웹상의 요청에 대한 일정한 전처리나 응답에 대한 후처리를 지원하는 형태로 사용됨.
 *  주의! 필터를 이용해 어플리케이션의 핵심 기능을 처리하진 않는다!
 *  
 *  1. Filter 인터페이스의 구현체나 HttpFilter 의 자식의 형태로 구현함.
 *  2. 필요한 경우, lifecycle callback, request callback 을 재정의함.
 *  	lifecycle callback : init, destroy
 *  	request callback : doFilter (실제 필터링 처리)
 *  		주의! chain.doFilter 를 호출하지 않으면, 필터에서 제어가 멈춤.
 *  3. filter 등록 및 filter-mapping 설정 (web.xml)
 *  	-> 컨테이너에 의해 FilterChain 으로 관리됨.
 *  	-> FilteChain 내의 필터들의 동작 순서는 web.xml 에 등록 순서를 따라감.
 *  
 *  요청 전처리 :
 *  	일정 조건을 만족하는 클라이언트에게만 서비스해야 하는 보호자원에 대해 접근 제어가 필요할때.
 *  	상태를 변경할 수 없는 request 에 대해 상태 변경이 필요한 경우, request wrapping 구조를 통해 처리 가능함. 
 *  응답 후처리 : 
 *  	네트워크 전송 부하를 줄이려면, 일괄적으로 일정 크기 이상의 응답에 대해 압축처리가 가능함.
 *      컨텐츠에 데코레이션을 통해 공통 레이아웃을 만들어내는 경우에도 활용됨 --> sitemesh 
 * 
 */
public class CharacterEncodingFilter extends HttpFilter{
	private String encoding;
	private Boolean forced;

	@Override
	public void init(FilterConfig config) throws ServletException {
		super.init(config);
		System.out.printf("%s 필터 초기화\n", this.getClass().getSimpleName());
		encoding = Optional.ofNullable(config.getInitParameter("encoding"))
				.filter(e->!e.isBlank())
				.orElse("UTF-8");
		forced = Optional.ofNullable(config.getInitParameter("forced"))
				.filter(f->!f.isBlank())
				.map(Boolean::parseBoolean)
				.orElse(false);
	}
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// 요청에 대한 전처리
		req.setCharacterEncoding(encoding);
		
		if(forced)
			res.setCharacterEncoding(encoding);
		
		super.doFilter(req, res, chain);
		// 응답에 대한 후처리
	}
}
















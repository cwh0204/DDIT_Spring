package kr.or.ddit.util;

import java.io.IOException;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.mbti.dto.MbtiDTO;

public class MbtiControllerUtil {
	
	private final Gson gson = new Gson();
	
	// 1. 공통 응답 처리 (JSON 출력)
	public void sendJsonResponse(HttpServletResponse resp, Object data) throws IOException {
		resp.setContentType("application/json; charset=UTF-8");
		resp.getWriter().print(gson.toJson(data));
	}

	// 2. URI에서 mbtiType 추출 (공통)
	public String getMbtiType(HttpServletRequest req) {
		String pathInfo = req.getPathInfo();
		if (pathInfo == null || pathInfo.equals("/"))
			return null;
		return pathInfo.substring(1).toLowerCase();
	}

	// 3. 파라미터를 DTO로 변환 (공통)
	public MbtiDTO bindDto(HttpServletRequest req) throws Exception {
		MbtiDTO dto = new MbtiDTO();
		BeanUtils.populate(dto, req.getParameterMap());
		return dto;
	}
	
}

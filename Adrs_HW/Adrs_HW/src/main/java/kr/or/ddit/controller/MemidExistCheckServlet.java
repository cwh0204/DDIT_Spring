package kr.or.ddit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.jsoup.internal.StringUtil;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.service.MemberServiceImpl;


@WebServlet("/member/id-check")
public class MemidExistCheckServlet extends HttpServlet{
	
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		1. 입력 아이디 확보
		String targetId = req.getParameter("targetId");
		
		if(StringUtil.isBlank(targetId)) {
			resp.sendError(400);
			return;
		}
		
//		2. 중복 여부 확인 : isExist
		boolean duplicated = service.isExist(targetId);
		Map<String, ?> nativeTarget = Map.of("duplicated", duplicated);
//		Map<String, Boolean> result = new HashMap<>();
//		result.put("duplicated", duplicated);
		resp.setContentType("application/json");		
//		3. 중복여부를 응답 JSON 컨텐츠로 전송 : {duplicated :true|false}
		try(PrintWriter out = resp.getWriter()) {
			new Gson().toJson(nativeTarget,out);		
		}
	}
}

package kr.or.ddit.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServlet;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.service.MemberServiceImpl;


//@WebServlet("/member/id-check")
@Controller
@RequestMapping("/member/id-check")
public class MemidExistCheckServlet {
	
	private MemberService service = new MemberServiceImpl();
	
	@GetMapping(produces = "application/json")
	@ResponseBody
	public Map<String, ?> doGet(@RequestParam(required = true) String targetId) {
		boolean duplicated = service.isExist(targetId);
		Map<String, ?> nativeTarget = Map.of("duplicated", duplicated);
		return nativeTarget;
	}
}

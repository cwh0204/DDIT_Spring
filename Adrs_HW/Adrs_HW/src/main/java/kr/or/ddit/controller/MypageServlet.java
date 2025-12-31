package kr.or.ddit.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServlet;
import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.service.MemberServiceImpl;

//@WebServlet("/mypage")
@Controller
public class MypageServlet {
	
	private MemberService service = new MemberServiceImpl();
	
	@GetMapping("/mypage")
	public String myPage(Principal principal, Model model){
		String username = principal.getName();
//		2. 마이페이지에서 출력할 모든 정보를 다시 조회.
		MemberDTO member = service.readMember(username);
		model.addAttribute("member", member);		
		return "/member/mypage";
	}
}

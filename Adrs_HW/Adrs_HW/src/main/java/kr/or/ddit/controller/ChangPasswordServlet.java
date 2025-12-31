package kr.or.ddit.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.commons.exception.AuthenticateException;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.service.MemberServiceImpl;

//@WebServlet("/change-password")
@Controller
@RequestMapping("/change-password")
public class ChangPasswordServlet{

	private MemberService service = new MemberServiceImpl();

	@GetMapping
	public String doGet(){
		// 비밀번호 변경 form UI 제공
		
		return "/member/passwordForm";
	}

	@PostMapping
	public String doPost(
			@RequestParam(required = true) String currentPassword,
			@RequestParam(required = true, name = "newPassword") String newPassword,
			@RequestParam(required = true, name = "retypePassword") String retypePassword,
			Principal principal,
			HttpSession session
	){
		// form data 처리 (currentPassword, newPassword, retypePassword), username 은 인증객체에서 꺼냄.
//		1. form data 확보
//		2. 인증 객체 확보
		String username = principal.getName();
		String logicalViewName = null;
		String message = null;
		
//		5. newPassword 와 retypePassword 일치 여부 확인
		if(newPassword.equals(retypePassword)) {
//			7. 로직을 통해 비번 변경
			try {
				service.changePassword(username, currentPassword, newPassword);
//			9. 비번 변경 완료 , 로그아웃 처리 (/logout redirect)
				logicalViewName = "redirect:/logout";
			} catch (AuthenticateException e) {
//			8. 비번 변경시 인증 실패로 인한 예외 발생 : form UI 로 이동(redirect), 비밀번호 오류로 인한 인증 실패(session Scope, flash attribute)
				logicalViewName = "redirect:/change-password";
				message = e.getMessage();
			}
			
		}else {
//		 6. 일치하지 않음. : form UI 로 이동(redirect), 재입력 비밀번호 확인(session Scope, flash attribute)
			logicalViewName = "redirect:/change-password";
			message = "변경할 비밀번호에 대한 재입력 오류";
		}
		
		session.setAttribute("message", message);
		
		return logicalViewName;
	}
}

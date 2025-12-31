package kr.or.ddit.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServlet;

//@WebServlet("/login")
@Controller
public class LoginFormServlet extends HttpServlet{
	
	@GetMapping("/login")
	public String login() {
		return "/login/loginForm";
	}
}

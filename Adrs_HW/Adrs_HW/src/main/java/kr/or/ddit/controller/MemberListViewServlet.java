package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServlet;


@Controller
@RequestMapping("/memberlist")
public class MemberListViewServlet{
	
	@GetMapping
	public String doGet() {
		return "/adrs/adrsForm";
	}
}

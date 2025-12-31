package kr.or.ddit.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/adrslist")
@Controller
public class AdrsListViewServlet{
	
	@GetMapping("/adrslist")
	public String doGet() {
		return "/adrs/myadrs";
	}
}

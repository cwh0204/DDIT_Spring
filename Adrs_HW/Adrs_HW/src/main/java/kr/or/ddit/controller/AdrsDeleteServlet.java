package kr.or.ddit.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.service.AddrsService;
import kr.or.ddit.service.AddrsServiceImpl;


@Controller
public class AdrsDeleteServlet{
	
	AddrsService service = new AddrsServiceImpl();
	
	@PostMapping("/adrs/delete")
	@ResponseBody
	public String doPost(@RequestParam(required = true) String adrsNo ) {
		service.removeAdrs(Integer.parseInt(adrsNo));
		return "성공";
	}
}

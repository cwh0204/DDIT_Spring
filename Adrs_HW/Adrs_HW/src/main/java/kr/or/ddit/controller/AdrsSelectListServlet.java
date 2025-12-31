package kr.or.ddit.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.dto.AdrsDTO;
import kr.or.ddit.service.AddrsService;
import kr.or.ddit.service.AddrsServiceImpl;

@Controller
@RequestMapping("/adrs/list")
public class AdrsSelectListServlet{
	AddrsService service = new AddrsServiceImpl();
	
	@GetMapping(produces = "application/json")
	@ResponseBody
	public List<AdrsDTO> doGet(Principal principal) {
		
		String username = principal.getName();
		List<AdrsDTO> adrsList = service.readAdrsList(username);
		return adrsList;
		
	}
}

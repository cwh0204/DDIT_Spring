package kr.or.ddit.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.dto.AdrsDTO;
import kr.or.ddit.service.AddrsService;
import kr.or.ddit.service.AddrsServiceImpl;
import kr.or.ddit.util.PopulateUtils;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;

@Controller
@RequestMapping("/adrs/insert")
public class AdrsInsertServlet {
	
	AddrsService service = new AddrsServiceImpl();
	
	@PostMapping
	@ResponseBody
	public String doPost(@ModelAttribute AdrsDTO adrs ,Principal principal) {
		
		
		String username = principal.getName();
		adrs.setMemId(username);
		Map<String,String> errors =  ValidateUtils.validate(adrs, InsertGroup.class); //2
		System.out.println(errors);
		System.out.println(adrs);
		boolean valid = errors.isEmpty();
		if(!valid) {
			return errors.toString();
		}else {
			service.createAdrs(adrs);
			return "추가 완료";
		}
		
	}
}

package kr.or.ddit.controller;

import java.io.IOException;
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
@RequestMapping("/adrs/update")
public class AdrsUpdateServlet {
	
	AddrsService service = new AddrsServiceImpl();
	
	@PostMapping
	@ResponseBody
	public String doPost(@ModelAttribute AdrsDTO adrs) {
		
		Map<String,String> errors =  ValidateUtils.validate(adrs, UpdateGroup.class); //2
		boolean valid = errors.isEmpty();
		if(!valid) {
			return errors.toString();
		}else {
			service.modifyAdrs(adrs);
			return "수정완료";
		}
	}
}

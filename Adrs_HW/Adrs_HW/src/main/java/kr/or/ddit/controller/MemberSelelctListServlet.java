package kr.or.ddit.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.servlet.http.HttpServlet;
import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.service.MemberServiceImpl;

@Controller
@RequestMapping("/member/list")
public class MemberSelelctListServlet {

	MemberService service = new MemberServiceImpl();
	
	@GetMapping(produces = "application/json")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@ResponseBody
	public List<MemberDTO> doGet(){
		System.out.println("들어옴");
		List<MemberDTO> memberList = service.readMemberList();
		return memberList;
	}
}

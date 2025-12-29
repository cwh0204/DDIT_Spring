package kr.or.ddit.controller;

import java.io.IOException;
import java.security.Principal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.dto.AdrsDTO;
import kr.or.ddit.service.AddrsService;
import kr.or.ddit.service.AddrsServiceImpl;

@WebServlet("/adrs/insert")
public class AdrsInsertServlet extends HttpServlet{
	
	AddrsService service = new AddrsServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String adrsName = req.getParameter("memName");
		String adrsTel = req.getParameter("memHp");
		String adrsAdd = req.getParameter("memAdd1");
		String adrsMail = req.getParameter("memMail");
		Principal principal = req.getUserPrincipal();
		String username = principal.getName();
		
		AdrsDTO adrs = new AdrsDTO();
		
		adrs.setMemId(username);
		adrs.setAdrsName(adrsName);
		adrs.setAdrsTel(adrsTel);
		adrs.setAdrsAdd(adrsAdd);
		adrs.setAdrsMail(adrsMail);
		
		service.createAdrs(adrs);
		
	}
}

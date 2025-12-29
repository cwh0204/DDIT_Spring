package kr.or.ddit.controller;

import java.io.IOException;
import java.security.Principal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.service.MemberServiceImpl;

@WebServlet("/mypage")
public class MypageServlet extends HttpServlet{
	
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		1. 인증 객체 확보
		Principal principal = req.getUserPrincipal();
		String username = principal.getName();
//		2. 마이페이지에서 출력할 모든 정보를 다시 조회.
		MemberDTO member = service.readMember(username);
		req.setAttribute("member", member);
//		3. 뷰 선택
		String view = "/WEB-INF/views/member/mypage.jsp";
//		4. 뷰 이동
		req.getRequestDispatcher(view).forward(req, resp);
	}
}

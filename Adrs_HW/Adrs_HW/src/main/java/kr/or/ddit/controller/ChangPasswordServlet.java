package kr.or.ddit.controller;

import java.io.IOException;
import java.security.Principal;

import org.jsoup.internal.StringUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.commons.exception.AuthenticateException;
import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.service.MemberServiceImpl;
import oracle.jdbc.driver.Message;

@WebServlet("/change-password")
public class ChangPasswordServlet extends HttpServlet {

	private MemberService service = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 비밀번호 변경 form UI 제공
		
		String view = "/WEB-INF/views/member/passwordForm.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// form data 처리 (currentPassword, newPassword, retypePassword), username 은
		// 인증객체에서 꺼냄.
//		1. form data 확보
		String currentPassword = req.getParameter("currentPassword");
		String newPassword = req.getParameter("newPassword");
		String retypePassword = req.getParameter("retypePassword");
//		2. 인증 객체 확보
		Principal principal = req.getUserPrincipal();
		String username = principal.getName();
//		3. 필수 파라미터 검증
		if (StringUtil.isBlank(currentPassword) ||
			StringUtil.isBlank(newPassword) ||
			StringUtil.isBlank(retypePassword)
			) {
//		4. 필수 파라미터 누락 : 400 에러 전송
			resp.sendError(400, "필수 파라미터 누락");
			return;
		}
//		5. newPassword 와 retypePassword 일치 여부 확인
		String location = null;
		String message = null;
		HttpSession session = req.getSession();
		if(newPassword.equals(retypePassword)) {
			
//		7. 로직을 통해 비번 변경
			try {
				service.changePassword(username, currentPassword, newPassword);
				location = req.getContextPath() + "/logout";
			}catch (AuthenticateException e) {
//		8. 비번 변경시 인증 실패로 인한 예외 발생 : form UI 로 이동(redirect), 비밀번호 오류로 인한 인증 실패(session Scope, flash attribute)
				location = req.getContextPath()+"/change-password";
				message = e.getMessage();
				e.printStackTrace();
			}
			
//		9. 비번 변경 완료, 로그아웃 처리(/logout redirect)
		}else {
//		6. 일치하지 않음. : form UI 로 이동(redirect), 재입력 비밀번호 확인(session Scope)
			location = req.getContextPath()+"/change-password";
			message = "변경할 비밀번에 대한 재입력 오류";
		}
		
		session.setAttribute("message", message);
		resp.sendRedirect(location);
	}
}

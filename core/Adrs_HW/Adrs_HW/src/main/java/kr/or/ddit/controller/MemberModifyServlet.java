package kr.or.ddit.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

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
import kr.or.ddit.util.PopulateUtils;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.UpdateGroup;

@WebServlet("/member/modify")
public class MemberModifyServlet extends HttpServlet {

	private static final String MODELNAME = "member";
	MemberService service = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 수정 form ui 제공, 현재 로그인된 사용자의 기본 정보를 view layer 로 전달 attribute name : MODELNAME

		HttpSession session = req.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute(MODELNAME);
		Map<String, String> errors = (Map) session.getAttribute("errors");

		session.removeAttribute(MODELNAME);
		session.removeAttribute("errors");

		Principal principal = req.getUserPrincipal();
		String username = principal.getName();

		if (member == null) {
			member = service.readMember(username);
		}

		req.setAttribute(MODELNAME, member);
		req.setAttribute("errors", errors);

		String logicalViewName = "member/memberEdit";

		if (logicalViewName.startsWith("redirect:")) {
			String location = logicalViewName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		} else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			String view = prefix + logicalViewName + suffix;
			req.getRequestDispatcher(view).forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		// form data 처리
		Map<String, String[]> parameterMap = req.getParameterMap();
//		1. form-data 수신
//		2. DTO(command object) 로 바인드
		MemberDTO member = new MemberDTO();

		PopulateUtils.populate(member, parameterMap);

//		3. command object 검증

		Map<String, String> errors = ValidateUtils.validate(member, UpdateGroup.class);

		String logicalViewName = null;

		if (errors.isEmpty()) {
			try {
				if (service.modifyMember(member)) {

					logicalViewName = "redirect:/mypage";
				} else {
					session.setAttribute(MODELNAME, member);
					session.setAttribute("message", "관리자에게 문의");
					logicalViewName = "redirect:/member/modify";
				}
			} catch (AuthenticateException e) {
				session.setAttribute(MODELNAME, member);
				session.setAttribute("message", e.getMessage());
				logicalViewName = "redirect:/member/modify";
			}
		} else {
			session.setAttribute(MODELNAME, member);
			session.setAttribute("errors", errors);
			logicalViewName = "redirect:/member/modify";
		}

//		4. 로직을 사용해 수정
//		5. 수정 완료 : /mypage 로 이동 (redirect)
//		6. 인증 실패 : 수정폼으로 이동 (redirect) , 메시지와 기존의 입력데이터를 view 로 전달
		if (logicalViewName.startsWith("redirect:")) {
			String location = logicalViewName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		} else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			String view = prefix + logicalViewName + suffix;
			req.getRequestDispatcher(view).forward(req, resp);
		}
	}

//	private Map<String, String> validate(MemberDTO member) {
//		Map<String, String> errors = new HashMap<>();
//
//		if (StringUtils.isBlank(member.getMemId())) {
//			errors.put("memId", "아이디 필수입력");
//		}
//		if (StringUtils.isBlank(member.getMemPass())) {
//			errors.put("memPass", "비밀번호 필수 입력");
//		}else if(! member.getMemPass().matches("\\w{4,8}")){
//			errors.put("memPass", "비밀번호 패턴 확인");
//		}
//		if (StringUtils.isBlank(member.getMemName())) {
//			errors.put("memName", "이름 필수 입력");
//		}
//		if (StringUtils.isBlank(member.getMemMail())) {
//			errors.put("memMail", "이메일 필수 입력");
//		}
//		if (StringUtils.isBlank(member.getMemHp())) {
//			errors.put("memHp", "휴대폰 필수 입력");
//		}
//		return errors;
//	}
}

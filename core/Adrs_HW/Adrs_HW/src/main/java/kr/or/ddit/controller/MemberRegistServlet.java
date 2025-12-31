package kr.or.ddit.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.internal.StringUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.util.PopulateUtils;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;

@WebServlet("/member/regist")
public class MemberRegistServlet extends HttpServlet {
	private static final String MODELNAME = "member";
	private MemberService service = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 가입 form UI 제공 - 17개의 입력 필드
		HttpSession session = req.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute(MODELNAME);
		Map<String, String> errors = (Map) session.getAttribute("errors");
		session.removeAttribute(MODELNAME);
		session.removeAttribute("errors");
		
		req.setAttribute(MODELNAME, member);
		req.setAttribute("errors", errors);

		String logicalViewName = "/member/memberForm";

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
		// form-data 처리 (17개 form data 수신)
//		1. 파라미터 수신(17)
//		String memId = req.getParameter("memId");
		Map<String, String[]> parameterMap = req.getParameterMap();
		HttpSession session = req.getSession();
		String logicalViewName = null;

//		2. 검증필요(지금은 생략)
//		======= 1
//		2. DTO(command object) 생성 후 form data 바인드 : PopulateUtils  활용
		MemberDTO member = new MemberDTO();
//		member.setMemId(memId);
		try {
			PopulateUtils.populate(member, parameterMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		3. command object 인 DTO 에 대한 객체 검증이 필요하게 됨.
		Map<String, String> errors = ValidateUtils.validate(member, InsertGroup.class);

		if (errors.isEmpty()) {
//		4. 로직을 사용해 가입 처리 : createMember
			ServiceResult result = service.createMember(member);

			if (ServiceResult.PKDUPLICATED.equals(result)) {
//		4. 아이디 중복 : 가입 form UI 로 이동 (redirect) , 기존 입력 데이터와 메시지 전달
				session.setAttribute(MODELNAME, member);
				logicalViewName = "redirect:/member/regist";
				session.setAttribute("message", "아이디가 중복됩니다.");
			} else {
//		5. 가입 성공 : 로그인폼으로 이동 (redirect)
				logicalViewName = "redirect:/login";
			}
		} else {
//			3. 검증 실패 : 기존 입력 데이터(command object), 검증 오류 데이터(errors), 가입 양식으로 이동(redirect)
			
			session.setAttribute(MODELNAME, member);
			session.setAttribute("errors", errors);
			logicalViewName = "redirect:/member/regist";
			
		}

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

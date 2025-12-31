package kr.or.ddit.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import kr.or.ddit.commons.exception.AuthenticateException;
import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.UpdateGroup;


@Controller
@RequestMapping("/member/modify")
public class MemberModifyServlet{

	private static final String MODELNAME = "member";
	MemberService service = new MemberServiceImpl();
	
	@GetMapping
	public String doGet(Model model, Principal principal ) throws ServletException, IOException {
		// 수정 form ui 제공, 현재 로그인된 사용자의 기본 정보를 view layer 로 전달 attribute name : MODELNAME

		String username = principal.getName();

		if(!model.containsAttribute(MODELNAME)) {
			MemberDTO member = service.readMember(username);
			model.addAttribute(MODELNAME, member);
		}
		return "member/memberEdit";
	}

	@PostMapping
	public String doPost(
			@ModelAttribute(MODELNAME) MemberDTO member,
			RedirectAttributes redirectAttributes
			) {

		Map<String, String> errors = ValidateUtils.validate(member, UpdateGroup.class);

		String logicalViewName = null;

		if (errors.isEmpty()) {
			try {
				if (service.modifyMember(member)) {

					logicalViewName = "redirect:/mypage";
				} else {
					redirectAttributes.addFlashAttribute(MODELNAME, member);
					redirectAttributes.addFlashAttribute("message", "관리자에게 문의");
					logicalViewName = "redirect:/member/modify";
				}
			} catch (AuthenticateException e) {
				redirectAttributes.addFlashAttribute(MODELNAME, member);
				redirectAttributes.addFlashAttribute("message", e.getMessage());
				logicalViewName = "redirect:/member/modify";
			}
		} else {
			redirectAttributes.addFlashAttribute(MODELNAME, member);
			redirectAttributes.addFlashAttribute("errors", errors);
			logicalViewName = "redirect:/member/modify";
		}

		return logicalViewName;
	}
}

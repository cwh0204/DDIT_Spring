package kr.or.ddit.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServlet;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.dto.MemberDTO;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;

//@WebServlet("/member/regist")
@Controller
@RequestMapping("/member/regist")
public class MemberRegistServlet{
	private static final String MODELNAME = "member";
	private MemberService service = new MemberServiceImpl();

	@GetMapping
	public String doGet(Model model) {
		return "/member/memberForm";
	}

	@PostMapping
	public String doPost(MemberDTO member, RedirectAttributes redirectAttributes) {


//		3. command object 인 DTO 에 대한 객체 검증이 필요하게 됨.
		Map<String, String> errors = ValidateUtils.validate(member, InsertGroup.class);
		String logicalViewName = null;
		if (errors.isEmpty()) {
//		4. 로직을 사용해 가입 처리 : createMember
			ServiceResult result = service.createMember(member);

			if (ServiceResult.PKDUPLICATED.equals(result)) {
//		4. 아이디 중복 : 가입 form UI 로 이동 (redirect) , 기존 입력 데이터와 메시지 전달
				redirectAttributes.addFlashAttribute(MODELNAME, member);
				redirectAttributes.addFlashAttribute("message", "아이디가 중복됩니다.");
				logicalViewName = "redirect:/member/regist";
			} else {
//		5. 가입 성공 : 로그인폼으로 이동 (redirect)
				logicalViewName = "redirect:/login";
			}
		} else {
//			3. 검증 실패 : 기존 입력 데이터(command object), 검증 오류 데이터(errors), 가입 양식으로 이동(redirect)
			
			redirectAttributes.addFlashAttribute(MODELNAME, member);
			redirectAttributes.addFlashAttribute("errors", errors);
			logicalViewName = "redirect:/member/regist";
			
		}

		return logicalViewName;
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

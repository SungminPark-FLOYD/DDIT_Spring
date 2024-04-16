package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ctc.wstx.util.StringUtil;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member/memberUpdate.do")
public class MemberUpdateControllerServlet{
	private final MemberService service;
	
	@GetMapping
	public String doGet(Principal principal, Model model)  {
		
		if(!model.containsAttribute("member")) {
			String memId = principal.getName();
			MemberVO member = service.retrieveMember(memId);
			model.addAttribute("member", member);
		}
		
		
		String viewName = "member/memberForm";
		return viewName;
	}
	
	@PostMapping
	public String doPost(@Validated(UpdateGroup.class) @ModelAttribute MemberVO member,BindingResult errors, Model model
			,RedirectAttributes redirectAttributes) {

		model.addAttribute("member", member);
		
		
//		 * 3. 로직 사용(model 확보)
		String viewName = null;
		//널인지 아닌지, 컬럼 길이제한이 넘는지 아닌지, date같은경우 날짜 포멧에 맞는지 아닌지
		if(!errors.hasErrors()) {
			
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				redirectAttributes.addFlashAttribute("message", "비밀번호인증실패");
				viewName = "redirect:/member/memberUpdate.do";
				break;
			case FAIL:
				model.addAttribute("message", "서버 오류");
				viewName = "member/memberForm";
				break;
			case OK:
//				req.getSession().setAttribute("lastCreated", member);
				viewName = "redirect:/mypage";
				break;
			}
			// 4. scope를 이용해 model 공유
			
		}else {
			redirectAttributes.addFlashAttribute("member", member);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "member", errors);
			viewName = "redirect:/member/memberUpdate.do";
		}
//		 * 5. view 결정
//		 * 6. view로 이동(flow control)
		
		return viewName;
	}
	
	
}

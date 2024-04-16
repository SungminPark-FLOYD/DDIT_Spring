package kr.or.ddit.member.controller;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;


/**
 * 1. 요청 접수, 분석
 * 2. 검증 
 * 3. 로직 사용(model 확보)
 * 4. scope를 이용해 model 공유
 * 5. view 결정
 * 6. view로 이동(flow control)
 *
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/member/memberInsert.do")
public class MemberInsertControllerServlet {
	
	private final MemberService service;
	
	@ModelAttribute("member")
	public MemberVO member() {
		return new MemberVO();
	}
	
	@GetMapping
	public String doGet()  {	
		return "member/memberForm";
	
	}
	@PostMapping
	public String doPost(@Validated(InsertGroup.class)@ModelAttribute MemberVO member, BindingResult errors, Model model
			,RedirectAttributes redirectAttributes)  {
		
//		 * 3. 로직 사용(model 확보)
		String viewName = null;
		//널인지 아닌지, 컬럼 길이제한이 넘는지 아닌지, date같은경우 날짜 포멧에 맞는지 아닌지
		if(!errors.hasErrors()) {
			
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				redirectAttributes.addFlashAttribute("member", member);
				redirectAttributes.addFlashAttribute("message", "아이디 중복");
				viewName = "redirect:/member/memberInsert.do";
				break;
			case FAIL:
				model.addAttribute("message", "서버 오류");
				viewName = "member/memberForm";
				break;
			case OK:
//				req.getSession().setAttribute("lastCreated", member);
				viewName = "redirect:/";
				break;
			}
			// 4. scope를 이용해 model 공유
			
		}else {
			redirectAttributes.addFlashAttribute("member", member);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "member", errors);
			viewName = "redirect:/member/memberInsert.do";
		}
//		 * 5. view 결정
//		 * 6. view로 이동(flow control)
		
		//모든 컨트롤러에 다 적용시킬 수 있다
		return viewName;
	}

}

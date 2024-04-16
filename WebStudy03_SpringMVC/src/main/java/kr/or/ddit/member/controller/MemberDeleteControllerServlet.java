package kr.or.ddit.member.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member/memberDelete.do")
public class MemberDeleteControllerServlet {
	private final MemberService service;
	@PostMapping
	public String doPost(Principal principal,@RequestParam String password, RedirectAttributes redirectAttributes)  {

		String memId = principal.getName();
		
		String viewName = null;

//			3. 로직 사용
		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);
		inputData.setMemPass(password);
		ServiceResult result = service.removeMember(inputData);
//			4, 로직으로부터 확보한 모델을 공유
		switch (result) {
		case INVALIDPASSWORD:			
			redirectAttributes.addFlashAttribute("message", "비밀번호인증실패");
			viewName = "redirect:/mypage";
			break;
		case FAIL:
			redirectAttributes.addFlashAttribute("message", "서버 오류");
			viewName = "redirect:/mypage";
			break;
		case OK:
			viewName = "forwrd:/login/logout.do";
			break;
		}
		

//		5. 뷰 선택
//		6. 뷰로 이동
//		 * 3. 로직 사용(model 확보)

//		 * 5. view 결정
//		 * 6. view로 이동(flow control)
		
		//모든 컨트롤러에 다 적용시킬 수 있다
		return viewName;
	}
}
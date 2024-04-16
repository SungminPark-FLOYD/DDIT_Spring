package kr.or.ddit.member.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/mypage")
public class MypageControllerServlet {
	private final MemberService service;;

	@GetMapping
	public String doGet(HttpServletRequest req, Principal principal, Model model)  {
		
		String viewName = null;
		MemberVO member = service.retrieveMember(principal.getName());
		
		model.addAttribute("member", member);
		viewName = "member/mypage";
		
		
		return viewName;
		
	}
}

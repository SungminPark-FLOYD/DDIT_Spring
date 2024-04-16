package kr.or.ddit.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member/memberDetail.do")
public class MemberDetailControllerServlet {
	
	private final MemberService service;

	@GetMapping
	public String doGet(@RequestParam String who, Model model) {
		MemberVO member = service.retrieveMember(who);
		model.addAttribute("member", member);				
		return "jsonView";
	}
}

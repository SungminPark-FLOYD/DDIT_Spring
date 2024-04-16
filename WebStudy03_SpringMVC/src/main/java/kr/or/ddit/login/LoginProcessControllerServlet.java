package kr.or.ddit.login;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.server.ResponseStatusException;

import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.login.service.AuthenticateServiceImpl;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/login/loginProcess.do")
public class LoginProcessControllerServlet {
	private final AuthenticateService service;
	
	@PostMapping
	public String doPost(HttpServletRequest req, Model model ,@ModelAttribute MemberVO member){
		HttpSession session = req.getSession(true);
		if(session.isNew()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "로그인을 하려면 로그인 폼이 먼저 최초의 요청을 전송되었어야 함");
		}
		String viewName = null;
		try {
			MemberVO authMember = service.authenticate(member);				
//				인증된 사용자 임을 증명하는 상태정보 생성 및 유지
			
			session.setAttribute("authMember", authMember);
			
//				성공 -> 웰컴페이지로 이동 - redirect
			viewName = "redirect:/index.do";
			
		}catch (AuthenticateException e) {
//				실패 -> 로그인 페이지로 이동 - forward
			session.setAttribute("message", e.getMessage());
			//req.getRequestDispatcher("/login/loginForm.jsp").forward(req, resp);
			viewName = "redirect:/login/loginForm.jsp";
		}
		
//			 * 6. view로 이동(flow control)
		
		//모든 컨트롤러에 다 적용시킬 수 있다
		
		return viewName;
	}
}

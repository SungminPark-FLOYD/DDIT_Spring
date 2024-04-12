package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
@WebServlet("/mypage")
public class MypageControllerServlet extends HttpServlet{
	private final MemberService service;;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVOWrapper principal =  (MemberVOWrapper) req.getUserPrincipal();
		
		String viewName = null;
		
		MemberVO member = service.retrieveMember(principal.getName());
		
		member.getCart().forEach(b -> log.info("prodName = {}", b.getProd()));
		
		req.setAttribute("member", member);
		viewName = "member/mypage";
		
		
		new ViewResolverComposite().resolveView(viewName, req, resp);
		
	}
}

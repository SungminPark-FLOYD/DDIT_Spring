package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * C : /member/memberInsert.do(GET, POST)
 * R (GET)
 * 단건 : /member/memberDetail.do?who=a001 
 * 다건 : /member/memberList.do
 * U : /member/memberUpdate.do(GET,POST)
 * D : /member/memberDelete.do(POST)
 *
 */
@RequiredArgsConstructor
@Controller
@Slf4j
@WebServlet("/member/memberList.do")
public class MemberListControllerServlet extends HttpServlet{
	//로거 설정파일에 있는 이름을 따라간다. -> 패키지에있는 구조를 따라서 클래스를 넘겨주면 알아서 찾아간다
//	private static final Logger logger = LoggerFactory.getLogger(MemberListControllerServlet.class);
	
	private MemberService service;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		log.info("컨트롤러 동작");
		req.setCharacterEncoding("utf-8");
		List<MemberVO> memberList = service.retrieveMemberList();
		//메시지 arg
		log.info("조회된 모델 : {}", memberList);
		req.setAttribute("memberList", memberList);
		
		
		
		String viewName = null;	
		viewName = "member/memberList";
		
		resp.setCharacterEncoding("utf-8");
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}

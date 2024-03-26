package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;


/**
 * C : /member/memberInset.do(GET, POST)
 * R (GET)
 * 단건 : /member/memberDetail.do?who=a001 
 * 다건 : /member/memberList.do
 * U : /member/memberUpdate.do(GET,POST)
 * D : /member/memberDelete.do(POST)
 *
 */
@WebServlet("/member/memberList.do")
public class MemberListControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		req.setCharacterEncoding("utf-8");
		List<MemberVO> memberList = service.retrieveMemberList();
		req.setAttribute("memberList", memberList);
		
		String accept = req.getHeader("accept");
		String viewName = null;
		if(accept.contains("json")) {
			viewName = "/jsonView";
		}else{
			viewName = "/WEB-INF/views/member/memberList.jsp";
		}
		
		resp.setCharacterEncoding("utf-8");
		req.getRequestDispatcher(viewName).forward(req, resp);	
	}
}

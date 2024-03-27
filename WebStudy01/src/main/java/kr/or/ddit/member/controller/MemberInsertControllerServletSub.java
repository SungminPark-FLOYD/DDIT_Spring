package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/memberInsert.do")
public class MemberInsertControllerServletSub extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		req.getRequestDispatcher("/WEB-INF/views/member/memberForm.jsp").forward(req, resp);		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//널인지 아닌지, 컬럼 길이제한이 넘는지 아닌지, date같은경우 날짜 포멧에 맞는지 아닌지
		String memId = (String)req.getParameter("memId");
		String memPass = (String)req.getParameter("memPass");
		String memName = (String)req.getParameter("memName");
		String memRegno1 = (String)req.getParameter("memRegno1");
		String memRegno2 = (String)req.getParameter("memRegno2");
		String memBir = (String)req.getParameter("memBir");
		String memZip = (String)req.getParameter("memZip");
		String memAdd1 = (String)req.getParameter("memAdd1");
		String memAdd2 = (String)req.getParameter("memAdd2");
		String memHometel = (String)req.getParameter("memHometel");
		String memComtel = (String)req.getParameter("memComtel");
		String memHp = (String)req.getParameter("memHp");
		String memMail = (String)req.getParameter("memMail");
		String memJob = (String)req.getParameter("memJob");
		String memLike = (String)req.getParameter("memLike");
		String memMemorial = (String)req.getParameter("memMemorial");
		String memMemorialday = (String)req.getParameter("memMemorialday");
		Long memMileage = Long.valueOf(req.getParameter("memMileage"));
		String memDelete = (String)req.getParameter("memDelete");
		
		MemberVO member = new MemberVO();
		member.setMemId(memId);
		member.setMemPass(memPass);
		member.setMemName(memName);
		member.setMemRegno1(memRegno1);
		member.setMemRegno2(memRegno2);
		member.setMemBir(memBir);
		member.setMemZip(memZip);
		member.setMemAdd1(memAdd1);
		member.setMemAdd2(memAdd2);
		member.setMemHometel(memHometel);
		member.setMemComtel(memComtel);
		member.setMemHp(memHp);
		member.setMemMail(memMail);
		member.setMemJob(memJob);
		member.setMemLike(memLike);
		member.setMemMemorial(memMemorial);
		member.setMemMemorialday(memMemorialday);
		member.setMemMileage(memMileage);
		member.setMemDelete(memDelete);
		
		ServiceResult sr = service.createMember(member);
		HttpSession session = req.getSession(); 
		session.setAttribute("member", member);
		session.setAttribute("sr", sr);
		
		switch (sr) {
		case PKDUPLICATED:
			resp.sendRedirect(req.getContextPath() + "/member/memberInsert.do");
			break;
		case FAIL :
			resp.sendRedirect(req.getContextPath() + "/member/memberInsert.do");
			return;
		case OK :		
			resp.sendRedirect(req.getContextPath() + "/member/memberList.do");
			break;
		}	
	}

}

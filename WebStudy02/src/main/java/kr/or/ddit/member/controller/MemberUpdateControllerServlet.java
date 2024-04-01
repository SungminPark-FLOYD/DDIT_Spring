package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.ctc.wstx.util.StringUtil;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberUpdate.do")
public class MemberUpdateControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.isNew()) {
			resp.sendError(400);
			return;
		}
		MemberVO authMember =(MemberVO) session.getAttribute("authMember");
		
		String viewName = null;
		if(authMember == null) {
			viewName = "redirect:/login/loginForm.jsp";
		}else {
			MemberVO member = service.retrieveMember(authMember.getMemId());
			req.setAttribute("member", member);
			viewName = "/WEB-INF/views/member/memberForm.jsp";
		}
		
		//모든 컨트롤러에 다 적용시킬 수 있다
		if(viewName.startsWith("redirect:")) {
			String location = viewName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		MemberVO member = new MemberVO(); //command Object 
		req.setAttribute("member", member);
		
		Map<String, String[]> paramMap = req.getParameterMap();
		//사용되는 프로퍼티들을 bean에 넣어주는 작업 -> key와 vo의 프로퍼티 이름이 같으면 bean으로 넣어준다
		try {
			BeanUtils.populate(member, paramMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
//		* 2. 검증
		//call by reference 방식
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(member,errors);
		
//		 * 3. 로직 사용(model 확보)
		String viewName = null;
		//널인지 아닌지, 컬럼 길이제한이 넘는지 아닌지, date같은경우 날짜 포멧에 맞는지 아닌지
		if(errors.isEmpty()) {
			
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				req.setAttribute("message", "비밀번호인증실패");
				viewName = "/WEB-INF/views/member/memberForm.jsp";
				break;
			case FAIL:
				req.setAttribute("message", "서버 오류");
				viewName = "/WEB-INF/views/member/memberForm.jsp";
				break;
			case OK:
//				req.getSession().setAttribute("lastCreated", member);
				viewName = "redirect:/mypage";
				break;
			}
			// 4. scope를 이용해 model 공유
			
		}else {
			viewName = "/WEB-INF/views/member/memberForm.jsp";
		}
//		 * 5. view 결정
//		 * 6. view로 이동(flow control)
		
		//모든 컨트롤러에 다 적용시킬 수 있다
		if(viewName.startsWith("redirect:")) {
			String location = viewName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(viewName).forward(req, resp); 
		}
	}
	
	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		//필수 컬럼 검증
		if(StringUtils.isBlank(member.getMemId())) { valid = false; errors.put("memId", "회원번호누락"); }
		if(StringUtils.isBlank(member.getMemPass())) { valid = false; errors.put("memPass", "암호 누락"); }
		if(StringUtils.isBlank(member.getMemName())) { valid = false; errors.put("memName", "회원명 누락"); }
		if(StringUtils.isBlank(member.getMemZip())) { valid = false; errors.put("memZip", "우편번호 누락"); }
		if(StringUtils.isBlank(member.getMemAdd1())) { valid = false; errors.put("memAdd1", "기본주소 누락"); }
		if(StringUtils.isBlank(member.getMemAdd2())) { valid = false; errors.put("memAdd2", "상세주소 누락"); }
		if(StringUtils.isBlank(member.getMemMail())) { valid = false; errors.put("memMail", "메일주소 누락"); }
		return valid;
	}
}

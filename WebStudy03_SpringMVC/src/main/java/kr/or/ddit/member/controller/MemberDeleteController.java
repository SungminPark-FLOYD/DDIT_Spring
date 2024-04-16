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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

//@WebServlet("/member/memberDelete.do")
@RequiredArgsConstructor
	public class MemberDeleteController extends HttpServlet{
	private final MemberService service;
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
			
			ServiceResult result = service.removeMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				req.setAttribute("message", "비밀번호인증실패");
				viewName = "member/memberUpdateForm";
				break;
			case FAIL:
				req.setAttribute("message", "서버 오류");
				viewName = "member/memberUpdateForm";
				break;
			case OK:
				req.getSession().setAttribute("lastCreated", member);
				viewName = "redirect:/member/memberList.do";
				break;
			}
			// 4. scope를 이용해 model 공유
			
		}else {
			viewName = "member/memberUpdateForm";
		}
//		 * 5. view 결정
//		 * 6. view로 이동(flow control)
		
		//모든 컨트롤러에 다 적용시킬 수 있다
	}
	
	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		//필수 컬럼 검증
		if(StringUtils.isBlank(member.getMemId())) { valid = false; errors.put("memId", "회원번호누락"); }
		if(StringUtils.isBlank(member.getMemPass())) { valid = false; errors.put("memPass", "암호 누락"); }
		return valid;
	}
}

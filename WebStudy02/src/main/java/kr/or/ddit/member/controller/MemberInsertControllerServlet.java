package kr.or.ddit.member.controller;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.MemberVO;


/**
 * 1. 요청 접수, 분석
 * 2. 검증 
 * 3. 로직 사용(model 확보)
 * 4. scope를 이용해 model 공유
 * 5. view 결정
 * 6. view로 이동(flow control)
 *
 */
@WebServlet("/member/memberInsert.do")
public class MemberInsertControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		String viewName = "member/memberForm";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVO member = new MemberVO(); //command Object 
		req.setAttribute("member", member);
		Map<String, String[]> paramMap = req.getParameterMap();
		//사용되는 프로퍼티들을 bean에 넣어주는 작업 -> key와 vo의 프로퍼티 이름이 같으면 bean으로 넣어준다
		
		//PopulateUtils로 바꿈
		PopulateUtils.populate(member, paramMap);
		
		System.out.println(member);
//		* 2. 검증
		//call by reference 방식
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidateUtils.validate(member,errors, InsertGroup.class);
//		 * 3. 로직 사용(model 확보)
		String viewName = null;
		//널인지 아닌지, 컬럼 길이제한이 넘는지 아닌지, date같은경우 날짜 포멧에 맞는지 아닌지
		if(errors.isEmpty()) {
			
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				req.setAttribute("message", "아이디 중복");
				viewName = "member/memberForm";
				break;
			case FAIL:
				req.setAttribute("message", "서버 오류");
				viewName = "member/memberForm";
				break;
			case OK:
//				req.getSession().setAttribute("lastCreated", member);
				viewName = "redirect:/";
				break;
			}
			// 4. scope를 이용해 model 공유
			
		}else {
			viewName = "member/memberForm";
		}
//		 * 5. view 결정
//		 * 6. view로 이동(flow control)
		
		//모든 컨트롤러에 다 적용시킬 수 있다
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}

}

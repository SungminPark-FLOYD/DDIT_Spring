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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.paging.DefaultPaginationRenderer;
import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.paging.PaginationRenderer;
import kr.or.ddit.paging.SimpleCondition;
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
@RequestMapping("/member/memberList.do")
public class MemberListControllerServlet {
	//로거 설정파일에 있는 이름을 따라간다. -> 패키지에있는 구조를 따라서 클래스를 넘겨주면 알아서 찾아간다
//	private static final Logger logger = LoggerFactory.getLogger(MemberListControllerServlet.class);
	
	@Autowired
	private MemberService service;
	@GetMapping
	protected String doGet(Model model, @ModelAttribute("paging") PaginationInfo paging
			) {	
		log.info("컨트롤러 동작");

		List<MemberVO> memberList = service.retrieveMemberList(paging);
		//메시지 arg
		log.info("조회된 모델 : {}", memberList);
		model.addAttribute("memberList", memberList);
			
		//나중에 컨테이너에서 주입받는 구조 가능
		PaginationRenderer render = new DefaultPaginationRenderer();
		
		model.addAttribute("pageFunction", "paging");
		String pagingHTML = render.renderPagination(paging, "paging");
		model.addAttribute("pagingHTML", pagingHTML);
		
		String viewName = "member/memberList";
		
		return viewName;
	}
}

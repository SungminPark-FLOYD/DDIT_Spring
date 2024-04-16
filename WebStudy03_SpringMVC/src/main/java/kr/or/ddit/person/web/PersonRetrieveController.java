package kr.or.ddit.person.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.person.service.PersonService;
import kr.or.ddit.person.service.PersonServiceImpl;
import kr.or.ddit.vo.PersonVo;



/**
 * RESTful URI
 * (명사(URI)와 동사(Method)의 분리형태)
 *
 * /member/memberList.do 회원 목록
 * /member/memberInsert.do 회원 등록
 * 
 * /member(GET) 회원의 목록을 조회하다
 * /member/a001 (GET) a001을 조회하다
 * /member/a001 (PUT) a001을 수정하다
 * /member/a001 (DELETE) a001을 삭제하다
 * /member/new (POST) 새로운 회원을 등록하다
 */
@Controller
@RequestMapping("/people")
public class PersonRetrieveController {
//	private PersonDAOImpl dao = new PersonDAOImpl();
	@Autowired
	private PersonService service;
	
	@GetMapping
	public String list(Model model) {
		List<PersonVo> people =  service.retrievePersonList();
		
		model.addAttribute("people", people);
		return "/person/people";
	}
	
	@GetMapping("{who}")
	public String detail(@PathVariable String who, Model model)  {		
		PersonVo person = service.retrievePerson(who);
		model.addAttribute("person", person);
		return "person/detail";	
	}
}

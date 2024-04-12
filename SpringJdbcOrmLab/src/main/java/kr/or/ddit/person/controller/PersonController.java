package kr.or.ddit.person.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.or.ddit.person.service.PersonService;
import kr.or.ddit.vo.PersonVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PersonController {
	private final PersonService service;
	
	public void init() {
		log.info("주입이 완료된 객체 : {}", service.getClass().getSimpleName());
	}
	
	public List<PersonVo> personListToResponse() {
		return service.retrievePersonList();
	}
	
	
}

package kr.or.ddit.case5.person.controller;

import java.util.List;

import kr.or.ddit.case5.person.service.PersonService;
import kr.or.ddit.vo.PersonVo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonController {
	private final PersonService service;
	
	public List<PersonVo> personListToResponse() {
		return service.retrievePersonList();
	}
}

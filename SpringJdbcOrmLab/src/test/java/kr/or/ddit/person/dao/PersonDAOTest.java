package kr.or.ddit.person.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.AbstractModelContextTest;
import kr.or.ddit.person.service.PersonService;

class PersonDAOTest extends AbstractModelContextTest{
	
	@Autowired
	PersonDAO dao;
	
	@Autowired
	PersonService service;
	
	@Test
	void testSelectPersonList() {
		dao.selectPersonList();
	}

	@Test
	void testSelectPerson() {
		dao.selectPerson("L0014");
	}
	
	@Test
	void test2() {
		service.retrievePersonList();
	}

}

package kr.or.ddit.person.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.PersonVo;

class PersonDAOImplTest {
	PersonDAO dao = new PersonDAOImpl();
	
	@Test
	void testSelectPersonList() {
		List<PersonVo> people = dao.selectPersonList();
		System.out.println(people.size());
	}

	@Test
	void testSelectPerson() {
		//assertion : 구조를 통해 테스트 결과를 추정함
		assertNotNull(dao.selectPerson("A0011"));
	}
	
	@Test
	void testSelectPersonNotExist() {
		//assertion : 구조를 통해 테스트 결과를 추정함
		assertNull(dao.selectPerson("asdasd"));
	}

}

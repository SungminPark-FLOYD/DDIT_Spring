package kr.or.ddit.adressBook.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.AbstractModelContextTest;
import kr.or.ddit.adressBook.service.AdressBookService;
import kr.or.ddit.vo.AdressBookVO;

@Transactional
class AdressBookDAOTest extends AbstractModelContextTest{
	
	@Autowired
	AdressBookService service;
	@Test
	void testSelectList() {
		service.selectAddressList();
	}

	@Test
	void testSelectOne() {
		
	}

	@Test
	void testInsert() {
		AdressBookVO address = new AdressBookVO();
		address.setMemId("a001");
		address.setAdrsAdd("대전 중구2");
		address.setAdrsName("대덕인재개발원2");
		address.setAdrsTel("00-0000-00002");
		service.insert(address);
	}

	@Test
	void testUpdate() {
		
	}

	@Test
	void testDelete() {
		
	}

}

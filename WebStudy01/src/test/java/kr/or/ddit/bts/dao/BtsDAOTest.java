package kr.or.ddit.bts.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.BtsVO;

class BtsDAOTest {

	BtsDAO dao = new InMemoryDAOImpl();
	@Test
	void testSelectBts() {
		BtsVO bts = dao.selectBts("B001");
		assertNotNull(bts); 
		
		assertNull(dao.selectBts("B013"));
	}

	@Test
	void testSelectBtsList() {
		fail("Not yet implemented");
	}
	


}

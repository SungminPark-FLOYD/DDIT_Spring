package kr.or.ddit.bts.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.BtsVO;

class InMemoryDAOImplTest {
	InMemoryDAOImpl dao = new InMemoryDAOImpl();

	@Test
	void test() {
		dao.selectBts("B001");
	}
	@Test
	void test1() {
		dao.selectBtsList();
	}
	
	@Test
	void testIncrementHit() {
		BtsVO bts = dao.selectBts("B001");
		dao.incrementHit("B001");
		System.out.println(dao.selectBts("B001"));
	}

}

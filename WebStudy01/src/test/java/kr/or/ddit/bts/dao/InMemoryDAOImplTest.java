package kr.or.ddit.bts.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InMemoryDAOImplTest {

	@Test
	void test() {
		InMemoryDAOImpl te = new InMemoryDAOImpl();
		te.selectBts("B001");
	}
	@Test
	void test1() {
		InMemoryDAOImpl te = new InMemoryDAOImpl();
		te.selectBtsList();
	}

}

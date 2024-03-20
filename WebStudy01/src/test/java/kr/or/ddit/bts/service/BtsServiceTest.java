package kr.or.ddit.bts.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.BtsVO;

class BtsServiceTest {

	BtsService service = new BtsServiceImpl();
	@Test
	void testReadBts() {
		BtsVO bts = service.readBts("B001");
		System.out.println(bts);
	}

	@Test
	void testReadBtsList() {
		fail("Not yet implemented");
	}

	@Test
	void testIncrementHit() {
		fail("Not yet implemented");
	}

}

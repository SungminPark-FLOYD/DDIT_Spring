package kr.or.ddit.paging;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class PaginationInfoTest {

	@Test
	void test() {
		PaginationInfo paging = new PaginationInfo();
		log.info("{}",paging);
		paging.setTotalRecord(101);
		log.info("{}",paging);
		paging.setCurrentPage(3);
		log.info("{}",paging);
	}

}

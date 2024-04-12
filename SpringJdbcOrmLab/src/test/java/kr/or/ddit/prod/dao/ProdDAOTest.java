package kr.or.ddit.prod.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import kr.or.ddit.AbstractModelContextTest;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ProdDAOTest extends AbstractModelContextTest{
	
	@Autowired
	ProdDAO dao;
	@Test
	void testSelectProdList() {
		List<ProdVO> pv = dao.selectProdList();
		pv.forEach(p -> log.info("a : {}", p));
	}

	@Test
	void testSelectProd() {
		ProdVO vo = dao.selectProd("P302000005");
		log.info("{}", vo);
	}

}

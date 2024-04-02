package kr.or.ddit.prod.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ProdDAOTest {
	private ProdDAO dao = new ProdDAOImpl();
	

	@Test
	@DisplayName("전체 리스트 불러오기")
	void test() {
		List<ProdVO> prodList = dao.selectProdList();
		log.info("list : {}", prodList);
	}
	
	@Test
	@DisplayName("선택한 제품 정보 불러오기")
	void selectProdTest() {
		ProdVO prod = new ProdVO();
		prod = dao.selectProd("P101000001");
		log.info("prod ={}", prod);
	}

}

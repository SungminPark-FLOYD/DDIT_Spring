package kr.or.ddit.prod.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.AbstractRootContextTest;
import kr.or.ddit.paging.PaginationInfo;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ProdDAOTest extends AbstractRootContextTest{
	@Autowired
	private ProdDAO dao;;
	

	@Test
	@DisplayName("전체 리스트 불러오기")
	void test() {
		PaginationInfo paging = new PaginationInfo();
		paging.setTotalRecord(dao.selectTotalRecord(paging));
		log.info("{}", paging);
		paging.setCurrentPage(4);
		List<ProdVO> prodList = dao.selectProdList(paging);
		log.info("list : {}", prodList);
		log.info("{}", paging);
	}
	
	@Test
	@DisplayName("선택한 제품 정보 불러오기")
	void selectProdTest() {
		ProdVO prod = new ProdVO();
		prod = dao.selectProd("P101000001");
		log.info("prod ={}", prod);
	}
	
	@Test
	@DisplayName("제품 등록")
	void insertProdTest() {
		ProdVO prod = new ProdVO();
		prod.setProdName("맥북프로 3");
		prod.setProdLgu("P103");
		prod.setProdBuyer("P20102");
		prod.setProdCost((long) 123213);
		prod.setProdPrice((long) 123123);
		prod.setProdSale((long) 123123);
		prod.setProdOutline("선명한 화질 1");
		prod.setProdImg("P201000009.gif");
		prod.setProdTotalstock((long) 12312);
		prod.setProdInsdate(LocalDate.now());
		prod.setProdProperstock((long) 213123);
		prod.setProdSize("14인치");
		prod.setProdColor("은색");
		prod.setProdDelivery("파손 주의");
		prod.setProdUnit("EA");
		
		int a = dao.insertProd(prod);
		
		System.out.println(prod);
				
	}

}
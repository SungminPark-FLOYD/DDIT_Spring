package kr.or.ddit.lprod.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.AbstractRootContextTest;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class LprodDAOTest extends AbstractRootContextTest{
	@Autowired
	LprodDAO dao;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testSelectLprodList() {
		//해당 lprod있는 거래처의 정보를 같이 출력
		//거래처의 아이디와 거래처의 이름
		List<LprodVO> lprodlist = dao.selectLprodList();
		
		lprodlist.forEach(lp -> log.info("lp = {}",lp));
		
		List<BuyerVO> buyerList = new ArrayList<BuyerVO>();
		for(LprodVO lprod : lprodlist) {
			buyerList.addAll(lprod.getBuyerList());
		}	
		buyerList.forEach(bl -> log.info("buyer : {}", bl));
	}

}

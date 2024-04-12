package kr.or.ddit.buyer.dao;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.AbstractModelContextTest;


class BuyerDAOTest extends AbstractModelContextTest{

	@Resource(name = "buyerDAO")
	BuyerDAO dao;
	
	@Test
	void testSelectBuyerList() {
		dao.selectBuyerList();
	}

}

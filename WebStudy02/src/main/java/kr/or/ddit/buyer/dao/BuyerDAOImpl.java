package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.BuyerVO;

public class BuyerDAOImpl implements BuyerDAO {
	private SqlSessionFactory factory = CustomSqlSessionFactoryBuilder.getSessionFactory();
	@Override
	public List<BuyerVO> selectBuyerList() {
		try(
			SqlSession session = factory.openSession();	
		){
			return session.getMapper(BuyerDAO.class).selectBuyerList();	
		}
	}

	@Override
	public BuyerVO selectBuyer(String buyerId) {
		try(
				SqlSession session = factory.openSession();	
			){
				return session.getMapper(BuyerDAO.class).selectBuyer(buyerId);	
			}
	}

}

package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements ProdDAO {
	private SqlSessionFactory factory = CustomSqlSessionFactoryBuilder.getSessionFactory();

	@Override
	public int insertProd(ProdVO prod) {
		try(
			SqlSession session = factory.openSession();	
		){
			int rowcnt = session.getMapper(ProdDAO.class).insertProd(prod);	
			if(rowcnt > 0) 
				session.commit();
			return rowcnt;
		}
	}

	@Override
	public List<ProdVO> selectProdList() {
		try(
			SqlSession session = factory.openSession();	
		){
			return session.getMapper(ProdDAO.class).selectProdList();	
		}
	}

	@Override
	public ProdVO selectProd(String prodId) {
		try(
			SqlSession session = factory.openSession();	
		){
			return session.getMapper(ProdDAO.class).selectProd(prodId);	
		}
	}

	@Override
	public int updateProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return 0;
	}
}

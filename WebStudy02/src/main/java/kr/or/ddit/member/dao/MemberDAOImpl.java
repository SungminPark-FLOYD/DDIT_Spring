package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.ConnectionFactory_HikariCP;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.utils.NamingUtils;
import kr.or.ddit.vo.DbVO;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	
	private SqlSessionFactory factory = CustomSqlSessionFactoryBuilder.getSessionFactory();
	
	@Override
	public int insertMember(MemberVO member) {
		try(
			//값 안주면 기본 autocommit기능 false
			SqlSession session = factory.openSession();
		){			
			int rowcnt = session.insert("kr.or.ddit.member.dao.MemberDAO.insertMember", member);
			if(rowcnt > 0) {
				session.commit();
			}
			return rowcnt;		
		}
		
			
	}
	

	@Override
	public List<MemberVO> selectMemberList() {	
		try (
				SqlSession session = factory.openSession();
			) {
				return session.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemberList");
		} 
	
	}

	@Override
	public MemberVO selectMember(String memId) {
		try (
				SqlSession session = factory.openSession();
			){		
				
				return session.selectOne("kr.or.ddit.member.dao.MemberDAO.selectMember",memId);
			}
	}


	@Override
	public int update(MemberVO member) {
		try(
			SqlSession session = factory.openSession();
		){
			int rowcnt = session.update("kr.or.ddit.member.dao.MemberDAO.update", member);
			if(rowcnt > 0) {
				session.commit();
			}
			return rowcnt;
		}
	}


	@Override
	public int delete(String memId) {
		
		try(
			SqlSession session = factory.openSession();
		){
			int rowcnt = session.update("kr.or.ddit.member.dao.MemberDAO.delete", memId);
			if(rowcnt > 0) {
				session.commit();
			}
			return rowcnt;
		}
	}

}

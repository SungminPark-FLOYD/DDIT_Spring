package kr.or.ddit.member.dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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
	private MemberDAO generateProxy(SqlSession sqlSession) {
		return (MemberDAO) Proxy.newProxyInstance(MemberDAO.class.getClassLoader(), new Class[] {MemberDAO.class}, new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// session.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemberList");
				//인터페이스의 참조 얻기 -> 인터페이스에서 결정된 메소드의 전체 경로 가져오기
				String namespace = method.getDeclaringClass().getName();
				String id = method.getName();
				String statement = namespace + "." + id;
				
				Object argument = null;
				if(args != null && args.length > 0) {
					argument = args[0];
				}
						
				if(method.getReturnType().equals(List.class)) {
					return sqlSession.selectList(statement, argument);
				}else {
					return sqlSession.selectOne(statement, argument);
				}
			}
		});
	}
	
	@Override
	public int insertMember(MemberVO member) {
		try(
			//값 안주면 기본 autocommit기능 false
			SqlSession session = factory.openSession();
		){			
//			int rowcnt = session.insert("kr.or.ddit.member.dao.MemberDAO.insertMember", member);
			int rowcnt = session.getMapper(MemberDAO.class).insertMember(member);
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
//				return session.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemberList");
//				return generateProxy(session).selectMemberList();
				MemberDAO mapperProxy = session.getMapper(MemberDAO.class);
				return mapperProxy.selectMemberList();
		} 
	
	}

	@Override
	public MemberVO selectMember(String memId) {
		try (
				SqlSession session = factory.openSession();
			){		
				
//				return session.selectOne("kr.or.ddit.member.dao.MemberDAO.selectMember",memId);
//				return generateProxy(session).selectMember(memId);
				return session.getMapper(MemberDAO.class).selectMember(memId);
			}
	}


	@Override
	public int update(MemberVO member) {
		try(
			SqlSession session = factory.openSession();
		){
//			int rowcnt = session.update("kr.or.ddit.member.dao.MemberDAO.update", member);
			int rowcnt = session.getMapper(MemberDAO.class).update(member);
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
//			int rowcnt = session.update("kr.or.ddit.member.dao.MemberDAO.delete", memId);
			int rowcnt = session.getMapper(MemberDAO.class).delete(memId);
			if(rowcnt > 0) {
				session.commit();
			}
			return rowcnt;
		}
	}

	@Override
	public MemberVO selectMemberForAuth(String memId) {
		try(
			SqlSession session = factory.openSession();
		){
			return session.getMapper(MemberDAO.class).selectMemberForAuth(memId);
			
		}
	}

}

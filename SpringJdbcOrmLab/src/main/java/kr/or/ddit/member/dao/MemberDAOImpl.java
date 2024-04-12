package kr.or.ddit.member.dao;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import kr.or.ddit.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
//	@Autowired
//	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private SqlSession sqlSession;
	
//	private MemberDAO generateProxy(SqlSession sqlSession) {
//		return (MemberDAO) Proxy.newProxyInstance(MemberDAO.class.getClassLoader(), new Class[] {MemberDAO.class}, new InvocationHandler() {
//			
//			@Override
//			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//				// session.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemberList");
//				//인터페이스의 참조 얻기 -> 인터페이스에서 결정된 메소드의 전체 경로 가져오기
//				String namespace = method.getDeclaringClass().getName();
//				String id = method.getName();
//				String statement = namespace + "." + id;
//				
//				Object argument = null;
//				if(args != null && args.length > 0) {
//					argument = args[0];
//				}
//						
//				if(method.getReturnType().equals(List.class)) {
//					return sqlSession.selectList(statement, argument);
//				}else {
//					return sqlSession.selectOne(statement, argument);
//				}
//			}
//		});
//	}
	
	@Override
	public int insertMember(MemberVO member) {
		
//			int rowcnt = session.insert("kr.or.ddit.member.dao.MemberDAO.insertMember", member);
			int rowcnt = sqlSession.getMapper(MemberDAO.class).insertMember(member);
			if(rowcnt > 0) {
				sqlSession.commit();
			}
			return rowcnt;			
			
	}
	

	@Override
	public List<MemberVO> selectMemberList() {	
//				return session.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemberList");
//				return generateProxy(session).selectMemberList();
				MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
				return mapperProxy.selectMemberList();
	
	}

	@Override
	public MemberVO selectMember(String memId) {
				
//				return session.selectOne("kr.or.ddit.member.dao.MemberDAO.selectMember",memId);
//				return generateProxy(session).selectMember(memId);
				return sqlSession.getMapper(MemberDAO.class).selectMember(memId);
	}


	@Override
	public int update(MemberVO member) {
//			int rowcnt = session.update("kr.or.ddit.member.dao.MemberDAO.update", member);
			int rowcnt = sqlSession.getMapper(MemberDAO.class).update(member);
			if(rowcnt > 0) {
				sqlSession.commit();
			}
			return rowcnt;
	}


	@Override
	public int delete(String memId) {
		
//			int rowcnt = session.update("kr.or.ddit.member.dao.MemberDAO.delete", memId);
			int rowcnt = sqlSession.getMapper(MemberDAO.class).delete(memId);
			if(rowcnt > 0) {
				sqlSession.commit();
			}
			return rowcnt;
	}

	@Override
	public MemberVO selectMemberForAuth(String memId) {
			return sqlSession.getMapper(MemberDAO.class).selectMemberForAuth(memId);
			
	}

}

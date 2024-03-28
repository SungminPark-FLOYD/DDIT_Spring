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
import kr.or.ddit.exception.PersistenceException;
import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.utils.NamingUtils;
import kr.or.ddit.vo.DbVO;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	
	private SqlSessionFactory factory = CustomSqlSessionFactoryBuilder.getSessionFactory();
	
	@Override
	public int insertMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();
//		1.
		sql.append(" INSERT INTO member (      ");
		sql.append(" 	    mem_id,            ");
		sql.append(" 	    mem_pass,          ");
		sql.append(" 	    mem_name,          ");
		sql.append(" 	    mem_regno1,        ");
		sql.append(" 	    mem_regno2,        ");
		sql.append(" 	    mem_bir,           ");
		sql.append(" 	    mem_zip,           ");
		sql.append(" 	    mem_add1,          ");
		sql.append(" 	    mem_add2,          ");
		sql.append(" 	    mem_hometel,       ");
		sql.append(" 	    mem_comtel,        ");
		sql.append(" 	    mem_hp,            ");
		sql.append(" 	    mem_mail,          ");
		sql.append(" 	    mem_job,           ");
		sql.append(" 	    mem_like,          ");
		sql.append(" 	    mem_memorial,      ");
		sql.append(" 	    mem_memorialday,   ");
		sql.append(" 	    mem_mileage,       ");
		sql.append(" 	    mem_delete         ");
		sql.append(" 	) VALUES (             ");
		sql.append(" 	    ?,                 ");
		sql.append(" 	    ?,                 ");
		sql.append(" 	    ?,                 ");
		sql.append(" 	    ?,                 ");
		sql.append(" 	    ?,                 ");
		sql.append(" 	    TO_DATE(?, 'yyyy-mm-dd'),                 ");
		sql.append(" 	    ?,                 ");
		sql.append(" 	    ?,                 ");
		sql.append(" 	    ?,                 ");
		sql.append(" 	    ?,                 ");
		sql.append(" 	    ?,                 ");
		sql.append(" 	    ?,                 ");
		sql.append(" 	    ?,                 ");
		sql.append(" 	    ?,                 ");
		sql.append(" 	    ?,                 ");
		sql.append(" 	    ?,                 ");
		sql.append(" 	    TO_DATE(?, 'yyyy-mm-dd') ,                 ");
		sql.append(" 	    ?,                 ");
		sql.append(" 	    ?                  ");
		sql.append(" 	)                      ");
		try(
			Connection conn = ConnectionFactory_HikariCP.getConnection();	
//			Statement stmt = conn.createStatement();	
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
//			3.
			int idx = 1;
			pstmt.setString(idx++, member.getMemId());
			pstmt.setString(idx++, member.getMemPass());
			pstmt.setString(idx++, member.getMemName());
			pstmt.setString(idx++, member.getMemRegno1());
			pstmt.setString(idx++, member.getMemRegno2());
			pstmt.setString(idx++, member.getMemBir());
			pstmt.setString(idx++, member.getMemZip());
			pstmt.setString(idx++, member.getMemAdd1());
			pstmt.setString(idx++, member.getMemAdd2());
			pstmt.setString(idx++, member.getMemHometel());
			pstmt.setString(idx++, member.getMemComtel());
			pstmt.setString(idx++, member.getMemHp());
			pstmt.setString(idx++, member.getMemMail());
			pstmt.setString(idx++, member.getMemJob());
			pstmt.setString(idx++, member.getMemLike());
			pstmt.setString(idx++, member.getMemMemorial());
			pstmt.setString(idx++, member.getMemMemorialday());
			if(member.getMemMileage()==null) {
				pstmt.setNull(idx++, JDBCType.NUMERIC.ordinal());
			}else {
				pstmt.setLong(idx++, member.getMemMileage());
			}
			pstmt.setString(idx++, member.getMemDelete());
			
			int rowcnt = pstmt.executeUpdate();
			return rowcnt;
		}catch (SQLException e) {
			throw new PersistenceException(e);
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
		StringBuffer sql = new StringBuffer();
//		1.
		sql.append("UPDATE member               ");
	    sql.append("SET                         ");
	    sql.append("    mem_pass =?,          ");
	    sql.append("    mem_name =?,          ");
	    sql.append("    mem_regno1 =?,        ");
	    sql.append("    mem_regno2 =?,        ");
	    sql.append("    mem_bir = TO_DATE(?, 'yyyy-mm-dd'),           ");
	    sql.append("    mem_zip =?,           ");
	    sql.append("    mem_add1 =?,          ");
	    sql.append("    mem_add2 =?,          ");
	    sql.append("    mem_hometel =?,       ");
	    sql.append("    mem_comtel =?,       ");
	    sql.append("    mem_hp =?,           ");
	    sql.append("    mem_mail =?,         ");
	    sql.append("    mem_job =?,          ");
	    sql.append("    mem_like =?,         ");
	    sql.append("    mem_memorial =?,     ");
	    sql.append("    mem_memorialday = TO_DATE(?, 'yyyy-mm-dd'),  ");
	    sql.append("    mem_mileage =?,      ");
	    sql.append("    mem_delete =?        ");
		sql.append("WHERE                       ");
		sql.append("    mem_id =?            ");
		try(
			Connection conn = ConnectionFactory_HikariCP.getConnection();	
//			Statement stmt = conn.createStatement();	
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
//			3.
			int idx = 1;
			pstmt.setString(idx++, member.getMemPass());
			pstmt.setString(idx++, member.getMemName());
			pstmt.setString(idx++, member.getMemRegno1());
			pstmt.setString(idx++, member.getMemRegno2());
			pstmt.setString(idx++, member.getMemBir());
			pstmt.setString(idx++, member.getMemZip());
			pstmt.setString(idx++, member.getMemAdd1());
			pstmt.setString(idx++, member.getMemAdd2());
			pstmt.setString(idx++, member.getMemHometel());
			pstmt.setString(idx++, member.getMemComtel());
			pstmt.setString(idx++, member.getMemHp());
			pstmt.setString(idx++, member.getMemMail());
			pstmt.setString(idx++, member.getMemJob());
			pstmt.setString(idx++, member.getMemLike());
			pstmt.setString(idx++, member.getMemMemorial());
			pstmt.setString(idx++, member.getMemMemorialday());
			if(member.getMemMileage()==null) {
				pstmt.setNull(idx++, JDBCType.NUMERIC.ordinal());
			}else {
				pstmt.setLong(idx++, member.getMemMileage());
			}
			pstmt.setString(idx++, member.getMemDelete());
			pstmt.setString(idx++, member.getMemId());
			int rowcnt = pstmt.executeUpdate();
			return rowcnt;
		}catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}


	@Override
	public int delete(String memId) {
		StringBuffer sql = new StringBuffer();
//		1.
		sql.append("DELETE FROM member  ");
		sql.append("WHERE               ");
		sql.append("    mem_id = ?      ");
		try(
			Connection conn = ConnectionFactory_HikariCP.getConnection();	
//			Statement stmt = conn.createStatement();	
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
//			3.
			pstmt.setString(1, memId);
			int rowcnt = pstmt.executeUpdate();
			return rowcnt;
		}catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

}

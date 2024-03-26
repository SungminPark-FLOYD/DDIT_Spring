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

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.exception.PersistenceException;
import kr.or.ddit.utils.NamingUtils;
import kr.or.ddit.vo.DbVO;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {

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
			Connection conn = ConnectionFactory.getConnection();	
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
		StringBuffer sql = new StringBuffer();

		sql.append(" SELECT                                         ");
		sql.append(" MEM_ID,  MEM_NAME                     ");
		sql.append(" ,  MEM_ADD1, MEM_ADD2                  ");
		sql.append(" ,  MEM_HP              ");
		sql.append(" , MEM_MAIL                ");
		sql.append(" , MEM_MILEAGE   ");
        sql.append("                                                ");
		sql.append(" FROM MEMBER                                    ");
		
		try (
			Connection conn = ConnectionFactory.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
			
			ResultSet rs = pstmt.executeQuery();
			
			List<MemberVO> list = new ArrayList<MemberVO>();
			while (rs.next()) {
				MemberVO member = new MemberVO();
				member.setMemId(rs.getString("MEM_ID"));
				member.setMemName(rs.getString("MEM_NAME"));
				member.setMemAdd1(rs.getString("MEM_ADD1"));
				member.setMemAdd2(rs.getString("MEM_ADD2"));
				member.setMemHp(rs.getString("MEM_HP"));
				member.setMemMail(rs.getString("MEM_MAIL"));
				member.setMemMileage(rs.getLong("MEM_MILEAGE"));
				list.add(member);
			}

			// Conection full 구조이기 때문에 로직이 종료된 후에 연결을 종료 시켜주어야 한다
			rs.close();
			return list;
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

	@Override
	public MemberVO selectMember(String memId) {
		StringBuffer sql = new StringBuffer();

		sql.append(" SELECT                                         ");
		sql.append(" MEM_ID, MEM_PASS, MEM_NAME                     ");
		sql.append(" , MEM_REGNO1, MEM_REGNO2, TO_CHAR(MEM_BIR, 'YYYY-MM-DD') MEM_BIR            ");
		sql.append(" , MEM_ZIP, MEM_ADD1, MEM_ADD2                  ");
		sql.append(" , MEM_HOMETEL, MEM_COMTEL, MEM_HP              ");
		sql.append(" , MEM_MAIL, MEM_JOB, MEM_LIKE                  ");
		sql.append(" , MEM_MEMORIAL, TO_CHAR(MEM_MEMORIALDAY, 'YYYY-MM-DD') MEM_MEMORIALDAY, MEM_MILEAGE   ");
		sql.append(" , MEM_DELETE                                   ");
        sql.append("                                                ");
		sql.append(" FROM MEMBER                                    ");
		sql.append(" WHERE MEM_ID = ?                         ");
		try (
			Connection conn = ConnectionFactory.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
			
			pstmt.setString(1, memId);
			ResultSet rs = pstmt.executeQuery();
			
			
			MemberVO member = null;
			if (rs.next()) {
				member = new MemberVO();
				member.setMemId(rs.getString("MEM_ID"));
				member.setMemPass(rs.getString("MEM_PASS"));
				member.setMemName(rs.getString("MEM_NAME"));
				member.setMemRegno1(rs.getString("MEM_REGNO1"));
				member.setMemRegno2(rs.getString("MEM_REGNO2"));
				member.setMemBir(rs.getString("MEM_BIR"));
				member.setMemZip(rs.getString("MEM_ZIP"));
				member.setMemAdd1(rs.getString("MEM_ADD1"));
				member.setMemAdd2(rs.getString("MEM_ADD2"));
				member.setMemHometel(rs.getString("MEM_HOMETEL"));
				member.setMemComtel(rs.getString("MEM_COMTEL"));
				member.setMemHp(rs.getString("MEM_HP"));
				member.setMemMail(rs.getString("MEM_MAIL"));
				member.setMemJob(rs.getString("MEM_JOB"));
				member.setMemLike(rs.getString("MEM_LIKE"));
				member.setMemMemorial(rs.getString("MEM_MEMORIAL"));
				member.setMemMemorialday(rs.getString("MEM_MEMORIALDAY"));
				member.setMemMileage(rs.getLong("MEM_MILEAGE"));
				member.setMemDelete(rs.getString("MEM_DELETE"));
				
			}

			// Conection full 구조이기 때문에 로직이 종료된 후에 연결을 종료 시켜주어야 한다
			rs.close();
			return member;
		} catch (SQLException e) {
			throw new PersistenceException(e);
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
			Connection conn = ConnectionFactory.getConnection();	
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
			Connection conn = ConnectionFactory.getConnection();	
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

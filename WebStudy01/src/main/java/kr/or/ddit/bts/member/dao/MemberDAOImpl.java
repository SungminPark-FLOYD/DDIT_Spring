package kr.or.ddit.bts.member.dao;

import java.sql.Connection;
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
		try(
				Connection conn = ConnectionFactory.getConnection();
				Statement stmt = conn.createStatement();
			) {
				StringBuffer sql = new StringBuffer();
				
				sql.append(String.format("insert into member(MEM_ID, MEM_PASS, MEM_NAME, MEM_REGNO1, MEM_REGNO2, "
						+ "MEM_BIR, MEM_ZIP, MEM_ADD1, MEM_ADD2, MEM_HOMETEL, MEM_COMTEL, MEM_HP, MEM_MAIL,"
						+ "MEM_JOB, MEM_LIKE, MEM_MEMORIAL, MEM_MEMORIALDAY, MEM_MILEAGE, MEM_DELETE)"
						+ "values('%s', '%s' ,'%s', '%s', '%s', '%s', '%s', '%s'"
						+ "		,'%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d, '%s')", 
						member.getMemId(), member.getMemPass(), member.getMemName(), member.getMemRegno1(), member.getMemRegno2()
						, member.getMemBir(), member.getMemZip(), member.getMemAdd1(), member.getMemAdd2(),
						member.getMemHometel(), member.getMemComtel(), member.getMemHp(), member.getMemMail()
						, member.getMemJob(), member.getMemLike(), member.getMemMemorial(), member.getMemMemorialday(),
						member.getMemMileage(), member.getMemDelete()
						));
//				if(member.getMemMileage() == null ) {
//					pstmt.setNUll(idx++, JDBC.NUMERIC.ordinal())
//				}
//				
				int cnt = stmt.executeUpdate(sql.toString());
					

				return cnt;
				
			}catch (SQLException e) {
				throw new PersistenceException(e);
			}
			
	}
	

	@Override
	public List<MemberVO> selectMemberList() {
		StringBuffer sql = new StringBuffer();

		sql.append("select MEM_ID, MEM_NAME, MEM_ADD1, MEM_ADD2, MEM_HP,  MEM_MAIL,MEM_MILEAGE ");
		sql.append("from member      ");
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
				System.out.println(member);
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

		sql.append("select MEM_ID, MEM_PASS, MEM_NAME, MEM_REGNO1, MEM_REGNO2,"
				+ "to_char(MEM_BIR, 'YYYY-MM-DD') MEM_BIR, MEM_ZIP, MEM_ADD1, MEM_ADD2, MEM_HOMETEL, MEM_COMTEL, MEM_HP, MEM_MAIL,"
				+ "MEM_JOB, MEM_LIKE, MEM_MEMORIAL, to_char(MEM_MEMORIALDAY, 'YYYY-MM-DD')MEM_MEMORIALDAY, MEM_MILEAGE, MEM_DELETE 		 ");
		sql.append("from member      ");
		sql.append("where mem_id = ?");
		try (
			Connection conn = ConnectionFactory.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
			
			pstmt.setString(1, memId);
			ResultSet rs = pstmt.executeQuery();
			
			
			MemberVO vo = null;
			if (rs.next()) {
				vo = new MemberVO();
				
			}

			// Conection full 구조이기 때문에 로직이 종료된 후에 연결을 종료 시켜주어야 한다
			rs.close();
			return vo;
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
	}

}

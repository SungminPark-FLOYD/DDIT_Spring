package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.or.ddit.db.ConnectionFactory_HikariCP;
import kr.or.ddit.db.ConnectionFactory_JDBC_Ver2;
import kr.or.ddit.db.ConnectionFactory_JDBC_Ver3;
import kr.or.ddit.exception.PersistenceException;
import kr.or.ddit.vo.MemberVO;

class ConnectionPoolingPerformanceCheck {
	static MemberDAO dao;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("beforeClass");
		dao = new MemberDAOImpl();
	}
	
//	@BeforeEach
//	void setUp() {
//		System.out.println("before each");
//	}

	@Test 
	// Connection pooling 사용 이전의 소요시간 1.2s, 0.34s
	// Connection pooling 사용 이후의 소요시간  0.34s, 0.31s
	@DisplayName("Connection pooling 사용 이전")
	void testSelectMemberNonCP() {
//		System.out.println("test case1");
		for(int i = 1; i <= 100; i++) {
			MemberVO member = null;
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
				Connection conn = ConnectionFactory_JDBC_Ver2.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				) {
				
				pstmt.setString(1, "a001");
				ResultSet rs = pstmt.executeQuery();
				
				
				
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
			} catch (SQLException e) {
				throw new PersistenceException(e);
			}
			System.out.printf("%s, %d번째 \n",member.getMemName(), i);
		}
	}
	@Test 
	// Connection pooling 사용 이전의 소요시간 1.2s, 0.34s
	// Connection pooling 사용 이후의 소요시간  0.34s, 0.31s
	@DisplayName("Connection pooling 사용 이후")
	void testSelectMemberCP() {
//		System.out.println("test case1");
		for(int i = 1; i <= 1000; i++) {
			MemberVO member = null;
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
					Connection conn = ConnectionFactory_HikariCP.getConnection(); 
					PreparedStatement pstmt = conn.prepareStatement(sql.toString());
					) {
				
				pstmt.setString(1, "a001");
				ResultSet rs = pstmt.executeQuery();
				
				
				
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
			} catch (SQLException e) {
				throw new PersistenceException(e);
			}
			System.out.printf("%s, %d번째 \n",member.getMemName(), i);
		}
	}
	
	@Test
	void testDummy() {
		System.out.println("test case2");
	}

}

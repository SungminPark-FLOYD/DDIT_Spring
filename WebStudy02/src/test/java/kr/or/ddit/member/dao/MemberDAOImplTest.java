package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.exception.PersistenceException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

class MemberDAOImplTest {
	private MemberDAO dao = new MemberDAOImpl();
	@Test
	void testInsertMember() {
		MemberVO member = new MemberVO();
		assertThrows(PersistenceException.class, ()->dao.insertMember(member));
		member.setMemId("a003");
		member.setMemPass("java");
		member.setMemName("테스터");
		member.setMemZip("00000");
		member.setMemAdd1("대전 오류");
		member.setMemAdd2("대덕인재개발원");
		member.setMemMail("aa@naver.com");
		member.setMemBir("2024-01-01");
		int rowcnt = dao.insertMember(member);
		assertEquals(1, rowcnt);
	}

	@Test
	void testSelectMemberList() {
		List<MemberVO> list = dao.selectMemberList();
		assertNotNull(list);
		assertNotEquals(0,list.size());
		System.out.println(list.size());
		
	}

	@Test
	void testSelectMember() {
		MemberVO vo = dao.selectMember("a001");
		assertNotNull(vo);
		System.out.println(vo);
		
		String memId = "asdasd' OR '1' = '1";
		assertNull(dao.selectMember(memId));
	}
	
	@Test
	void testUpdateMember() {
		MemberVO member = new MemberVO();
		assertThrows(PersistenceException.class, ()->dao.update(member));
		member.setMemId("a003");
		member.setMemPass("java");
		member.setMemName("업데이트");
		member.setMemZip("00000");
		member.setMemAdd1("대전 오류");
		member.setMemAdd2("대덕인재개발원");
		member.setMemMail("aa@naver.com");
		member.setMemBir("2024-01-01");
		int rowcnt = dao.update(member);
		assertEquals(1, rowcnt);
	}
	
	@Test
	void testDeleteMember() {
		assertThrows(PersistenceException.class, ()->dao.delete("123"));
		int rowcnt = dao.delete("a003");
		assertEquals(1, rowcnt);
	}

}

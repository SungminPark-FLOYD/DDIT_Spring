package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.bts.member.dao.MemberDAO;
import kr.or.ddit.bts.member.dao.MemberDAOImpl;
import kr.or.ddit.exception.PersistenceException;
import kr.or.ddit.vo.MemberVO;

class MemberDAOImplTest {
	private MemberDAO dao = new MemberDAOImpl();
	@Test
	void testInsertMember() {
		MemberVO member = new MemberVO();
		assertThrows(PersistenceException.class, () -> dao.insertMember(member));

		member.setMemId("a003");
		member.setMemPass("java");
		member.setMemName("테스트");
		member.setMemZip("0000");
		member.setMemAdd1("asdasd");
		member.setMemAdd2("asdasd");
		member.setMemAdd2("asdasfasf");
		
		int cnt = dao.insertMember(member);
		assertEquals(1, cnt);
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

}

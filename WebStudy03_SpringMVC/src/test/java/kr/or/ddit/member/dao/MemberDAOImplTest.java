package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.exceptions.PersistenceException;

import kr.or.ddit.AbstractRootContextTest;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class MemberDAOImplTest extends AbstractRootContextTest{
	
	@Autowired
	private MemberDAO dao;
	@Test
	void testInsertMember() {
		MemberVO member = new MemberVO();
		assertThrows(PersistenceException.class, ()->dao.insertMember(member));
		member.setMemId("a007");
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
		log.info("list : {}", list);
		
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
		member.setMemId("a001");
		assertThrows(PersistenceException.class, ()->dao.update(member));
		member.setMemId("a008");
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
		assertNotEquals(1, dao.delete("1231"));
		int rowcnt = dao.delete("a007");
		assertEquals(1, rowcnt);
	}

}

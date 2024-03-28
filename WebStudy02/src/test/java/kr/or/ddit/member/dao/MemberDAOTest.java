package kr.or.ddit.member.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import kr.or.ddit.vo.MemberVO;

class MemberDAOTest {
	private MemberDAO dao = new MemberDAOImpl();
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void selectListTest() {
		List<MemberVO> list = dao.selectMemberList();
		for(MemberVO vo : list) {
			System.out.println(vo);
		}
	}
	
	@Test
	void selectTest() {
		MemberVO vo = dao.selectMember("a001");
		System.out.println(vo);
	}
	

}

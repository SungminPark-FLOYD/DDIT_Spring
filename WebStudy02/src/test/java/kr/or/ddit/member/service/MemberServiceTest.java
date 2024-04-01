package kr.or.ddit.member.service;

import static org.junit.jupiter.api.Assertions.*;

import java.security.Provider.Service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PkNotFoundException;
import kr.or.ddit.vo.MemberVO;

class MemberServiceTest {

	MemberService service = new MemberServiceImpl();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testCreateMember() {
		fail("Not yet implemented");
	}

	@Test
	void testRetrieveMemberList() {
		fail("Not yet implemented");
	}

	@Test
	void testRetrieveMember() {
		fail("Not yet implemented");
	}

	@Test
	void testModifyMember() {
		final MemberVO member = new MemberVO();
		member.setMemId("asd");

		assertThrows(PkNotFoundException.class, () -> {
			service.modifyMember(member);
		});
		
		member.setMemId("a001");
		member.setMemPass("asdasdd");
		assertEquals(ServiceResult.INVALIDPASSWORD, service.modifyMember(member));
		MemberVO memberOk = service.retrieveMember(member.getMemId());
		assertEquals(ServiceResult.OK, service.modifyMember(memberOk));
	}

	@Test
	void testRemoveMember() {
		MemberVO member = new MemberVO();
		member.setMemId("asd");
		assertThrows(PkNotFoundException.class, () -> {
			service.removeMember(member);
		});
		
		member.setMemId("a001");
		member.setMemPass("asdasdd");
		assertEquals(ServiceResult.INVALIDPASSWORD, service.removeMember(member));
		MemberVO memberOk = service.retrieveMember(member.getMemId());
		assertEquals(ServiceResult.OK, service.removeMember(memberOk));
	}

}

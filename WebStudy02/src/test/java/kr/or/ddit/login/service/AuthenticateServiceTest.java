package kr.or.ddit.login.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import kr.or.ddit.login.AuthenticateException;
import kr.or.ddit.login.BadCredentialException;
import kr.or.ddit.login.UserNotFoundException;
import kr.or.ddit.vo.MemberVO;

class AuthenticateServiceTest {
	private AuthenticateService service = new AuthenticateServiceImpl();

	@Test
	@DisplayName("존재하지 않는 사용자")
	void test() {
		final MemberVO member = new MemberVO();
		member.setMemId("asd");
		//익명객체안에 있는 메서드가 하나밖에없으면 functional interface라고 한다 -> 익명함수 사용가능
		assertThrows(UserNotFoundException.class, new Executable() {		
			@Override
			public void execute() throws Throwable {
				service.authenticate(member);
			}
		}); 
		
	}

	@Test
	@DisplayName("비밀번호 인증 실패")
	void test1() {
		final MemberVO member = new MemberVO();		
		member.setMemId("a001");
		member.setMemPass("asdasd");
		assertThrows(BadCredentialException.class, () -> service.authenticate(member)); 
	}
	
	@Test
	@DisplayName("검증 성공")
	void test2() {
		final MemberVO member = new MemberVO();		
		member.setMemId("a001");
		member.setMemPass("asdfasdf");
		assertNotNull(service.authenticate(member));
	}


}

package kr.or.ddit.crypto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class PasswordEncoderTest {

	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();


	@Test
	@DisplayName("암호화 테스트")
	void testEncrypting() {
		String plain = "java";
		
		//인코딩과 인크립팅 둘다 지원
		String encoded = encoder.encode(plain);
		
		log.info("encoded = {} ",encoded);
	}
	
	@Test
	@DisplayName("평문 비교 테스트")
	void testMathes() {
		String savedPass = "{bcrypt}$2a$10$Cs65ad8iEz.gne5jaOCo7elb/IhbbCpLvEJX4O74vfr5.8Isu9v0q";
		String inputPass = "java";
		
		log.info("인증 성공 여부 : {}", encoder.matches(inputPass, savedPass));
		
	}

}

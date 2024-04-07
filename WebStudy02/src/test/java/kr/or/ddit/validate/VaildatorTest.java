package kr.or.ddit.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import org.apache.ibatis.annotations.Delete;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class VaildatorTest {
	static Validator validator;
	
	@BeforeAll
	static void setUpBeforeClass() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	void testMemberVO() {
		MemberVO target = new MemberVO();
//		target.setMemId("a001");
//		target.setMemPass("14");
//		target.setMemName("이름");
//		target.setMemZip("12345");
//		target.setMemAdd1("주소1");
//		target.setMemAdd2("주소2");
//		target.setMemMail("asd@asd.com");
//		target.setMemRegno1("123456");
//		target.setMemRegno2("1234567");
		Set<ConstraintViolation<MemberVO>> violations = validator.validate(target, InsertGroup.class, UpdateGroup.class, DeleteGroup.class);
		for(ConstraintViolation<MemberVO> single : violations) {
			String propName = single.getPropertyPath().toString();
			String message = single.getMessage();
			log.info("{}; {}", propName, message);
		}
		boolean valid = violations.isEmpty();
		log.info("검증 통과 여부 : {}", valid);
		log.info("검증에 통과하지 못한 프로퍼티 개수 : {}", violations.size());
	}
}

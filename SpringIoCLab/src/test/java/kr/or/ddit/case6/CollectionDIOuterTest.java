package kr.or.ddit.case6;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitConfig(locations = "classpath:/kr/or/ddit/case6/conf/Case6-Context.xml")
class CollectionDIOuterTest {
	
	@Autowired
	CollectionDIOuter outer1;
	@Resource(name = "outer2")
	CollectionDIOuter outer2;
	
	@Test
	void test() {
		log.info("outer1 : {}", outer1);
		log.info("outer2 : {}", outer2);
	}

}

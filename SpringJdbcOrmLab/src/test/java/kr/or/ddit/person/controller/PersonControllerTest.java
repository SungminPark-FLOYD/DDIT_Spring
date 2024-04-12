package kr.or.ddit.person.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.AbstractModelContextTest;

class PersonControllerTest extends AbstractModelContextTest{
	
	@Autowired
	PersonController controller;
	
	@Test
	void testPersonListToResponse() {
		controller.personListToResponse();
	}

}

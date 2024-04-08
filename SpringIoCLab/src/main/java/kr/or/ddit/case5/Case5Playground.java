package kr.or.ddit.case5;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.case5.person.controller.PersonController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Case5Playground {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new GenericXmlApplicationContext("classpath:/kr/or/ddit/case5/conf/Case5-Context.xml");
		PersonController controller = context.getBean(PersonController.class);
		
		log.info("list : {}",controller.personListToResponse());
	}
}

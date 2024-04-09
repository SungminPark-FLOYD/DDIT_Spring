package kr.or.ddit.case5;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.case5.person.controller.PersonController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Case5Playground {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new GenericXmlApplicationContext("classpath:/kr/or/ddit/case8/conf/Person-Context.xml");
		//돌고있는 쓰레드가 하나도 없으면 자동 종료 -> 컨테이너가 사용하는 자원들 정리
		context.registerShutdownHook();
		
		PersonController controller = context.getBean(PersonController.class);
		
		log.info("list : {}",controller.personListToResponse());
	}
}

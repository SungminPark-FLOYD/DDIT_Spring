package kr.or.ddit.case6;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Case6Playground {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				new GenericXmlApplicationContext("classpath:/kr/or/ddit/case6/conf/Case6-Context.xml");
		context.registerShutdownHook();
		
		CollectionDIOuter cd1 = context.getBean("outer1",CollectionDIOuter.class);
		CollectionDIOuter cd2 = context.getBean("outer2",CollectionDIOuter.class);
		
		log.info("cd : {}", cd1);
		log.info("cd : {}", cd2);
		
		
	}
}

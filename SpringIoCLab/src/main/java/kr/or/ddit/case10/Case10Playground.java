package kr.or.ddit.case10;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.case10.conf.RootContext;
import kr.or.ddit.case5.person.controller.PersonController;
import kr.or.ddit.vo.PersonVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Case10Playground {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(RootContext.class);
		
		ConfigurableApplicationContext child = 
				new ClassPathXmlApplicationContext(new String[] {"/kr/or/ddit/case10/conf/child-context.xml"}, context);
		
		PersonController person = child.getBean(PersonController.class);
		
		List<PersonVo> pList = person.personListToResponse();
		log.info("personList : {}" , pList);
	}
}

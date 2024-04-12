package kr.or.ddit.case8;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.case5.person.controller.PersonController;
import kr.or.ddit.vo.PersonVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Case8Playground {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				new ClassPathXmlApplicationContext("/kr/or/ddit/case8/conf/hierarchy/root-context.xml");
		
		ConfigurableApplicationContext child =
				new ClassPathXmlApplicationContext(new String[] {"/kr/or/ddit/case8/conf/hierarchy/child-context.xml"} , context);
		
		PersonController controller = child.getBean(PersonController.class);
		
		List<PersonVo> personList = controller.personListToResponse();
		
		log.info("list : {}", personList);
		
	}
}

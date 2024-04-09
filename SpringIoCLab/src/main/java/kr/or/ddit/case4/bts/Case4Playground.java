package kr.or.ddit.case4.bts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;

import kr.or.ddit.case4.bts.service.BtsService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Controller
public class Case4Playground {
	@Autowired
	private BtsService service;
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				new GenericXmlApplicationContext("classpath:/kr/or/ddit/case8/conf/Bts-Context.xml");
		context.registerShutdownHook();
		
		Case4Playground controller = context.getBean(Case4Playground.class);
		
		log.info("bts : {}",controller.service.readBtsList());
	}
}

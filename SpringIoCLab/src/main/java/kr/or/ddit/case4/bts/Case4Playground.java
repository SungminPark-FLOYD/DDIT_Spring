package kr.or.ddit.case4.bts;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.case4.bts.service.BtsService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public class Case4Playground {
	private BtsService service;
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				new GenericXmlApplicationContext("classpath:/kr/or/ddit/case4/conf/Case4-Context.xml");
		context.registerShutdownHook();
		
		Case4Playground controller = context.getBean(Case4Playground.class);
		
		log.info("bts : {}",controller.service.readBtsList());
	}
}

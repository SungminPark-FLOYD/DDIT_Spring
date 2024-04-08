package kr.or.ddit.case2;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Case2Playground {
	public static void main(String[] args) {
		//컨테이너가 더이상 필요없으면 종료시키는 기능을 가지고있다
		ConfigurableApplicationContext context =
				new ClassPathXmlApplicationContext("/kr/or/ddit/case2/conf/Case2-Context.xml");
		
		Foo foo = context.getBean("foo1", Foo.class);
		log.info("foo : {}", foo);
	}
}

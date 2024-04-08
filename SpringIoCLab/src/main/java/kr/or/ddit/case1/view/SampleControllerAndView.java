package kr.or.ddit.case1.view;

import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.case1.dao.SampleDAO;
import kr.or.ddit.case1.dao.SampleDAOImpl_MariaDB;
import kr.or.ddit.case1.service.SampleService;
import kr.or.ddit.case1.service.SampleServiceImpl;
import kr.or.ddit.vo.SampleVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleControllerAndView {
	public static void main(String[] args) {
//		SampleDAO dao = new SampleDAOImpl_MariaDB();
//		SampleService service = new SampleServiceImpl(dao);
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("/kr/or/ddit/case1/conf/Sample-Context.xml");
		//xml에 설정해둔 id값 넘겨주기
		SampleService service = context.getBean(SampleService.class);	
		List<SampleVO> list = service.readSampleList();
		log.info("list : {} " , list);
		SampleVO sample = service.readSample("T001");
		log.info("sample : {} " , sample);
	}
}

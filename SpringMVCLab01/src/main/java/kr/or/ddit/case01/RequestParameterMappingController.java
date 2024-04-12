package kr.or.ddit.case01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case01/mapping4")
public class RequestParameterMappingController {
	
	@RequestMapping(params = "who")
	public void handler1() {
		log.info("who 파라미터가 있는 요청의 handler");
	}
	
	@RequestMapping
	public void handler2() {
		log.info("who 파라미터가 없는 요청의 handler");
	}
}

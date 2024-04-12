package kr.or.ddit.case01;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case01/mapping3")
public class RequestHeaderMappingController {

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public void handler3() {
		log.info("응답 컨텐츠로 json을 요청한 경우의 handler");
	}
	@GetMapping
	public void handler4() {
		log.info("응답 컨텐츠로 json이외의 요청한 경우의 handler");
	}
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void handler1() {
		log.info("요청이 바디에 json이 전송된 경우의 handler");
	}
	@PostMapping
	public void handler2() {
		log.info("요청이 바디에 json이외의 컨텐트가 전송된 경우의 handler");
	}
	
}

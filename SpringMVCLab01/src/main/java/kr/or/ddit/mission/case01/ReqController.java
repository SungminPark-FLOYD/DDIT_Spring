package kr.or.ddit.mission.case01;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mission/case01")
public class ReqController {
	
//	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
	public void jsonGet() {
		log.info("json GET요청 처리");
	}
	
	@PostMapping
	public void allPost() {
		log.info("Post요청 전부 처리");
	}

}

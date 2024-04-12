package kr.or.ddit.mission.case02;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mission/case02")
public class MissionCase02 {
	
	@GetMapping
	public void reqHeader(@RequestHeader(name = "user-agent") String userAgent, @CookieValue Optional<Integer> myCookie) {
		log.info("userAgent : {}" , userAgent);
		log.info("myCookie : {}", myCookie.orElse(1000));
	}
	
}

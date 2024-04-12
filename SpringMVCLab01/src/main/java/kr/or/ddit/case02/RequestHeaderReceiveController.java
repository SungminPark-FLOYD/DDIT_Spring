package kr.or.ddit.case02;

import java.util.Enumeration;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case02")
public class RequestHeaderReceiveController {
	@RequestMapping("header6")
	//header이름과 변수명이 같으면 생략가능
	public void handler6(@RequestHeader Optional<String> optHeader) {
		log.info("optHeader : {}", optHeader.orElse("default"));
	}
	
	@RequestMapping("header5")
	//header 이름 , 필수여부, 기본값 정하기
	public void handler5(@RequestHeader(name="myHeader", required = false, defaultValue = "default") String myHeader) {
		log.info("myHeader : {}", myHeader.toString());
	}
	@RequestMapping("header4")
	public void handler4(@RequestHeader(name="accept", required = true) String accept) {
		log.info("appect : {}", accept);
	}
	
	@RequestMapping("header3")
	public void handler3(@RequestHeader MultiValueMap<String, String> headers) {
		headers.forEach((k,v) -> log.info("{} : {}",k,v));
	}
	
	@RequestMapping("header2")
	public void handler2(@RequestHeader Map<String, Object> headers) {
		headers.forEach((k,v) -> log.info("{} : {}",k,v));
	}
	
	@RequestMapping("header1")
	public void handler1(HttpServletRequest req) {
		Enumeration<String> names = req.getHeaderNames();
		while(names.hasMoreElements()) {
			String headerName = (String)names.nextElement();
			String headerValue = req.getHeader(headerName);
			log.info("{} : {}", headerName, headerValue);
		}
	}
}

package kr.or.ddit.case02;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case02")
public class CookieReceiveController {
	@RequestMapping("cookie2")
	public void handler2(@CookieValue String newCookie) {
		log.info("newCookie:{}",newCookie);
	}
	@RequestMapping("cookie1")
	public void handler1(HttpServletRequest req) throws UnsupportedEncodingException {
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for(Cookie single : cookies) {
				String value = URLDecoder.decode(single.getValue(), "UTF-8");
				log.info("{} : {}", single.getName(), value);
			}
		}
	}
}

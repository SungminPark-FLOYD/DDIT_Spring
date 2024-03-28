package kr.or.ddit.utils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * List<Object> dummyList = new ArrayList<>();
 * int num = 34;
 * dummyList.add(new Integer(number));
 *
 */
public class CookieMapRequestWrapper {
	
	private HttpServletRequest adaptee;
	private Map<String, Cookie> cookieMap;

	public CookieMapRequestWrapper(HttpServletRequest adaptee) {
		super();
		this.adaptee = adaptee;
		cookieMap = new LinkedHashMap<String, Cookie>();
		Cookie[] cookies = adaptee.getCookies();
		if(cookies != null) {
			for(Cookie single : cookies) {
				cookieMap.put(single.getName(), single);
			}
		}
	}
	
	public boolean hasCookie(String cookieName) {
		return cookieMap.containsKey(cookieName);
	}
	
	public Cookie getCookie(String cookieName) {
		return cookieMap.get(cookieName);
	}
	
	public String getCookieValue(String cookieName) {
		if(hasCookie(cookieName)) {
			Cookie finded = getCookie(cookieName);
			try {
				return URLDecoder.decode(finded.getValue(), "UTF-8");
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}
		}else {
			return null;
		}
	}
	 
}

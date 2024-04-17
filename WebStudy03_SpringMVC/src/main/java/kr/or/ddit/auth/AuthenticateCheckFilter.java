package kr.or.ddit.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;


/**
 * 보호자원에 대한 요청이 발생한 경우, 
 * 해당 요청의 사용자가 인증된 사용자인지 여부를 확인하기 위한 필터
 * 
 *  1. 인증 판단 기준 : userPrincipal 존재 여부로 판단
 *  2. 보호자원인지 여부 판단 기준
 *
 *	보호자원인 경우
 *		인증 여 : 통과
 *		인증 부 : 로그인폼으로 연결
 * 비보호자원인 경우 : 통과
 */
@Slf4j
public class AuthenticateCheckFilter implements Filter{
	
	@Resource(name = "securedResources")
	private Map<String, String[]> securedResources;	
	@Resource(name = "securedProps")
	private Properties securedProps;
	
	public void init() {
		
		Enumeration<Object> keys = securedProps.keys();
		while(keys.hasMoreElements()) {
			String uri = (String)keys.nextElement();
			String[] values = securedProps.getProperty(uri).split(",");
			Arrays.sort(values);
			securedResources.put(uri, values);
			log.info("{} : {}", uri, values);
		}
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		boolean pass = true;
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length());
		if(securedResources.containsKey(uri)) {
			pass = req.getUserPrincipal() != null;
		}else {
			pass = true;
		}
		log.info("request uri : {}", uri);
		
		
		
		if(pass) {
			chain.doFilter(request, response);	
		}else {
			resp.sendRedirect(req.getContextPath() + "/login/loginForm.jsp");
		}
				
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}

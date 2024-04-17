package kr.or.ddit.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import kr.or.ddit.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;


/**
 * session scope에 "authMember"라는 속성이 존재하면,
 * 현재 request에 Wrapper를 만들고, 
 * 해당 wrapper통해 UserPrincipal을 관리하기 위한 필터
 */
@Slf4j
public class GeneratePrincipalFilter implements Filter{

	@Autowired
	private MemberService service;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{} 초기화 되었음.", this.getClass().getSimpleName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);
		if(session != null) {
			Object authMember = session.getAttribute("authMember");
			if(authMember != null) {
				UserPrincipalRequestWrapper wrapper = new UserPrincipalRequestWrapper(req);
				chain.doFilter(wrapper, response);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		log.info("{} 소멸 되었음.", this.getClass().getSimpleName());
	}

}

package kr.or.ddit.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.vo.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * 보호자원에 대한 요청이 발생한 경우, 
 * 인증된 사용자의 롤을 확인하고 
 * 해당 보호자원에 설정된 인가정보에 일치하는 여부를 확인
 * 인가된 사용자 : 통과
 * 비인가 사용자 : 403(forbidden)
 */

@Slf4j
public class AuthorizationCheckFilter implements Filter{
	@Resource(name = "securedResources")
	private Map<String, String[]> securedResources;	
	
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
		MemberVOWrapper principal = null;
		//보호자원이면
		if(securedResources.containsKey(uri)) {
			principal = (MemberVOWrapper) req.getUserPrincipal();
			String userRole = principal.getRealUser().getMemRole();
			String[] roles = securedResources.get(uri);
			
			//찾으면 인덱스 못찾으면 음수 반환(정렬 먼저 해야함)
			pass = Arrays.binarySearch(roles, userRole) >= 0;
		}else {
			pass= true;
		}
		
		if(pass) {
			chain.doFilter(request, response);
		}else {
			log.warn("{} 에 대한 요청이 권한이 없는 {} 사용자로 부터 발생함",  uri, principal.getName());
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}

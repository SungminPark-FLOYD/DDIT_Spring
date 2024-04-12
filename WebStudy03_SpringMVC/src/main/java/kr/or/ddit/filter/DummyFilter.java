package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DummyFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("================before : 요청 필터링================");
		chain.doFilter(request, response);
		log.info("==============after==================");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}

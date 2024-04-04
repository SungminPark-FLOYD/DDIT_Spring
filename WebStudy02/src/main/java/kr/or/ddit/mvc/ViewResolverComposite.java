package kr.or.ddit.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ViewResolverComposite implements ViewResolver {
	
	private List<ViewResolver> viewResolvers;
	{
		ViewResolver CNVR = new ContentNegotiatingViewResolver();
		
		ViewResolver IRVR = new InternalResourceViewResolver();
		IRVR.setPrefix("/WEB-INF/views/");
		IRVR.setSuffix(".jsp");
		viewResolvers = new ArrayList<ViewResolver>();
		viewResolvers.add(CNVR);
		
		//항상 가장 마지막에 동작해야 함.
		viewResolvers.add(IRVR);
	}

	@Override
	public void resolveView(String viewName, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//모든 컨트롤러에 다 적용시킬 수 있다
		if(viewName.startsWith("redirect:")) {
			String location = viewName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		}else if(viewName.startsWith("forwrd:")) {
			String path = viewName.substring("forwrd:".length());
			req.getRequestDispatcher(path).forward(req, resp);
		}
		else {
			for(ViewResolver single : viewResolvers) {
				try {
					single.resolveView(viewName, req, resp);
					log.info("{} 이 {} 을 해결했음", single.getClass().getSimpleName(), viewName);
					break;
				}catch (CannotResolveViewNameException e) {
					log.warn("{} 이 {} 을 해결하지 못했음", single.getClass().getSimpleName(), viewName);
					continue;
				}
			}
		}

	}

}

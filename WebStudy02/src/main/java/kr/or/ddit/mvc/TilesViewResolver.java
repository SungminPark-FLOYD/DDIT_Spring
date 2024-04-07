package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TilesViewResolver implements ViewResolver {
	private String prefix = "/";
	private String suffix = ".tiles";
	
	@Override
	public void resolveView(String viewName, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String path = prefix + viewName + suffix;
		try {
			req.getRequestDispatcher(path).forward(req, resp);
		}catch (Exception e) {
			throw new CannotResolveViewNameException(e);
		}
	}

}

package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * response content-type을 결정하기 위한 컨텐츠 협상을 처리할 전략
 */
public class ContentNegotiatingViewResolver implements ViewResolver {

	@Override
	public void resolveView(String viewName, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String accept = req.getHeader("accept");
		if(accept.contains("json")) {
			req.getRequestDispatcher("/jsonView.do").forward(req, resp);
		}else {
			throw new CannotResolveViewNameException(String.format("%s를 해결할 수 없음. 이건 JSON용이 아니야", viewName));
		}
	}

}

package kr.or.ddit.commons.views;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/jsonView.do")
public class JsonView extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		marshalling(req, resp);
	}
	
	private void marshalling(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json;charset=utf-8");
		Enumeration<String> attrNames = req.getAttributeNames();
		Map<String, Object> targetMap = new HashMap<String, Object>();
		while (attrNames.hasMoreElements()) {
			String name = (String) attrNames.nextElement();
			Object value = req.getAttribute(name);
			targetMap.put(name, value);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		try(
			PrintWriter out = resp.getWriter();
		){
			mapper.writeValue(out, targetMap);
		}		
	}
}

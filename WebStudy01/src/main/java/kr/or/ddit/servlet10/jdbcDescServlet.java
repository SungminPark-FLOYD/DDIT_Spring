package kr.or.ddit.servlet10;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.or.ddit.exception.PersistenceException;
import kr.or.ddit.servlet10.service.PropertyService;
import kr.or.ddit.servlet10.service.PropertyServiceImpl;
import kr.or.ddit.utils.NamingUtils;
import kr.or.ddit.vo.DbVO;

@WebServlet("/15/jdbcDesc.do")
public class jdbcDescServlet extends HttpServlet {
	private PropertyService service = new PropertyServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> pareamMap = new HashMap<String, Object>();
		service.readProperties(pareamMap);
		
		for(Entry<String, Object> enc : pareamMap.entrySet()) {
			req.setAttribute(enc.getKey(), enc.getValue());
		}
		
		String accept = req.getHeader("accept");
		String viewName = null;
		if(accept.contains("json")) {
			//marshalling(req, resp);
			//뷰네임 바꾸기
			viewName = "/jsonView.do";
		}else {
			viewName = "/WEB-INF/views/15/jdbcDesc.jsp";
		}
		req.getRequestDispatcher(viewName).forward(req, resp);

		
	}
}

package kr.or.ddit.servlet09;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/case2/fileInfo")
public class ServerFileInfo extends HttpServlet{
	private ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		application = getServletContext();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getParameter("path");
		if(StringUtils.isBlank(path)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		
		String realPath = application.getRealPath(path);
		File file = new File(realPath);
		
		//비어있거나 파일이 아니면 잘못된 요청
		if(!file.exists() || file.isDirectory()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		//어떤 파일 클릭했는지 데이터는 path라는 이름으로 받기
		//파일객체 만들어서 json데이터 서비스하고 사이즈까지 보여주기
		long size = file.length();
		req.setAttribute("size", size);

		String viewName = "/jsonView.do";
		req.getRequestDispatcher(viewName).forward(req, resp);
		
	}
	
	
}

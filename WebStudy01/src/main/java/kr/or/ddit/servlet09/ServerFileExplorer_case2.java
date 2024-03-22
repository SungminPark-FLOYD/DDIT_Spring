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
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/case2/serverFile")
public class ServerFileExplorer_case2 extends HttpServlet{
	private ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		application = getServletContext();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("accept");
		// 비어있거나 파라미터가 folder이면 파일빼고 출력
		//파라미터가 
		String type = Optional.ofNullable(req.getParameter("type"))
				.filter(tp -> !tp.isEmpty())
				.orElse("folder");
				
				
		String base = Optional.ofNullable(req.getParameter("base"))
						.filter((bp)-> !bp.isEmpty())
						.orElse("/");
		
		boolean folderFlag = "folder".equals(type);
		boolean fileFlag = "file".equals(type);
		boolean allFlag = "all".equals(type);
		
		List<FileWrapper> wrapperList = new ArrayList<>();	

		for(String path : application.getResourcePaths(base)) {
			String realPath = application.getRealPath(path);
			File tmp = new File(realPath);
			//왼쪽부터 순서대로 연산
			if(allFlag || (folderFlag&&tmp.isDirectory()) || (fileFlag&&tmp.isFile()) ) {
				FileWrapper wrapper = new FileWrapper(tmp, path);
				wrapperList.add(wrapper);
			}
		}

		
		req.setAttribute("wrapperList", wrapperList);
		Collections.sort(wrapperList);
		
		String viewName = null;
		if(accept.contains("json")) {
			//marshalling(req, resp);
			//뷰네임 바꾸기
			viewName = "/jsonView.do";
		}else {
			viewName = "/WEB-INF/views/explorer/fileView_case2.jsp";
		}
		req.getRequestDispatcher(viewName).forward(req, resp);
		
		
	}
	
}

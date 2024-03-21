package kr.or.ddit.servlet09;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/serverFile")
public class ServerFileExplorer extends HttpServlet{
	private ServletContext application;
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		application = getServletContext();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String base = Optional.ofNullable(req.getParameter("base"))
						.filter((bp)-> !bp.isEmpty())
						.orElse("/");
		
		Map<String, File> fileList = new HashMap<String, File>();
		Set<String> set = application.getResourcePaths(base);
		for(String path : set) {
			String realPath = application.getRealPath(path);
			File tmp = new File(realPath);
			fileList.put(path, tmp);
		}
		
		req.setAttribute("fileList", fileList);
		String viewName = "/WEB-INF/views/explorer/fileView.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}

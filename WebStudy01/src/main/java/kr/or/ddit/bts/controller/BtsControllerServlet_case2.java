package kr.or.ddit.bts.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.bts.service.BtsService;
import kr.or.ddit.bts.service.BtsServiceImpl;
import kr.or.ddit.exception.ResponseStatusException;
import kr.or.ddit.vo.BtsVO;

@WebServlet("/case2/bts")
public class BtsControllerServlet_case2 extends HttpServlet{
	private ServletContext application;
	private BtsService service = new BtsServiceImpl();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		application = getServletContext();
		ArrayList<BtsVO> btsList =  (ArrayList<BtsVO>) service.readBtsList();		
		Collections.sort(btsList, new BtsVO().reversed());
		req.setAttribute("btsList", btsList);
		String viewName = "/WEB-INF/views/bts/btsForm_case2.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		try {
			String btsKey = Optional.ofNullable(req.getParameter("member")) 
					.filter(tp->!tp.isEmpty())
					.orElseThrow(()->new ResponseStatusException(400, "필수파라미터 누락"));
			BtsVO bts = service.readBts(btsKey);
			
			String content = String.format("/WEB-INF/views/%s.jsp",bts.getPath());
			req.setAttribute("content", content);
			req.setAttribute("bts", bts);
			String path = "/WEB-INF/views/bts/base.jsp";
			req.getRequestDispatcher(path).forward(req,resp);
		}catch(ResponseStatusException e) {
			resp.sendError(e.getStatus(), e.getMessage()); 
		}
	}
}

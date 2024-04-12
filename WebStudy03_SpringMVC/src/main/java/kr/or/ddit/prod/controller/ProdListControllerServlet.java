package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@WebServlet("/prod/prodList.do")
public class ProdListControllerServlet extends HttpServlet{
	private final ProdService service;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ProdVO> prodList = service.retrieveProdList();
		req.setAttribute("prodList", prodList);
		
		String viewName = "prod/prodList";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}

}

package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
@WebServlet("/prod/prodDetail.do")
public class ProdDetailControllerServlet extends HttpServlet{
	private final ProdService service;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String prodId = req.getParameter("who");
		
		if(StringUtils.isBlank(prodId)) {
			resp.sendError(400);
			return;
		}
		
		ProdVO prod = service.retrieveProd(prodId);
		req.setAttribute("prod", prod);
			
		String viewName = "prod/prodDetail";
		
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}

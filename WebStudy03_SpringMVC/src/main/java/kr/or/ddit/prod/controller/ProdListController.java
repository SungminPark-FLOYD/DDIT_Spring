package kr.or.ddit.prod.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ProdListController{
	private final ProdService service;

	@RequestMapping("/prod/prodList.do")
	protected String prodList(Model model) {
		List<ProdVO> prodList = service.retrieveProdList();
		model.addAttribute("prodList", prodList);
		
		return "prod/prodList";
		
	}

}

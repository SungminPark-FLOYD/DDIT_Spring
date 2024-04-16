package kr.or.ddit.prod.controller;

import java.io.IOException;


import javax.servlet.http.HttpServlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/prod/prodDetail.do")
public class ProdDetailController {
	private final ProdService service;
	
	@GetMapping
	protected String doGet(@RequestParam String who,Model model) {
	
		ProdVO prod = service.retrieveProd(who);
		model.addAttribute("prod", prod);
			
		return "prod/prodDetail";
		
	}
}

package kr.or.ddit.lprod.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.ddit.lprod.service.OthersService;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import lombok.RequiredArgsConstructor;

/**
 * POJO(Plain Old Java Object)
 * POJO는 객체 지향적인 원리에 충실하면서 환경과 기술에 종속되지 않고 필요에 따라 재활용될 수 있는 방식으로 설계된 오브젝트이다
 *	-> AOP 방법론과 연결된다.
 */
/**
 *	kr.or.ddit.prod 패키지 아래의 모든 컨트롤러를 대상으로 사전 weaving되는 advice 
 *
 */
@RequiredArgsConstructor
@ControllerAdvice(basePackages = "kr.or.ddit.prod")
public class OthersControllerAdvice {
	private final OthersService service;
	
	@ModelAttribute("lprodList")
	public List<LprodVO> addLprodList(Model model) {
		return service.retrieveLprodList();
	}
	
	@ModelAttribute("buyerList")
	public List<BuyerVO> addBuyerList(Model model) {
		return service.retrieveBuyerList();
	}
}

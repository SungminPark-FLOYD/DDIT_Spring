package kr.or.ddit.lprod.controller;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.lprod.service.OthersService;
import kr.or.ddit.lprod.service.OthersServiceImpl;

/**
 * POJO(Plain Old Java Object)
 * POJO는 객체 지향적인 원리에 충실하면서 환경과 기술에 종속되지 않고 필요에 따라 재활용될 수 있는 방식으로 설계된 오브젝트이다
 *	-> AOP 방법론과 연결된다.
 */

public class OthersControllerAdvice {
	private OthersService service = new OthersServiceImpl();
	
	
	public void addLprodList(HttpServletRequest req) {
		req.setAttribute("lprodList", service.retrieveLprodList());
	}
	
	public void addBuyerList(HttpServletRequest req) {
		req.setAttribute("buyerList", service.retrieveBuyerList());
	}
}

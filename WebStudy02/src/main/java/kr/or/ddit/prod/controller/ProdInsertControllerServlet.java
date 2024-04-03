package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodInsert.do")
public class ProdInsertControllerServlet extends HttpServlet{
	ProdService service = new ProdServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "/WEB-INF/views/prod/prodForm.jsp";
		
		//모든 컨트롤러에 다 적용시킬 수 있다
		if(viewName.startsWith("redirect:")) {
			String location = viewName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		}else if(viewName.startsWith("forwrd:")) {
			String path = viewName.substring("forwrd:".length());
			req.getRequestDispatcher(path).forward(req, resp);
		}
		else {
			req.getRequestDispatcher(viewName).forward(req, resp); 
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		ProdVO prod = new ProdVO(); //command Object 
		req.setAttribute("prod", prod);
		Map<String, String[]> paramMap = req.getParameterMap();
		//사용되는 프로퍼티들을 bean에 넣어주는 작업 -> key와 vo의 프로퍼티 이름이 같으면 bean으로 넣어준다
		try {
			BeanUtils.populate(prod, paramMap);
		} catch (Exception e) {
//			throw new RuntimeException(e);
			String insdate = req.getParameter("prodInsdate");
			LocalDate prodInsdate = LocalDate.parse(insdate);
			prod.setProdInsdate(prodInsdate);
		}
		System.out.println(prod);
//		* 2. 검증
		//call by reference 방식
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(prod,errors);
//		 * 3. 로직 사용(model 확보)
		String viewName = null;
		//널인지 아닌지, 컬럼 길이제한이 넘는지 아닌지, date같은경우 날짜 포멧에 맞는지 아닌지
		
		if(errors.isEmpty()) {	
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case FAIL:
				req.setAttribute("message", "서버 오류");
				viewName = "/WEB-INF/views/prod/prodForm.jsp";
				break;
			case OK:
				viewName = "redirect:/prod/prodDetail.do?who="+prod.getProdId();
				break;
			}
			
			// 4. scope를 이용해 model 공유
		}else {
			viewName = "/WEB-INF/views/prod/prodForm.jsp";
		}	

		
		//모든 컨트롤러에 다 적용시킬 수 있다
		if(viewName.startsWith("redirect:")) {
			String location = viewName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		}else if(viewName.startsWith("forwrd:")) {
			String path = viewName.substring("forwrd:".length());
			req.getRequestDispatcher(path).forward(req, resp);
		}
		else {
			req.getRequestDispatcher(viewName).forward(req, resp); 
		}
	}

	private boolean validate(ProdVO prod, Map<String, String> errors) {
		boolean valid = true;
		//필수 컬럼 검증
		if(StringUtils.isBlank(prod.getProdId())) { valid = false; errors.put("prodId", "상품코드 누락");}
		if(StringUtils.isBlank(prod.getProdName())) { valid = false; errors.put("prodName", "상품명 누락");}
		if(StringUtils.isBlank(prod.getProdLgu())) { valid = false; errors.put("prodLgu", "상품분류 누락");}
		if(StringUtils.isBlank(prod.getProdBuyer())) { valid = false; errors.put("prodBuyer", "거래처 누락");}
		if(prod.getProdCost()==null) { valid = false; errors.put("prodCost", "구매가 누락");}
		if(prod.getProdPrice()==null) { valid = false; errors.put("prodPrice", "판매가 누락");}
		if(prod.getProdSale()==null) { valid = false; errors.put("prodSale", "세일가 누락");}
		if(StringUtils.isBlank(prod.getProdOutline())) { valid = false; errors.put("prodOutline", "요약정보 누락");}
		if(StringUtils.isBlank(prod.getProdImg())) { valid = false; errors.put("prodImg", "이미지 누락");}
		if(prod.getProdTotalstock()==null) { valid = false; errors.put("prodTotalstock", "총재고 누락");}
		if(prod.getProdProperstock()==null) { valid = false; errors.put("prodProperstock", "적정재고 누락");}
		return valid;
	}
}

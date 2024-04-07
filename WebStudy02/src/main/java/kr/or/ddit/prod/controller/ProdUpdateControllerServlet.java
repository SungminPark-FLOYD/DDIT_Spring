package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.lprod.controller.OthersControllerAdvice;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodUpdate.do")
public class ProdUpdateControllerServlet extends HttpServlet{
	private ProdService service = new ProdServiceImpl();
	private OthersControllerAdvice advice = new OthersControllerAdvice();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String prodId = req.getParameter("what");
		if(StringUtils.isBlank(prodId)) {
			resp.sendError(400);
			return;
		}
		
		ProdVO prod = service.retrieveProd(prodId);
		req.setAttribute("prod", prod);
		
		String viewName = "prod/prodEdit";		
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		req.setCharacterEncoding("utf-8");
		ProdVO prod = new ProdVO(); //command Object 
		req.setAttribute("prod", prod);
		
		Map<String, String[]> paramMap = req.getParameterMap();
		
		PopulateUtils.populate(prod, paramMap);
		
		System.out.println(prod);
//		* 2. 검증
		//call by reference 방식
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidateUtils.validate(prod,errors, UpdateGroup.class);
//		 * 3. 로직 사용(model 확보)
		String viewName = null;
		//널인지 아닌지, 컬럼 길이제한이 넘는지 아닌지, date같은경우 날짜 포멧에 맞는지 아닌지
		
		if(errors.isEmpty()) {	
			
			ServiceResult result = service.modifyProd(prod);
			switch (result) {
			case FAIL:
				req.setAttribute("message", "서버 오류");
				viewName = "prod/prodForm";
				break;
			case OK:
				viewName = "redirect:/prod/prodDetail.do?who="+prod.getProdId();
				break;
			}
			
			// 4. scope를 이용해 model 공유
		}else {
			//logical view Name
			viewName = "prod/prodForm";
		}	

		
		//모든 컨트롤러에 다 적용시킬 수 있다
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}

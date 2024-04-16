package kr.or.ddit.prod.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.lprod.controller.OthersControllerAdvice;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/prod/prodInsert.do")
public class ProdInsertController {
	public static final String MODELNAME = "newProd";
	final ProdService service;
	@GetMapping
	protected String doGet(Model model){
	
		model.addAttribute(MODELNAME, new ProdVO());
		return "prod/prodForm";			
	}
	
	@PostMapping
	protected String doPost(@Validated(InsertGroup.class)@ModelAttribute(MODELNAME) ProdVO prod, BindingResult errors,Model model){
//		 * 3. 로직 사용(model 확보)
		String viewName = null;
		//널인지 아닌지, 컬럼 길이제한이 넘는지 아닌지, date같은경우 날짜 포멧에 맞는지 아닌지
		
		if(!errors.hasErrors()) {	
			
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case FAIL:				
				model.addAttribute("message", "서버 오류");
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
		return viewName;
	}

}

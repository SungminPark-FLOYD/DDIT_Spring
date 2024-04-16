package kr.or.ddit.case07;

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

import kr.or.ddit.case07.vo.AddressBookVO;

@Controller
@RequestMapping("/case07/handler1")
public class FormDataBindingController {
	
	/**
	 *	form:form 형태의 커스텀 태그 사용
	 *	1. form의 modelAttribute 속성으로 사용할 모델 전달
	 *	2. form:input 커스텀 태그의 path속성이 command object의 프로퍼티로 반영됨
	 *	 
	 */
	@GetMapping
	public String formHandler(Model model) {
		//처음 get요청 발생시
		if(!model.containsAttribute("adrs")) {
			model.addAttribute("adrs", new AddressBookVO());
		}
		return "case07/formView3";
	}
	
	/**
	 * post요청으로 전송된 여러개의 파라미터를 하나의 객체로 바인딩해서 받기 위한 객체 : command object => model attribute로 반영됨
	 * @ModelAttribute를 이용해 command object의 모델명을 변경함
	 * command object 검증단계
	 * 1. @Vaild / @Vaildated를 커맨드 오브젝트 앞에 사용
	 * 	(그룹 힌트를 적용 가능)
	 * 2. 검증 대상이 되는 커맨드 오브젝트 바로 다음에 Errors / BindingResult 파라미터를 통해 받음
	 * @param adrs
	 * @return
	 */
	@PostMapping
	public String process(@Validated @ModelAttribute("adrs") AddressBookVO adrs, BindingResult errors
						, RedirectAttributes redirectAttributes) {
		
		if(!errors.hasErrors()) {
			return null;
		}else {
			redirectAttributes.addFlashAttribute("adrs", adrs);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "adrs", errors);
			return "redirect:/case07/handler1";
		}
	}
}

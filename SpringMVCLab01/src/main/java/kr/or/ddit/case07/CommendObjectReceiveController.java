package kr.or.ddit.case07;

import javax.validation.Valid;
import javax.validation.groups.Default;

import org.springframework.http.ResponseEntity;
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
import kr.or.ddit.case07.vo.BankInfoVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case07")
public class CommendObjectReceiveController {
	
	@GetMapping("commandObject1")
	public String formHandler1(@ModelAttribute("bank") BankInfoVO bank) {
		return "case07/formView1";
	}
	
	//Command Object 이름 정하기 @ModelAttribute
	//@Valid로 검증하기
	//@Validated Grouphint 정해줄때 사용
	@PostMapping("commandObject1")
	public String processHandler1(@Validated(Default.class) @ModelAttribute("bank") BankInfoVO bank, Errors errors) {
		log.info("bank:{}", bank);
		if(errors.hasErrors()) {
			//검증 실패
			return "case07/formView1";
		}else {
			//검증 통과
			return "case07/formResult1";
		}
	}
	
	@GetMapping("commandObject2")
	public String formHandler2(@ModelAttribute("address") AddressBookVO address) {
		return "case07/formView2";
	}
	
	@PostMapping("commandObject2")
	public String processHandler2(@Validated(Default.class) @ModelAttribute("address") AddressBookVO address, Errors errors) {
		log.info("address:{}", address);
		if(errors.hasErrors()) {
			//검증 실패
			return "case07/formView2";
		}else {
			//검증 통과
			return "case07/formResult2";
		}
	}
	@GetMapping("commandObject3")
	public String formHandler3(Model model) {
		if(!model.containsAttribute("address")) {
			model.addAttribute("address", new AddressBookVO());
		}else {
			log.info("검증 실패 후 리다이렉션 : model :{}, errors : {}"
					, model.getAttribute("address"), model.getAttribute(BindingResult.MODEL_KEY_PREFIX + "address"));
		}
		return "case07/formView2";
	}
	
	@PostMapping("commandObject3")
	public String processHandler3(@Validated(Default.class) @ModelAttribute("address") AddressBookVO address, Errors errors
			,RedirectAttributes redirectAttributes) {
		log.info("address:{}", address);
		if(errors.hasErrors()) {
			//검증 실패
			redirectAttributes.addFlashAttribute("address", address);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "address", errors);
			return "redirect:/case07/commandObject3";
		}else {
			//검증 통과
			return "case07/formResult2";
		}
	}
}

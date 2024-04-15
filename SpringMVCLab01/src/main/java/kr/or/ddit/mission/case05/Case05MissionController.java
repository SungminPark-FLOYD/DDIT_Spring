package kr.or.ddit.mission.case05;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mission/case05")
public class Case05MissionController {
	@RequestMapping("step1")
	public String handler1(Model model) {
		model.addAttribute("step1Info", "step1에서 만든 모델");
		return "forward:/mission/case05/step2";
	}
	@RequestMapping("step2")
	public String handler2(@RequestAttribute(required = false)String step1Info, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("step1Info", step1Info);
		redirectAttributes.addFlashAttribute("step2Info", "step2에서 만든 모델");
		return "redirect:/mission/case05/step3";
	}
	@RequestMapping("step3")
	public String handler3(Model model) {
		model.addAttribute("step3Info", "step3에서 만든 모델");		
		return "case05/mission";
	}
}

package kr.or.ddit.mission;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mission/case06")
public class Case06MissionController {
	
	@GetMapping
	public String getOpt() {
		return "case06/missionForm";
	}
	
//	@PostMapping
//	public ModelAndView postOpt(int leftOp, int rightOp) {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("result", leftOp + rightOp);
//		mav.setViewName("case06/mission");
//		return mav;
//	}
	
	//한 화면에 출력
	@PostMapping
	public String postOpt1(RedirectAttributes redirectAttributes,int leftOp, int rightOp) {
		redirectAttributes.addFlashAttribute("result", leftOp + rightOp);
		redirectAttributes.addFlashAttribute("leftOp", leftOp);
		redirectAttributes.addFlashAttribute("rightOp", rightOp);
		
		return "redirect:/case06/missionForm";
	}
	
	//json 비동기 요청처리
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public void process(int leftOp, int rightOp, Model model) {
		model.addAttribute("result", leftOp + rightOp);
	}
}

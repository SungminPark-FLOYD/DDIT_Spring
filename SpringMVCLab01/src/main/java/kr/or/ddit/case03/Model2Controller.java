package kr.or.ddit.case03;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;

//@Controller : 빈에 등록 / handler adepter 가 tracing을 하기위한 조건
@Controller
@RequestMapping("/case03")
public class Model2Controller {
	
	@RequestMapping(value="view4", produces = MediaType.TEXT_HTML_VALUE)
	public String handler5() {
		return "case03/view4";
	}

	@RequestMapping(value="view4", produces = MediaType.APPLICATION_JSON_VALUE)
	public String handler4() {
		return "jsonView";
	}
	
	@RequestMapping(value="view-3" , produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView handler3() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("case03/view3");
		return mav;
	}
	
	@RequestMapping(value="view2",produces = MediaType.TEXT_HTML_VALUE)	//logical view name : case03/view2
	public String handler2() {
		return "case03/view2";
	}
	
	@GetMapping(value="view1", produces = MediaType.TEXT_HTML_VALUE)
	public String handler1() {
		return "case03/view1";
	}
}

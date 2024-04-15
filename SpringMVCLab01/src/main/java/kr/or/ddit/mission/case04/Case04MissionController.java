package kr.or.ddit.mission.case04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.case04.service.DummyService;

@Controller
@RequestMapping("/mission/case04")
public class Case04MissionController {
	@Autowired
	private DummyService service;
	
	@RequestMapping
	private ModelAndView mission1() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("dummy", service.retrieveInfo());
		mav.setViewName("case04/mission");
		return mav;
	}
}

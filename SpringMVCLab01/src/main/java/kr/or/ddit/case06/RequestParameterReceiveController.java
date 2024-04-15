package kr.or.ddit.case06;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case06")
public class RequestParameterReceiveController {
	
	@RequestMapping("param4")
	public String handler4(Optional<Integer> numParam) {
		log.info("numParam : {}", numParam.orElse(1));
		return "jsonView";
	}

	//아무런 어노테이션도없으면 RequestParam을 붙여서 찾는다
	//데이터 타입에따라서 검증을 한번 해준다
	@RequestMapping("param3")
	public String handler3(@RequestParam(defaultValue = "1")int numParam) {
		log.info("numParam : {}", numParam);
		return "jsonView";
	}
	
	//xml 형식으로보내면 500 에러 -> xml은 비어있는 상태를 표현할 수 없기 때문에
	//@RequestParam은 파라미터 하나를 받을때 사용한다.
	@RequestMapping("param2")
	public void handler2(Model model,@RequestParam(required = false, defaultValue = "DEFAULT") String what) {
		log.info("what: {}", what);
		model.addAttribute("what", what);
	}
	
	@GetMapping("param1")
	public String handler1(@RequestParam(required = true) String who, Model model) {
		log.info("who : {}", who);
		model.addAttribute("who", who);
		return "jsonView";
	}
	
	
}

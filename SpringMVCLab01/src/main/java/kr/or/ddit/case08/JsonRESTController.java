package kr.or.ddit.case08;

import java.time.LocalDate;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.jdbc.support.incrementer.H2SequenceMaxValueIncrementer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.case07.vo.BankInfoVO;

@RestController
@RequestMapping("/case08")
public class JsonRESTController {
	
	@RequestMapping("rest2")
	public String handler2() {
		return "JSONVIEW";
	}
	
	@RequestMapping("rest1")
	public BankInfoVO handler1() {
		return bank();
	}
	
	private BankInfoVO bank() {
		BankInfoVO target = new BankInfoVO();
		target.setBankName("하나은행");
		target.setBankNo("11-111-111");
		target.setBankDate(LocalDate.now());
		return target;
	}
}

package kr.or.ddit.case2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.or.ddit.case1.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
@Setter
@Component
public class Foo {
	
	private final Bar bar; //required
	@Autowired
	private Baz baz; //optional
	
	private final SampleService service;
	
}

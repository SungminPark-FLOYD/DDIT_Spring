package kr.or.ddit.designpattern.templatemethodpattern.obj;

public class DerivedClass2 extends TemplateClass{

	@Override
	protected void stepTwo() {
		System.out.println("내맘대로 실행한 2단계");
	}
	
}

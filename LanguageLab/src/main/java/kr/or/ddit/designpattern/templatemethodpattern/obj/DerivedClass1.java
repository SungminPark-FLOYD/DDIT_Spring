package kr.or.ddit.designpattern.templatemethodpattern.obj;

public class DerivedClass1 extends TemplateClass{

	@Override
	protected void stepTwo() {
		System.out.println("파생 클래스 1번에서 실행된 2단계");
	}

}

package kr.or.ddit.designpattern.adapterpattern.obj;

public class OtherConcrete implements Target{

	@Override
	public void request() {
		System.out.println("other concrete 실행");
	}

}

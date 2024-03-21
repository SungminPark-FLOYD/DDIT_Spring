package kr.or.ddit.designpattern.adapterpattern.obj;

public class Adapter implements Target {

	private Adaptee adaptee;
	
	public Adapter(Adaptee adaptee) {
		super();
		this.adaptee = adaptee;
	}


	@Override
	public void request() {
		adaptee.specificRequest();
	}

}

package kr.or.ddit.case2;

import lombok.ToString;

@ToString
public class Foo {
	private Bar bar; //required
	private Baz baz; //optional
	
	public Foo(Bar bar) {
		super();
		this.bar = bar;
	}
	
	public void setBaz(Baz baz) {
		this.baz = baz;
	}
	
	
	
	
}

package kr.or.ddit.designpattern.adapterpattern;

import kr.or.ddit.designpattern.adapterpattern.obj.Adaptee;
import kr.or.ddit.designpattern.adapterpattern.obj.Adapter;
import kr.or.ddit.designpattern.adapterpattern.obj.Client;
import kr.or.ddit.designpattern.adapterpattern.obj.OtherConcrete;

public class AdapterPatternPlayground {
	public static void main(String[] args) {
		Client client = new Client();
		client.setTarget(new Adapter(new Adaptee()));
		
		client.getTarget().request();
	}
}

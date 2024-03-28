package kr.or.ddit.designpattern.templatemethodpattern;

import kr.or.ddit.designpattern.templatemethodpattern.obj.DerivedClass1;
import kr.or.ddit.designpattern.templatemethodpattern.obj.DerivedClass2;
import kr.or.ddit.designpattern.templatemethodpattern.obj.TemplateClass;

public class TemplateMethodPatternPlayground {
	public static void main(String[] args) {
		TemplateClass[] array = new TemplateClass[]{new  DerivedClass1(), new DerivedClass2()};
		for(TemplateClass tmp : array) {
			tmp.template();
		}
	}
}

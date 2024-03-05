package kr.or.ddit.properties;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import org.junit.jupiter.api.Test;


class ResourceBundleTest {

	@Test
	void test() {
		//확장자 미포함 -> 무조건 properties확장자를 가지고 있어야 한다, 무조건 클래스패스 이후의 경로를 기술해야 한다 "."구분자로 표기
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.Messages", Locale.ENGLISH);
		Set<String> keySet = bundle.keySet();
		Iterator<String> it =  keySet.iterator();
		while (it.hasNext()) {
			String code = (String) it.next();
			String message = bundle.getString(code);
			System.out.printf("%s : %s\n", code, message);
		}
		
	}

}

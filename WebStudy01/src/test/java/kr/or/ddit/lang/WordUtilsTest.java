package kr.or.ddit.lang;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.jupiter.api.Test;

class WordUtilsTest {

	@Test
	void test() {
		String origin = "PROPERTY_NAME";
		String initChar = origin.toLowerCase().substring(0,1);
		String camelCase = initChar + WordUtils.capitalizeFully(origin, '_').replace("_", "").substring(1);
		System.out.println(camelCase);
	}
		

}

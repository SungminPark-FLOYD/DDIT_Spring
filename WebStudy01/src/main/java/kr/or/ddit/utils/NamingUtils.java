package kr.or.ddit.utils;

import org.apache.commons.lang3.text.WordUtils;

public class NamingUtils {
	public static String snakeToCamel(String snake) {
		String initChar = snake.toLowerCase().substring(0,1);
		String camelCase = initChar + WordUtils.capitalizeFully(snake, '_').replace("_", "").substring(1);
		
		return camelCase;
	}

}

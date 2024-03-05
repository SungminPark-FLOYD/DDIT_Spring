package kr.or.ddit.properties;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.junit.jupiter.api.Test;

import kr.or.ddit.servlet01.DescriptionServlet;
import kr.or.ddit.vo.PersonVo;

class PropertiesTest {
	
	@Test
	void testPropertiesHandling() throws IOException{
		Properties props = new Properties();
		try(
			InputStream is = DescriptionServlet.class.getResourceAsStream("/kr/or/ddit/MemberData.properties");
		){
			props.load(is);
			Set<Object> keySet = props.keySet();
			
			
			//set과 list는 size라는 메소드를 가지고 있다 -> 반복이 제한되어 있다
//			for(Object key : keySet) {
//				Object value = props.get(key);
//				System.out.printf("%s = %s\n", key, value);
//			}
//			
//			for(Entry<Object, Object> entry : props.entrySet()) {
//				System.out.printf("%s = %s\n", entry.getKey(), entry.getValue());
//			}
			
			//Enumeration는 size나 length의 메서드를 가지지 않기 때문에 반복할때 하나씩 확인하는 while문을 사용한다
			//Enumeration -> collection view라고 한다 : 실제 집합 객체에 대한 접근 방법을 정의하고 있는 객체
			//row데이터를 객체로 매핑하는 작업
			Enumeration<Object> keys = props.keys();
			while (keys.hasMoreElements()) {
				Object key = (Object) keys.nextElement();
				Object value = props.get(key);
				String[] tokens = value.toString().split("\\|");
				PersonVo person = new PersonVo(key.toString(), tokens[0], tokens[1], tokens[2], tokens[3]);
				System.out.println(person);
				
			}
		}
		
	}
	@Test
	void testProperties() throws IOException {
		Properties props = new Properties();
		System.out.printf("properties size : %d\n", props.size());
		String realPath = DescriptionServlet.class.getResource("/kr/or/ddit/MemberData.properties").getFile();
		File writeFile = new File(realPath);
		//물리경로만 사용해서는 접근할 수 없다
//		File readFile = new File("kr/or/ddit/MemberData.properties");
		try(
			//논리경로로 접근하는 방법 반드시 '/'을 경로의 시작점에 추가해야한다 아니면 상대경로로 인식이 된다
//			DescriptionServlet.class.getResourceAsStream("../MemberData.properties");
			InputStream is = DescriptionServlet.class.getResourceAsStream("/kr/or/ddit/MemberData.properties");
//			FileOutputStream fos = new FileOutputStream(writeFile);
			
		){
			props.load(is);
//			props.clear();
//			props.save(fos, "clear");
			
			System.out.printf("properties size : %d\n", props.size());
		}
		

		
	}
	
	@Test
	void test1() {
		System.out.println("출력");
	}
	@Test
	void test2() {
		System.out.println("출력");
	}

}

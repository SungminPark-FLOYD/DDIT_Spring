package kr.or.ddit.bts.reflect;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReflectionTest {



	@Test
	@DisplayName("reflection 테스트")
	void test() {
		Class instanceType = member.getClass();
		Field[] fields = instanceType.getDeclaredFields();
		for(Field fld : fields) {
			//파라미터 이름과 같은 프로퍼티 찾기
			if(paramName.equals(fld.getName())) {	
				try {
					PropertyDescriptor pd = new PropertyDescriptor(paramName, instanceType);
					//member.set
					pd.getWriteMethod().invoke(member, paramValue);
					//member.get
					Object propValue = pd.getReadMethod().invoke(member);
					System.out.printf("%s : %s\n", pd.getName(), propValue);
					System.out.println("-->" +fld.getName());
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				
				break;
			}
		}
	}

}

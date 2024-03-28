package kr.or.ddit.instance;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import kr.or.ddit.reflection.obj.DabakBungeoppangzip;

/**
 * ClassA instanceA = new ClassA();
 * 
 * 1. ClassLoader 가 ClassA.class 파일을 클래스 패스에서 검색하고, 
 * 	해당 클래스 정보를 메모리에 저장함
 * 2. 1번 단계에서 저장된 클래스 정보를 기반으로 인스턴스를 생성하고, 메모리에 저장함.
 * 3. 2번 단계에서 생성하고 저장된 인스턴스의 참조주소를 instanceA라는 변수에 저장함
 * 
 * 리플렉션이란?
 * 	: 일반적인 객체 생성과정을 거치지 않고 획득한 인스턴스를 대상으로, 
 * 	  해당 인스턴스의 타입(클래스), 상태정보(프로퍼티), 행동정보(메소드)를 찾아가는 작업
 * 	java.reflect 패키지의 API가 지원됨 
 *
 *	modify : 접근제어자 , ??? trasiant
 */
public class ReflectionDesc {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IntrospectionException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException {
		Object instance = DabakBungeoppangzip.buyBungeoppang();
		//가장먼저 클래스의 정보를 가져온다
		Class instanceType = instance.getClass();
		//Method Area의 Static Area에 있는 정보를 꺼내오기
		System.out.printf("%s \n", instanceType.getName());
		System.out.println("=====================상태정보 리플렉션=====================");
		//클래스가 가지고있는 모든 전역변수의 상태정보를 가져오기
		Field[] fields = instanceType.getDeclaredFields();
		
		for(Field fld : fields) {
			String modifiers = Modifier.toString(fld.getModifiers());
			Class fldType = fld.getType();
			String fldName = fld.getName();
			System.out.printf("%s %s %s;\n", modifiers, fldType.getSimpleName(), fldName);
		}
		
		System.out.println("=====================행동정보 리플렉션=====================");
		//클래스가 가지고있는 모든 전역변수의 행동정보를 가져오기
		Method[] methods = instanceType.getDeclaredMethods();
		
		for(Method mtd : methods) {
			//접근제어자 가져오기
			String modifiers = Modifier.toString(mtd.getModifiers());
			//리턴타입받아오기
			Class returnType = mtd.getReturnType();
			//메소드 이름받아오기
			String mtdName = mtd.getName();
			Class[] paramTypes = mtd.getParameterTypes();
			System.out.printf("%s %s %s(%s);\n", modifiers, returnType.getSimpleName(), mtdName, Arrays.toString(paramTypes));
		}
		
		//상태정보와 행동정보가 일치하는 변수들을 프로퍼티라고 한다 메소드 이름을 자바 빈 규약에 따라서 만들지 않으면 field라고 한다
		//java bean 규약에따라서 getter와 setter가 있는 것을 프로퍼티라고한다
		System.out.println("=====================프로퍼티정보 리플렉션=====================");
		Object newBungeoppang = instanceType.newInstance();
		for(Field fld : fields) {
			String fldName = fld.getName();
			try {
				//프로퍼티가 맞다는 것을 예상하고 로직을 구성했을때 exception처리
				PropertyDescriptor pd = new PropertyDescriptor(fldName, instanceType);
				Method getter = pd.getReadMethod();
				Method setter = pd.getWriteMethod();
				//get는 파라미터를 받지않기 때문에 두번째 파라미터를 생략한다
				Object value = getter.invoke(instance);
				setter.invoke(newBungeoppang, value);
				System.out.printf("%s = %s;\n", fldName, value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(newBungeoppang);
//		fishType:붕어, ppangseo:슈크림
		instanceType.getDeclaredField("fishType");
		PropertyDescriptor pd1 = new PropertyDescriptor("fishType", instanceType);
		pd1.getWriteMethod().invoke(newBungeoppang, "붕어");
		PropertyDescriptor pd2 = new PropertyDescriptor("ppangso", instanceType);
		pd2.getWriteMethod().invoke(newBungeoppang, "슈크림");
		System.out.println(newBungeoppang);
		
	}
}

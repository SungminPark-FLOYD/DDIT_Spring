package kr.or.ddit.exception;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * 예외란?
 * 	: 프로그램이 실행이 중단될 수 있는 모든 상황에 대한 정보를 통칭.
 * 	: Throwable의 하위 인스턴스 객체의 형태
 * Throwable -- Error : 복구 / 전환 / 회피가 불가능한 심각한 문제, 
 * 						런타임에 직접 처리가 불가능한 시스템 폴트 상황등의 심각정도가 다른 Throwable 객체.
 *			 -- Exception : 필요에 의해 예외 처리 정책에 따라 직접 처리(회피)가 가능한 Throwable 객체
 *					- Checked Exception : 예외가 발생 가능한 로직에서 발생 가능한 예외를 반드시 직접 처리해야 하는 예외
 *						ex) IOException, SQLException
 *					- UnChecked Exception (RuntimeException 하위) : 
 *								직접 예외를 처리하지 않더라도 호출 구조를 통해 최종 JVM까지 던져지는 예외
 *								(모든 메소드의 선언부에 thorws RuntimeException 구문이 묵시적으로 포함되어있음)
 *						ex) NullPointerException, IllegalArgumentException, NumberFormatException
 *
 * RuntimeException : 모든 UnChecked Exception을 처리한다
 * 
 *  ** 예외 처리 정책
 *  1. 적극적인 처리(전환, 복구)
 *  	try(){
 *  		예외 발생 가능 로직
 *  	}catch(처리할 예외 타입...들..){
 *  		전환이나 복구 정책 구현
 *  		1) 전환(***) : 특정 조건이나 상황을 명확하게 표현할 수 있는 구체적인 타입의 예외나, 
 *  					  혹은 전혀 다른 예외의 분류로 wrapping할때 사용되는 전략.
 *  			ex) catch(IOException e) {
 *  					throw new FileCopyFailureException();
 *  				}
 * 			2) 복구 : 로그기록 --> 트랜젝션 마무리 --> 일정시간 지연 --> 재시도
 *  	}
 *  2. 소극적인 처리(회피) : throws 키워트로 호출자에게 예외 처리를 떠넘기는 구조.
 *  
 *  *** 커스텀 예외 정의 방법
 *  1. 예외 계층 구조 결정 (Exception, RuntimeException..)
 *  2. throw new 생성자() 로 예외 객체 생성후 투과.
 */
public class ExceptionDesc {
	public static void main(String[] args) {
		
		try {
			method1();
		} catch (IOException e) {
			throw new FileCopyFailureException(e);
//			throw new RuntimeException(e);
		}
		
//		try {			
//			method1();
//		}catch (RuntimeException e) {
//			e.printStackTrace();
//			System.out.println("하이");
//		}
	}
	
	private static void method1() throws IOException {
		throw new IOException("강제발생 예외");
	}
	
	private static void method2() throws NullPointerException{
		throw new NullPointerException("강제발생 예외");
	}

}

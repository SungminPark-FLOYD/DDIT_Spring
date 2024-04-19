package kr.or.ddit.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import lombok.extern.slf4j.Slf4j;

/**
 * brfore : 타겟 메소드 호출 전에 위빙
 * after : 타겟 메소드 호출 이후 위빙
 * around : 
 *
 */
@Slf4j
public class LoggingAdvice {
	public void beforeAdvice(JoinPoint point) {
		Object target = point.getTarget();
		//타겟의 경로
		String targetName = target.getClass().getName();
		//어떤 타겟의 어떤 메소드 정보
		String methodName = point.getSignature().getName();
		//파라미터 정보
		Object[] args = point.getArgs();
		
		log.info("{}.{}({}) 호출 전 위빙", targetName, methodName, args);
	}
	
	public void afterAdvice(JoinPoint point, Object retValue) {
		Object target = point.getTarget();
		//타겟의 경로
		String targetName = target.getClass().getName();
		//어떤 타겟의 어떤 메소드 정보
		String methodName = point.getSignature().getName();
		//파라미터 정보
		Object[] args = point.getArgs();
		log.info("{}.{}==>{} 호출 이후 위빙", targetName, methodName, retValue);
	}
	
	public Object aroundAdvice(ProceedingJoinPoint point) throws Throwable {
		Object target = point.getTarget();
		//타겟의 경로
		String targetName = target.getClass().getName();
		//어떤 타겟의 어떤 메소드 정보
		String methodName = point.getSignature().getName();
		Object[] args = point.getArgs();
		log.info("호출 전 위빙 {}.{}({})", targetName, methodName, args);
		long start = System.currentTimeMillis();
		//타겟의 반환값
		Object retValue = point.proceed(args);
		long end = System.currentTimeMillis();
		log.info("호출 이후 위빙, 소요시간 : {}ms, {}.{}==>{}",(end-start), targetName, methodName, retValue);
		return retValue;
		
		
	}
}

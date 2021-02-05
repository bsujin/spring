package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@org.aspectj.lang.annotation.Aspect   - 클래스명과 동일하면 이렇게 생성된다 
public class Aspect {
	
	private static final Logger logger = LoggerFactory.getLogger(Aspect.class);

	// 특정 메소드가 실행되기 전에 실행되어야 할 공통의 관심사항
	public void beforeMethod(JoinPoint joinPoint) {
		logger.debug("Aspect.beforeMethod");
	}
	
	
	// around : 특정 메소드 실행 전후
	// 메소드 실행 전 - 공통 관심사항  
	// 메소드의 원래 로직
	// 메소드의 실행 후 - 공통 관심 사항
	
	// 메소드 인자와 반환 타입이 다르다 - object / ProceedingJoinPoint(인자) 
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		// 메소드  본 로직 실행 전 
		long startTime = System.nanoTime();
		
		// joinpoint를 사용하면 advice가 적용될 시점/ 지점 (메소드 전체 - 핵심 로직을 담고있는 메소드 )  
		//getTarget - service의 어떤 객체를 의미 / getClass() - class 정보를 알아올 수 있다  
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		
		// object 형태의 arg 로 사용 - 메소드 인자를 아는 방법은 없음
		// 이 메서드의 실행 인자 - joinPoint.getArgs() 를 제공  
		Object ret = joinPoint.proceed(joinPoint.getArgs());	// 실제 실행되는 메서드 (이전에 쓰거나 이후에 쓰면 된다 )
		
		
		// 메소드 본 로직 실행 후 
		long endTime = System.nanoTime();

		// 어떤 특정 메서드의 실행 시간을 출력
		logger.debug("className : {} methodName : {} duration : {}",className,methodName,  endTime - startTime);
		
		// 실제 메서드가 반환해줘야 하는것 - 반환값을 ret에 넣어줌  
		return ret;
	}
	

}

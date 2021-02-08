package kr.or.ddit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PerformanceCheckInterceptor extends HandlerInterceptorAdapter{
	private static final Logger logger = LoggerFactory.getLogger(PerformanceCheckInterceptor.class);
	
	// 실행시간 구하는 Interceptor
	
	//1) 상속 : HandlerInterceptorAdapter
	
	//2) 구현하는 메서드 : preHandle, postHandle

//	long startTime = 0; 을 postHandle에 사용하기 위해 전역변수로 사용하면 문제  -> session, request 사용 
//		spring은 값이 하나만 존재 (객체가 하나 ) => 같은 변수를 여러사람이 사용하므로 전역변수 사용하면 안된다
	// service 의 경우는 로직이므로 다름 (필드는 모든사용자가 사용)
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 시작 시간을 기록
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		
		
		// 요청을 다음 interceptor  또는 controller(= handler)
		return true;
	}

	// 응답이 이미 생성되어있으므로 값이 들어가있다 - ModelAndView 가 있는 이유 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// controller 메소드가 실행 된 이후 실행되는 영역
		
		long endTime = System.nanoTime();
		long startTime = (long) request.getAttribute("startTime");
		
		logger.debug("duration : {} " , endTime-startTime);
	}

}

package kr.or.ddit.batch.ranger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class RangerScheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(RangerScheduler.class);
	
	// logging 을 반복적으로 호출 하는 작업 실행 
	
	public void logging() {
		logger.debug("rangersScheduler.logging() ");
	}
	
	public static void main(String[] args) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("classpath:/kr/or/ddit/config/spring/scheduler-context.xml");
			// 컨테이너만 생성하면 알아서 나머지 처리를 해준다
	}

}

package kr.or.ddit.batch.ranger;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.scope.context.JobContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RangerBatchScheduler {

	private static final Logger logger = LoggerFactory.getLogger(RangerBatchScheduler.class);

	@Resource(name = "jobLauncher")
	private JobLauncher jobLauncher;

	@Resource(name = "rangersJob")
	private Job job;

	public void rangerTask() {
		// 실행
		try {
			jobLauncher.run(job, new JobParameters());
			logger.debug("jobLauncher : {}", jobLauncher);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
		logger.debug("rangersScheduler.logging() ");
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:/kr/or/ddit/config/spring/scheduler-context.xml",
						"classpath:/kr/or/ddit/config/spring/batch-context.xml" });
		
		/*
		 * RangerBatchScheduler rangerbatchScheduler = new RangerBatchScheduler();
		 * rangerbatchScheduler.jobLauncher =
		 * context.getBean("jobLauncher",JobLauncher.class); rangerbatchScheduler.job =
		 * context.getBean("job",Job.class);
		 */
		// 스케줄러에 스프링 빈을 등록했으므로 new연산자로 만들면 다른 객체  --> 에러발생 
		
	}

}

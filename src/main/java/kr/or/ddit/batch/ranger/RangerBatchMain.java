package kr.or.ddit.batch.ranger;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RangerBatchMain {

	public static void main(String[] args) {
		
		// application Context - spring container == IOC container ⇒ container
		ApplicationContext context =
		new ClassPathXmlApplicationContext("classpath:/kr/or/ddit/config/spring/batch-context.xml");
		
		//job 실행할때 필요 : jobLauncher, job(batch job)
		// DI, DL (현재는 DL)
		
		// 방법1)
//		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		
		
		// 방법2)  메서드 오버로딩 
		JobLauncher jobLauncher= context.getBean("jobLauncher",JobLauncher.class);
		Job job = context.getBean("rangersJob", Job.class);
		 
		
		// 실행
		try {
			jobLauncher.run(job,new JobParameters());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}

}

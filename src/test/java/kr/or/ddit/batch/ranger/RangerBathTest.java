package kr.or.ddit.batch.ranger;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 추가적으로 batch 테스트 할때만 필요한 설정파일이 있음 - test/resources 에 만들기 (batch-test.xml)
@ContextConfiguration({ "classpath:/kr/or/ddit/config/spring/scheduler-context.xml",
						"classpath:/kr/or/ddit/config/spring/batch-context.xml",
						"classpath:/kr/or/ddit/config/spring/batch-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RangerBathTest {
	
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Test
	public void rangerTaskTest() throws Exception {
		// launcher, job이 없음 -> 배치 테스트 할 때 규약사항
		// batch 테스트시 job 타입으로 등록된 스프링 빈은 하나여야된다
		// job 이름을 명시하지 않아도 container에 하나의 배치 job이 등록되어있다는 가정이 있기 때문에 별도로 job이름을 명시하지는 않는다 
		JobExecution excution = jobLauncherTestUtils.launchJob();
		// 정상적으로 완료가 되면 ExitStatus.COMPLETED 코드를 갖고 있어야 한다 - JobExecution에 명시되어있음 
		assertEquals(ExitStatus.COMPLETED, excution.getExitStatus());
	}

}

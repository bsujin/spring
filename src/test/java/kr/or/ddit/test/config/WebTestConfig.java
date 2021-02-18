package kr.or.ddit.test.config;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ContextConfiguration(locations = { "classpath:/kr/or/ddit/config/spring/application-context.xml",
									"classpath:/kr/or/ddit/config/spring/root-context.xml",
									"classpath:/kr/or/ddit/config/spring/datasource-context_dev.xml"})

//"classpath:/kr/or/ddit/config/spring/datasource-context.xml"		})
@WebAppConfiguration // ������ ȯ���� WEB����� Application Context�� ����
@RunWith(SpringJUnit4ClassRunner.class)
public class WebTestConfig{
	
//	@Resource(name = "helloController")
//	private HelloController helloController;
	
//	@Test
//	public void helloControllerTest() {
//		assertNotNull(helloController);
//		
//	}
	@Autowired
	private WebApplicationContext context;
	
	protected MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Ignore
	@Test
	public void dummy() {
		
	}
	
}

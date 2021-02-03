package kr.or.ddit.hello;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.config.WebTestConfig;
import kr.or.ddit.user.model.UserVo;

/*
 * 	java- gui  (��ǥ�� : swing, awt, java fx,swt)
 */
//@ContextConfiguration(locations = { "classpath:/kr/or/ddit/config/spring/application-context.xml",
//									"classpath:/kr/or/ddit/config/spring/root-context.xml" })
//@WebAppConfiguration // ������ ȯ���� WEB����� Application Context�� ����
//@RunWith(SpringJUnit4ClassRunner.class)
public class HelloControllerTest extends WebTestConfig {

	// helloController�� ������ �������� 2���� ���� �ʿ��ϴ�
//	@Resource(name = "helloController")
//	private HelloController helloController;
//	@Test
//	public void helloControllerTest() {
//		assertNotNull(helloController);
//		
//	}

//	@Autowired
//	private WebApplicationContext context;
//	
////	private MockMvc mockMvc;
//	protected MockMvc mockMvc;
//	
//	@Before
//	public void setup() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//	}
	
	// localhost/hello/view
	@Test
	public void viewTest()throws Exception {
//		mockMvc �� buildupPattern	/ andExpect�� ����Ǿ��� �� ��밪�� �Է� ,
		// hellow view��� ��û�� �������� ������°� ��� ���� ��븦 �Ѵ� ��� �ǹ� 
		// isOk �� ������ ���������� �� ��� - 200
		// view �̸� - ������ �������� �� ���Ǵ� view �̸� - �츮�� hello�̹Ƿ� hello�� �������ش� 
		// andExpect�� ��� ����  
		// ������ �ٸ� �� : ��밪�� ������ ���� �� �� �� ���ο� ���� 
		MvcResult mvcResult = mockMvc.perform(get("/hello/view"))
				.andExpect(status().isOk())
				.andExpect(view().name("hello"))
				.andExpect(model().attributeExists("userVo"))
				.andDo(print())
				.andReturn();
		
		// MvcResult �߰�
		ModelAndView  mav = mvcResult.getModelAndView();
//		assertEquals("��밪", "������");
		assertEquals("hello", mav.getViewName());
		UserVo userVo = (UserVo)mav.getModel().get("userVo");
		assertEquals("brown", userVo.getUserid());
		
	}
	
	
//		fail("Not yet implemented");  ���������� ���и� �����Ѵ�

	
	// pathVariableTest 
	@Test
	public void pathVariableTest()throws Exception{
		MvcResult mvcResult = mockMvc.perform(get("/hello/path/brown"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("subpath"))
				.andDo(print())
				.andReturn();
		
	}
}

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
 * 	java- gui  (대표적 : swing, awt, java fx,swt)
 */
//@ContextConfiguration(locations = { "classpath:/kr/or/ddit/config/spring/application-context.xml",
//									"classpath:/kr/or/ddit/config/spring/root-context.xml" })
//@WebAppConfiguration // 스프링 환경을 WEB기반의 Application Context로 생성
//@RunWith(SpringJUnit4ClassRunner.class)
public class HelloControllerTest extends WebTestConfig {

	// helloController가 주입을 받으려면 2개의 빈이 필요하다
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
//		mockMvc 는 buildupPattern	/ andExpect는 실행되었을 때 기대값을 입력 ,
		// hellow view라는 요청을 보냈을때 응답상태가 어떻게 될지 기대를 한다 라는 의미 
		// isOk 는 응답이 정상적으로 온 경우 - 200
		// view 이름 - 응답이 정상적일 때 기대되는 view 이름 - 우리는 hello이므로 hello를 지정해준다 
		// andExpect를 계속 적용  
		// 기존과 다른 것 : 기대값과 실행을 검증 할 때 한 라인에 들어간다 
		MvcResult mvcResult = mockMvc.perform(get("/hello/view"))
				.andExpect(status().isOk())
				.andExpect(view().name("hello"))
				.andExpect(model().attributeExists("userVo"))
				.andDo(print())
				.andReturn();
		
		// MvcResult 추가
		ModelAndView  mav = mvcResult.getModelAndView();
//		assertEquals("기대값", "실제값");
		assertEquals("hello", mav.getViewName());
		UserVo userVo = (UserVo)mav.getModel().get("userVo");
		assertEquals("brown", userVo.getUserid());
		
	}
	
	
//		fail("Not yet implemented");  강제적으로 실패를 유도한다

	
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

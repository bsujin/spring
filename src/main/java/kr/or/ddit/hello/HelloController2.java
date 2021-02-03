package kr.or.ddit.hello;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.user.service.UserService;

// 컨트롤러에는 @Controller 붙여줘야 한다 
//1) 
//@RequestMapping("hello")
@Controller
public class HelloController2 {

	private static final Logger logger = LoggerFactory.getLogger(HelloController2.class);
	
	// 1) 
//	@Resource(name="userService")
//	private UserService userService;
//	
//	@RequestMapping("view")
//	private String view() {
//		logger.debug("HelloController.view() : {}", userService.getUser("brown"));
//		return "hello";
//	}
	
	// 2) 값을 가져오기 - model 사용
// 주입을 받는다 
//	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping("view")
	private String view(Model model) {
		logger.debug("HelloController.view() : {}", userService.selectUser("brown"));
		
		// request.setAttribute("userVo", userService.getUser("brown")); 와 동일하다 
		
		model.addAttribute("userVo",  userService.selectUser("brown"));
		
		return "hello";
	}
	
	// 3) 값을 가져오기 - 서블릿에 인자 넣어주기  
//	@Resource(name="userService")
//	private UserService userService;
//	
//	@RequestMapping("view")
//	private String view(HttpServletRequest request) {
//		logger.debug("HelloController.view() : {}", userService.getUser("brown"));
//		
//		 request.setAttribute("userVo", userService.getUser("brown"));
//		
//		return "hello";
//	}
	
//	view는 스프링에서 제공해주는 인터페이스
//	우리가 리턴하는 것은 문자열 - view name 
	
}

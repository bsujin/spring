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

// ��Ʈ�ѷ����� @Controller �ٿ���� �Ѵ� 
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
	
	// 2) ���� �������� - model ���
// ������ �޴´� 
//	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping("view")
	private String view(Model model) {
		logger.debug("HelloController.view() : {}", userService.selectUser("brown"));
		
		// request.setAttribute("userVo", userService.getUser("brown")); �� �����ϴ� 
		
		model.addAttribute("userVo",  userService.selectUser("brown"));
		
		return "hello";
	}
	
	// 3) ���� �������� - ������ ���� �־��ֱ�  
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
	
//	view�� ���������� �������ִ� �������̽�
//	�츮�� �����ϴ� ���� ���ڿ� - view name 
	
}

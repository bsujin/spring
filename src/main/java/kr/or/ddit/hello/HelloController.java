package kr.or.ddit.hello;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.or.ddit.user.service.UserService;

// 컨트롤러에는 @Controller 붙여줘야 한다 
//1)
@SessionAttributes("rangers")			//저장하고싶은 속성 명을 기입
@RequestMapping("hello")
@Controller
public class HelloController {

	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

// 자동으로 주입을 해준다 -의존 자동 주입  // Type으로 찾아간다  
	@Autowired
	private UserService userService;
	
	
	@ModelAttribute(name="rangers")
	public List<String> rangers(){
		logger.debug("helloController.rangers()");
		
		List<String> list = new ArrayList<String>();
		list.add("brown");
		list.add("sally");
		list.add("james");
		list.add("cony");
		list.add("moon");
		return list;
	}
	
//	@RequestMapping("view")
//	private String view(Model model, HttpServletRequest request) {
//		logger.debug("HelloController.view() : {}", userService.selectUser("brown"));
//		
//	// request.setAttribute("userVo", userService.getUser("brown")); 와 동일하다 
//		
//		model.addAttribute("userVo",  userService.selectUser("brown"));
//		
//		return "hello";
//	}
	
	
//	모델에서 꺼내는 동작을 인자로 받아서 처리  - attribute를 하면 바로 바인딩 가능 
	
	@RequestMapping("view")
	private String view(Model model, @ModelAttribute(name="rangers") List<String>rangers,HttpServletRequest request) {
		logger.debug("HelloController.view() : {}", userService.selectUser("brown"));
		logger.debug("rangers : {}", rangers);
		
		model.addAttribute("userVo",  userService.selectUser("brown"));
		
		return "hello";
	}

	//hello/path/subpath
	//hello/path/brown
	//hello/path/cony
	// - 마지막의 값이 어떤것이 들어갈 지 모른다 
	@RequestMapping("path/{subpath}")
	public String pathVariable(@PathVariable("subpath") String subpath, Model model,
			// User-Agent 헤더 값 바인딩 
			@RequestHeader(name="User-Agent") String userAgent) {
		// User-Agent 값 로거로 출력
			logger.debug("user-agent : {} ", userAgent);
			
		model.addAttribute("subpath",subpath);
		
		return "hello";
		
	}
	
}

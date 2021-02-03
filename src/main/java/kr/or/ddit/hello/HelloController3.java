package kr.or.ddit.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 컨트롤러에는 @Controller 붙여줘야 한다 
//1) 
//@RequestMapping("hello")
@Controller
public class HelloController3 {

	private static final Logger logger = LoggerFactory.getLogger(HelloController3.class);
	// localhost/hellow/view (이상태로 하고싶으면 Mapping에 적어주기)  ==>> localhost/view
//	@RequestMapping("hellow/view")
	
	// localhost/hello/view.do --> .do 2000 년대 초반에 유행
	
	//2)
	@RequestMapping("view.do")
	private String view() {
		logger.debug("HelloController.view");
		return "hello";
	}
//	
//	view는 스프링에서 제공해주는 인터페이스
//	우리가 리턴하는 것은 문자열 - view name 
	
}

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

// ��Ʈ�ѷ����� @Controller �ٿ���� �Ѵ� 
//1)
@SessionAttributes("rangers")			//�����ϰ���� �Ӽ� ���� ����
@RequestMapping("hello")
@Controller
public class HelloController {

	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

// �ڵ����� ������ ���ش� -���� �ڵ� ����  // Type���� ã�ư���  
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
//	// request.setAttribute("userVo", userService.getUser("brown")); �� �����ϴ� 
//		
//		model.addAttribute("userVo",  userService.selectUser("brown"));
//		
//		return "hello";
//	}
	
	
//	�𵨿��� ������ ������ ���ڷ� �޾Ƽ� ó��  - attribute�� �ϸ� �ٷ� ���ε� ���� 
	
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
	// - �������� ���� ����� �� �� �𸥴� 
	@RequestMapping("path/{subpath}")
	public String pathVariable(@PathVariable("subpath") String subpath, Model model,
			// User-Agent ��� �� ���ε� 
			@RequestHeader(name="User-Agent") String userAgent) {
		// User-Agent �� �ΰŷ� ���
			logger.debug("user-agent : {} ", userAgent);
			
		model.addAttribute("subpath",subpath);
		
		return "hello";
		
	}
	
}

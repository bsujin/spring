package kr.or.ddit.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.user.service.UserService;

//@Controller
public class LoginController1{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController1.class);
	
	@Autowired
	private UserService userService;
	
@RequestMapping("loginController")
	private String view(Model model) {
		logger.debug("loginController.view() :시작");
		
		return "login";
	}

}		
package kr.or.ddit.user.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.hello.HelloController;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

//@Controller
//@RequestMapping("user")
public class UserController3 {
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

	// 자동으로 주입을 해준다 -의존 자동 주입  // Type으로 찾아간다  
//		@Resource(name="userService")
		private UserService userService;
		
		
//		@RequestMapping("allUser")
		private String view(Model model) {
			logger.debug("allUser.view() 진입");
			
			model.addAttribute("userList",  userService.selectAllUser());
			
			return "user/allUser";
		}

//	@RequestMapping("pagingUser")
	public String pagingUser(@RequestParam(defaultValue="1") int page,
			@RequestParam(defaultValue="5") int pageSize, Model model) {
		
		PageVo pageVo = new PageVo(page, pageSize);
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		return "user/paginUser";

	}
	
		
		
		
}

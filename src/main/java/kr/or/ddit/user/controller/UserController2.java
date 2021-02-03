package kr.or.ddit.user.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.hello.HelloController;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

//@Controller
//@RequestMapping("user")
public class UserController2 {
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

		//	방법1) 연습 
//	@RequestMapping("pagingUser")
//		public String pagingUser(@RequestParam(defaultValue="1") int page,
//								 @RequestParam(defaultValue="5") int pageSize,
//				@RequestParam(name="p") int price) {
//			logger.debug("page:{}, pageSize : {}", page, pageSize);
//			
//			PageVo pageVo = new PageVo(page, pageSize);
//			// --> 파라미터로 넣어주기 
//			return "";
//		}
	
//	@RequestMapping("pagingUser")
	public String pagingUser(@RequestParam(defaultValue="1") int page,
			@RequestParam(defaultValue="5") int pageSize, Model model) {
		
		PageVo pageVo = new PageVo(page, pageSize);
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		return "user/paginUser";

//	2	Map<String, Object> map = userService.selectPagingUser(pageVo);
//		model.addAllAttributes(map);
		
//	1	int memCnt = (int) map.get("userCnt");
//		int pagination =  (int) Math.ceil((double)memCnt/pageVo.getPageSize());
//		model.addAttribute("userList", map.get("userList"));
//		model.addAttribute("pagination", pagination);
//		model.addAttribute("pageVo",pageVo);
		// --> 파라미터로 넣어주기 
	}
	
		
		//바인딩 못하면 그냥 넘어가고, 과정에서 다르면 에러 발생 
//		@RequestMapping("pagingUser")
		public String pagingUser(PageVo pageVo, Model model) {
			
			Map<String, Object> map = userService.selectPagingUser(pageVo);
			List<UserVo> pageList = (List<UserVo>) map.get("userList");
			int memCnt = (int) map.get("memCnt");
			int pagination =  (int) Math.ceil((double)memCnt/pageVo.getPageSize());
			model.addAttribute("pageList", map.get("userList"));
			model.addAttribute("pagination", pagination);
			model.addAttribute("pageVo",pageVo);
			
			
			return "user/paginUser";
		}
		
		
		
}

package kr.or.ddit.user.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("ajaxUser")
@Controller
public class UserController_ajax {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController_ajax.class);
	
	@Resource(name="userService")
	private UserService userService;

	//데이터는 안보여주고 데이터를 가져온다음 화면에 작업하기
	// 사용자 리스트가 없는 상태의 화면만 응답으로 생성 
	@RequestMapping("pagingUserAjaxView")
	public String pagingUserAjaxView() {
		
		return "tiles.userbody.pagingUserAjax";
	}
	
	@RequestMapping("pagingUserAjax")  //--> userbodyCotroller에 있음
	public String pagingUserAjax(@RequestParam(defaultValue = "1") int page,
								 @RequestParam(defaultValue = "5") int pageSize,
								   Model model) {
		
		PageVo pageVo = new PageVo(page, pageSize);
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		
		// jsonView를 추가 - model에 담겨진 부분을 jsonView로 한다 
		return "jsonView";
	}
	
	
	@RequestMapping("pagingUserAjaxHtml")  //--> userbodyCotroller에 있음
	public String pagingUserAjaxHtml(@RequestParam(defaultValue = "1") int page,
									 @RequestParam(defaultValue = "5") int pageSize,
			Model model) {
		
		PageVo pageVo = new PageVo(page, pageSize);
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		
		//requestmapping에 담겨진 값을 그대로 작성
		/* 
		 * pagingUserAjaxHtml ==> WEB-INF/views/user/pagingUserAjaxHtml.jsp (In) 
		 */
		return "userbody/pagingUserAjaxHtml";
	}
	
}














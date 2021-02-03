package kr.or.ddit.mvc.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//@RequestMapping("ajax")
@Controller
public class AjaxController2 {


	
	// localhost/ ajax/ jsonView - 실질적으로 ajax 코드는 아니다 
	@RequestMapping("jsonView")
	public String jsonView(Model model) {
		
		List<String> rangers= new ArrayList<String>();
		rangers.add("brown");
		rangers.add("sally");
		rangers.add("con");
		rangers.add("cony");
		rangers.add("moon");
		rangers.add("james");
		
		// 속성으로 넣어주기
		model.addAttribute("rangers", rangers);
		
		return "jsonView";		//xml에 등록한 스프링 빈이름(뷰)를 반환
		// return을 하면 빈이름을 검색 - 모델에 담겨져있는 속성을 꺼내서 json문자열로 만든다 
	}
	
	
}

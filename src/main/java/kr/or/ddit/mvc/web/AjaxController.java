package kr.or.ddit.mvc.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@RequestMapping("ajax")
@Controller
public class AjaxController {
//	전체적인 틀 - 프레임워크 (틀 자체를 뒤 엎을 수는 없다) 
	
	//requestMapping이 실행되기 전에 실행되는것 - 반환하는 어떤 값을 model객체에 자동으로 넣어준다  
	@ModelAttribute(name="rangers")
	public List<String> rangers(){
		List<String> rangers= new ArrayList<String>();
		rangers.add("brown");
		rangers.add("sally");
		rangers.add("con");
		rangers.add("cony");
		rangers.add("moon");
		rangers.add("james");
		rangers.add("뷰");
		
		return rangers;
	}

	
	// localhost/ ajax/ jsonView - 실질적으로 ajax 코드는 아니다 
	@RequestMapping("jsonView")
	public String jsonView() {
		return "jsonView";		
	}
	
	@RequestMapping("jsonViewViewObj")
	public View jsonViewViewObj() {
		// view 객체를 리턴함 - view Resolver를 거치지 않고 개발자가 직접 반환한다 
		return new MappingJackson2JsonView();
		//new 연산자를 계속 생성하여, 메모리를 할당하므로 좋지 않다 
		// 생성한 빈을 주입하여 사용하는데, 모든 컨트롤러마다 view객체 주입보다는 bean에 있는 resolver를 하는것이 더 낫다 
	}
	
	// 과거스타일 ModelAndView
	@RequestMapping("jsonViewMav")
	public ModelAndView jsonViewMav() {
		// 내부적으로 같고있던 model과 합쳐져서 view로 반환한다 
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jsonView");
		return mav;
	}
	
	
	
}

package kr.or.ddit.login;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;

@RequestMapping("login")
@Controller
public class LoginController_sem{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController_sem.class);
	
	@Autowired
	private UserService userService;
	
	// params - 특정 파라미터의 값이 정해진 값과 일치할 때만 해당 요청을 처리 
	@RequestMapping(path = "view", method = {RequestMethod.GET})
//	@RequestMapping("view")
	private String view() {
		logger.debug("login.view() 시작");
		
		return "login";
	}
	
//	@RequestMapping("process")
	// 파라미터는 항상 string (문자)타입, 파라미터 이름과 동일하게 인자값을 작성하기 
	private String process(String userid, String pass, int price) {
		logger.debug("login.process() 시작");
		logger.debug("userid : {}", userid);
		logger.debug("pass : {}", pass);
		logger.debug("price : {}", price);
		
		return "";
	}
	
	// method는 배열형태로 입력해야하지만, 한개일때는 상관없음 
	@RequestMapping(path="process", method = RequestMethod.POST )
	private String process(UserVo userVo, HttpSession session, HttpServletRequest request, RedirectAttributes ra) {
		logger.debug("userVo : {}", userVo);
		UserVo dbUser = userService.selectUser(userVo.getUserid());
		logger.debug("dbUser : {}", dbUser);
		
		/*
		 * - servlet
		 * if(user != null && pass.equals(user.getPass())) {
				HttpSession session = req.getSession();
				session.setAttribute("S_USER", user);
			req.getRequestDispatcher("/main.jsp").forward(req, resp);
		 */
		
		if(dbUser!=null && userVo.getPass().equals(dbUser.getPass())) {
			// 데이터 베이스에서 조회한 사용자 값을 넣기 
			session.setAttribute("S_USER", dbUser);
				return "main";
		}
		/*
		 * //로그인 실패 - servlet
		else {
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
		}
		 */
		else {
//			request.setAttribute("msg", "잘못된 사용자 정보입니다.");
			// redirect 로는 login으로 가므로 뜨지 않는다 --> session에 넣어주기 
//			session.setAttribute("msg", "잘못된 사용자 정보입니다.");
			
			// 내부적으로 session을 사용하여 속성을 저장
			// 리다이렉트 처리가 완료 되면 스프링 프레임워크에서 자동으로 sesison에서 제거 
			ra.addFlashAttribute("msg", "잘못된 사용자 정보입니다.");
			
			// 일반 속성을 추가한 경우 :  addAttribute
			// 리다이렉트 페이지의 파라미터로 전달 된다 
			ra.addAttribute("userid", "brown");
			
			return "redirect:/login/view";
		}
	}
}
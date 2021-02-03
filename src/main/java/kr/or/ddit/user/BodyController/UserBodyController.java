package kr.or.ddit.user.BodyController;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.validator.UserVoValidator;

@RequestMapping("userbody")
@Controller
public class UserBodyController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserBodyController.class);
	
	@Resource(name="userService")
	private UserService userService;

	// layout을 사용 
	@RequestMapping("pagingUserTiles")
	public String paigingUserTiles(@RequestParam(defaultValue = "1") int page,
								   @RequestParam(defaultValue = "5") int pageSize,
								   Model model) {
		
		PageVo pageVo = new PageVo(page, pageSize);
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		
		// tiles_definition에 설정한 이름
		// tiles를 처리해줄 bean을 설정해야한다 
		logger.debug("tiles.userbody.pagingUser");
		return "tiles.userbody.pagingUser";
	}
	
	@RequestMapping("allUserTiles")
	public String allUser(Model model) {
		
		model.addAttribute("userList", userService.selectAllUser());

		logger.debug("tiles.userbody.pagingUser");
		return "tiles.userbody.allUser";
	}
	
	@RequestMapping(path="detailUserTiles", method=RequestMethod.GET)
	public String user(String userid, Model model) {
		
		model.addAttribute("user", userService.selectUser(userid));
		
		return "tiles.userbody.detailUser";
	}
	
	@RequestMapping(path="modifyUserTiles", method=RequestMethod.GET)
	public String modify(String userid, Model model) {
		
		model.addAttribute("user", userService.selectUser(userid));
		
		return "tiles.userbody.userModify";
	}
	
	@RequestMapping(path="modifyUserTiles", method=RequestMethod.POST)
	public String modify(UserVo userVo, MultipartFile profile, RedirectAttributes ra, Model model) {
		
		logger.debug("modify post");
		
		int updateCnt = 0;
		if(profile.getSize() > 0) {
			String originalFilename = profile.getOriginalFilename();
			String filename = UUID.randomUUID().toString() + "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			
			userVo.setFilename(originalFilename);
			userVo.setRealfilename("d:\\upload\\" + filename);
			
			try {
				profile.transferTo(new File(userVo.getRealfilename()));
				updateCnt = userService.modifyUser(userVo);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		if(updateCnt == 1) {
			ra.addAttribute("userid", userVo.getUserid());
			return "redirect:/userbody/detailUser";
		}
		else {
			return modify(userVo.getUserid(), model);
		}
	}
	
	@RequestMapping(path="registUserTiles", method=RequestMethod.GET)
	public String regist() {
		return "tiles.userbody.registUser";
	}
	
	// bindingResult 객체는 command 객체 바로 뒤에 인자로 기술해야 한다 
	@RequestMapping(path="registUserTiles", method=RequestMethod.POST)
// 1.	public String regist(UserVo userVo, BindingResult result, MultipartFile profile, Model model) {
//2.
		public String regist(@Valid UserVo userVo, BindingResult result, MultipartFile profile, Model model) {
		
		//검증하는 메서드
		new UserVoValidator().validate(userVo, result);
		//여기에서는 메세지가 담겨 있다 ( 참조 객체 이므로 ) 
		
		if(result.hasErrors()) {
			// redirect 로 보내면 다른 페이지로 가므로 문제가 발생될 수 있음 forward
			logger.debug("result has error");
			return "tiles.userbody.registUser";
		}
		
		int insertCnt = 0;
		String originalFilename = "";
		String filename = "";
		
		if(profile.getSize() > 0) {
			originalFilename = profile.getOriginalFilename();
			filename = UUID.randomUUID().toString() + "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			
			userVo.setFilename(originalFilename);
			userVo.setRealfilename("d:\\upload\\" + filename);
			
			try {
				profile.transferTo(new File(userVo.getRealfilename()));
				insertCnt = userService.registerUser(userVo);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		if(insertCnt == 1) {
			return "redirect:/userbody/pagingUserTiles";
		}
		else {
			return "tiles.userbody.registUser";
		}
	}
	
	@RequestMapping("deleteUserTiles")
	public String delete(String userid) {
		
		int deleteCnt = 0;
		
		try {
			deleteCnt = userService.deleteUser(userid);
		}catch(Exception e) {
			deleteCnt = -1;
		}
		
		if(deleteCnt == 1) {
			// redirect를 사용시에는 requestMapping을 사용 
			return "redirect:/userbody/pagingUserTiles";
		}
		else {
			return "redirect:/userbody/detailuserTiles?userid="+ userid;
		}
	}
	
	
	
	
	
}














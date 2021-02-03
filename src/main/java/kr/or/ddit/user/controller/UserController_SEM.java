package kr.or.ddit.user.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;

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

@RequestMapping("user")
@Controller
public class UserController_SEM {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="userService")
	private UserService userService;

	@RequestMapping("allUser")
	public String allUser(Model model) {
		
		model.addAttribute("userList", userService.selectAllUser());

		return "user/allUser";
	}
	
	@RequestMapping("pagingUser")
	public String paigingUser(@RequestParam(defaultValue = "1") int page,
							  @RequestParam(defaultValue = "5") int pageSize,
							  Model model) {
		
		PageVo pageVo = new PageVo(page, pageSize);
		
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		
		return "user/pagingUser";
	}

	//@RequestMapping("pagingUser")
	public String pagingUser(PageVo pageVo) {
		
		logger.debug("pageVo : {}", pageVo);
		
		return "";
	}
	
//	@RequestMapping(path="user", method=RequestMethod.GET)
//	public String user(String userid, Model model) {
//		
//		model.addAttribute("user", userService.selectUser(userid));
//		
//		return "user/user";
//	}
	@RequestMapping(path="detailUser", method=RequestMethod.GET)
	public String user(String userid, Model model) {
		
		model.addAttribute("user", userService.selectUser(userid));
		
		return "user/detailUser";
	}
	
	@RequestMapping(path="modifyUser", method=RequestMethod.GET)
	public String modify(String userid, Model model) {
		
		model.addAttribute("user", userService.selectUser(userid));
		
		return "user/userModify";
	}
	
	@RequestMapping(path="modifyUser", method=RequestMethod.POST)
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
		
		//����� ������ ���������� �� ���	==> �ش� ������� ����ȸ �������� �̵�
		if(updateCnt == 1) {
			ra.addAttribute("userid", userVo.getUserid());
			return "redirect:/user/user";
		}
		//����� ������ ������������ �� ��� ==> �ش� ������� ���� ���� �������� �̵�
		else {
			return modify(userVo.getUserid(), model);
		}
	}
	
	@RequestMapping(path="registUser", method=RequestMethod.GET)
	public String regist() {
		return "user/registUser";
	}
	
	// bindingResult 객체는 command 객체 바로 뒤에 인자로 기술해야 한다 
	@RequestMapping(path="registUser", method=RequestMethod.POST)
	public String regist(UserVo userVo, MultipartFile profile, Model model, BindingResult result) {
		
		//검증하는 메서드
		new UserVoValidator().validate(userVo, result);
		//여기에서는 메세지가 담겨 있다 ( 참조 객체 이므로 ) 
		
		if(result.hasErrors()) {
			// redirect 로 보내면 다른 페이지로 가므로 문제가 발생될 수 있음 forward
			logger.debug("result has error");
			return "user/registUser";
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
		
		//����� ����� ���������� �� ���	==> ����� ����¡ ����Ʈ�� �̵�(1������)
		if(insertCnt == 1) {
			return "redirect:/user/pagingUser";
		}
		//����� ������ ������������ �� ��� ==> ����� ��� �������� �̵�(����ڰ� �Է��� �� ����)
		else {
			return "user/userRegist";
		}
	}
	
	@RequestMapping("deleteUser")
	public String delete(String userid) {
		
		int deleteCnt = 0;
		
		try {
			deleteCnt = userService.deleteUser(userid);
		}catch(Exception e) {
			deleteCnt = -1;
		}
		
		if(deleteCnt == 1) {
			return "redirect:/user/pagingUser";
		}
		else {
			return "redirect:/user/user?userid="+ userid;
		}
	}
	
}














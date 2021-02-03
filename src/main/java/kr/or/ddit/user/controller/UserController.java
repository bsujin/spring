package kr.or.ddit.user.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.hello.HelloController;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.util.FileUtil;

//@SessionAttributes("S_USER")
//@Controller
//@RequestMapping("user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

	// 자동으로 주입을 해준다 -의존 자동 주입 // Type으로 찾아간다
	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping("allUser")
	private String view(Model model) {
		logger.debug("allUser.view() 진입");

		model.addAttribute("userList", userService.selectAllUser());

		return "user/allUser";
	}

	@RequestMapping("pagingUser")
	public String pagingUser(@RequestParam(defaultValue = "1") 
	int page, @RequestParam(defaultValue = "5") int pageSize,
			Model model) {

		PageVo pageVo = new PageVo(page, pageSize);
		model.addAllAttributes(userService.selectPagingUser(pageVo));
		return "user/pagingUser";
	}

	@RequestMapping("detailUser")
	public String detailUser(String userid, Model model) {

//		userid = "brown";
		UserVo userVo = userService.selectUser(userid);
		model.addAttribute("user", userVo);

		return "user/detailUser";

	}

	@RequestMapping(value = "modifyUser", method = RequestMethod.POST)
	public String modifyUser(UserVo userVo,Model model) {
		logger.debug("uservo : {}", userVo);
//		userVo = new UserVo("test","대덕인재","test", new Date(),"개발원 m", "대전시 중구 중앙로76","4층 대덕인재개발원","34940","brown.png","uuid-generated-filename.png");
		int updateCnt = userService.modifyUser(userVo);
		if (updateCnt == 1) { // 수정이 정상적으로 된 경우 => 해당 사용자의 상세조회 페이지로 이동
			return "redirect:/detailUser?userid=" + userVo.getUserid();
		} else { // 수정이 비정상적으로 된 경우 => 해당 사용자의 정보 수정 페이지로 이동
			return "user/userModify";
		}

	}
	@RequestMapping(value = "modifyUser", method = RequestMethod.GET)
	public String modifyUser(String userid,Model model) {
//		userVo = new UserVo("test","대덕인재","test", new Date(),"개발원 m", "대전시 중구 중앙로76","4층 대덕인재개발원","34940","brown.png","uuid-generated-filename.png");
		UserVo userVo = userService.selectUser(userid);
		model.addAttribute("user", userVo);
		return "user/userModify";

	}


	@RequestMapping(value = "registUser", method = RequestMethod.POST)
	public String registUser(UserVo userVo, RedirectAttributes ra, MultipartFile profile) {
		logger.debug("registUser 진입");
		logger.debug("uservo : {}", userVo);
		if(userVo.getUserid().length() < 5) {
			
		}
		try {
			// 파일 이름과 확장자를 가져오기
			String fileExtension = FileUtil.getFileExtension(profile.getOriginalFilename());
			// 파일확장자가 없는 경우는 상관 없으나 존재하면 toString 뒤에 확장자를 붙여줘야한다
			String realFilename = UUID.randomUUID().toString() + fileExtension;
			
			 userVo.setFilename(profile.getOriginalFilename());
			 userVo.setRealfilename(realFilename);
			 
			 profile.transferTo(new File("d:\\upload\\" + profile.getOriginalFilename()));
			 
			} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
			}
		int cnt = userService.registerUser(userVo);
		if (cnt == 1) {
			return "redirect:/user/paginUser";
		}
		ra.addFlashAttribute("msg", "회원가입오류");
		return "redirect:/user/registerUser";
		}
	
	
	@RequestMapping(value = "registUser", method = RequestMethod.GET)
	public String registUser() {
		logger.debug("registUser get 진입");
		return "user/registUser";
	}

	@RequestMapping("deleteUser")
	public String DeleteUser(String userid) {

		userid = "brownb";
		int deleteCnt = userService.deleteUser(userid);
		if (deleteCnt == 1) {
			return "redirect:/user/pagingUser";
		} else {
			return "redirect:/detailUser?userid=" + userid;
		}

	}

}

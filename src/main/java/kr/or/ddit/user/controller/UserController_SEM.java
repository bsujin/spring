package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
// 1.	public String regist(UserVo userVo, BindingResult result, MultipartFile profile, Model model) {
//2								//검증메서드 2 @Valid
		public String regist(@Valid UserVo userVo, BindingResult result, MultipartFile profile, Model model) {
		
		//검증하는 메서드 1.
//		new UserVoValidator().validate(userVo, result);
		//여기에서는 메세지가 담겨 있다 ( 참조 객체 이므로 ) 
		
		if(result.hasErrors()) {
			// redirect 로 보내면 다른 페이지로 가므로 문제가 발생될 수 있음 forward
			logger.debug("result has error");
			logger.debug("result : {}", result);
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
			logger.debug("file:{}", filename);
			try {
				profile.transferTo(new File(userVo.getRealfilename()));
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		insertCnt = userService.registerUser(userVo);
		if(insertCnt == 1) {
			return "redirect:/user/pagingUser";
		}
		else {
			return "user/registUser";
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
	
	// localhost:8081/user/excelDownload
	// 사용자 전체 엑셀 다운로드
	@RequestMapping("excelDownload")
	public String excelDownload(Model model) {
		List<String> header = new ArrayList<String>();
		header.add("사용자 아이디");
		header.add("사용자 이름");
		header.add("사용자 별명");

		model.addAttribute("header", header);
		model.addAttribute("data", userService.selectAllUser());
		return "userExcelDownloadView";
	}

	@RequestMapping("profile")
	public void profileDownload(String userid, HttpServletRequest req, HttpServletResponse resp) {
		
		UserVo userVo= userService.selectUser(userid);
		
		String path = "";
		String filename = "";
		if(userVo.getRealfilename() == null) {
			filename= "unknown.png";
			//webapp에 넣기
			path = req.getServletContext().getRealPath("/image/unknown.png");
		}
		else {
			path = userVo.getRealfilename();
			filename= userVo.getFilename();
		}
		
		resp.setHeader("Content-Disposition", "attachment; filename=" + filename);
		
		// userid 파라미터를 이용하여
		// userService 객체를 통해 사용자의 사진 파일 이름을 획득
		// 파일 입출력을 통해 사진을 읽어들여 resp객체의 outputStream으로 응답 생성
		
		logger.debug("path : {} ", path);
		
		try {
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos =  resp.getOutputStream();
			
			byte[] buff = new byte[512];
			
			while(fis.read(buff) != -1) {
				sos.write(buff);
			}
			
			fis.close();
			sos.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}














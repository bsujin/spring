package kr.or.ddit.mvc.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.test.config.WebTestConfig;
public class MvcControllerTest extends WebTestConfig {

	// 파일 업로드 하는 테스트 - 파일에대해 준비를 해야 테스트 가능 
	
	// 경로는 항상 / 적어주기, 테스트 안에 파라미터 없애기 
	@Test
	public void fileuploadViewTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/mvc/fileupload/view"))
				.andExpect(status().isOk())
				.andExpect(view().name("file/view"))
				.andDo(print())
				.andReturn();
				
	}

	@Test
	public void fileuploadTest() throws Exception{
		// String name, String originalFilename, String contentType, InputStream inputStream
		
		ClassPathResource resource = new ClassPathResource("kr/or/ddit/upload/cony.png");
		
		MockMultipartFile file = new MockMultipartFile("picture", "cony.png","image/png", resource.getInputStream());
		
			mockMvc.perform(fileUpload("/mvc/fileupload/upload")
					.file(file)
					.param("userid","brown"))
					.andExpect(view().name("file/view"))
					.andDo(print());
	}
	
			
			
}

package kr.or.ddit.user.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import kr.or.ddit.test.config.WebTestConfig;


public class UserRegistTest extends WebTestConfig{
	@Test
	public void RegistUserTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/user/registerUser"))
				.andExpect(view().name("redirect:/user/paginUser"))
				.andExpect(model().attributeExists("userVo"))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
				
	}
	@Test
	public void DetailUserTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/user/detailUser"))
				.andExpect(view().name("user/detailUser"))
				.andExpect(model().attributeExists("userVo"))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
		

	}
	@Test
	public void DelteUserTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/user/deleteUser"))
				.andExpect(view().name("user/pagingUser"))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
		
		
	}

	

}

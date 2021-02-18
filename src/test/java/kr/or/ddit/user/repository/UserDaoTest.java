package kr.or.ddit.user.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;

// 스프링 환경에서 junit 코드를 실행 ⇒ junit 코드도 스프링 빈으로 등록
// 기본값은 설정파일 위치를 지정하게 되어있다 

public class UserDaoTest extends ModelTestConfig{

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="dataSource")
	private DataSource dataSource;
	
	@Before
	public void setup() {
		
		UserVo userVo = new UserVo("test","대덕인재","test", new Date(), "개발원 m", "대전시 중구 중앙로76","4층 대덕인재개발원","34940","brown.png","uuid-generated-filename.png");
		userDao.registerUser(userVo);
		// 신규 입력 테스트를 위해 테스트 과정에서 입력된 데이터를 삭제 
		// "ddit_n"이라는 사용자는 무조건 삭제를 한다 
		userDao.deleteUser("ddit_n");
		
	}
	@After
	public void teadDown() {
		userDao.deleteUser("test");
	}
	
	
	@Test
	public void getUserTest() {
		/***Given***/
		String userid = "brown";

		/***When***/
		UserVo userVo = userDao.selectUser(userid);

		/***Then***/
		
		//db에서 값을 가져오기 - 성공하면 값을 가져옴 
//		assertEquals("브라운", userVo.getUsernm());
		assertEquals("수진", userVo.getUsernm());
		
	}
	
	@Test
	public void getAllUserTest() {
		List<UserVo> selectAllUser =  new ArrayList<UserVo>();
				selectAllUser= userDao.selectAllUser();
		assertEquals(20, selectAllUser.size());
	}
	
	@Test
	public void selectPaging() {
		
		/***Given***/
		PageVo pageVo = new PageVo(2, 5);
		
		/***When***/
		List<UserVo> pageList = userDao.selectPagingUser(pageVo);

		/***Then***/
		assertEquals(5, pageList.size());
		
	}
//	
//	// 사용자 페이징 조회 테스트 
//	@Test
//	public void selectPaging() {
//		
//		/***Given***/
//	
//		UserService userService = new UserServiceImpl();
//		PageVo pageVo = new PageVo(2, 5);
//		
//		/***When***/
//		Map<String, Object> map = userService.selectPagingUser(pageVo);
//		List<UserVo> pageList = (List<UserVo>)map.get("userList");
//		int userCnt = (int)map.get("userCnt");
//
//		/***Then***/
//		assertEquals(5, pageList.size());
//		assertEquals(16, userCnt);
//		
//	}
	
	@Test
	public void selectAllUserTest() {
		
		int userCnt = userDao.selectAllUserCnt();
//		System.out.println(userCnt);
		
		/***Then***/
		assertEquals(20, userCnt);
		
	}
	
	@Test
	public void modifyUserTest() {
		
		UserVo userVo = new UserVo("test","대덕인재","수정",  "개발원 m", "대전시 중구 중앙로76","4층 대덕인재개발원","34940","brown.png","uuid-generated-filename.png",new Date(), new Date(),1);
		int modifyCnt = userDao.modifyUser(userVo);
		
		/***Then***/
		assertEquals(1, modifyCnt);
		
	}
	
	// 삭제 테스트
		@Test
		public void deleteUserTest() {
			/***Given***/
			// 해당 테스트가 실행 될 때는 testUser 라는 사용자가 before 메소드에 의해 등록이 된 상태 
			String userid = "test";
			
			/***When***/
			int delteCnt = userDao.deleteUser(userid);
			
			/***Then***/
			assertEquals(1, delteCnt);
		}
	
	
	

}

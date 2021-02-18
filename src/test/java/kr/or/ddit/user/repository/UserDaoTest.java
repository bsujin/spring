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

// ������ ȯ�濡�� junit �ڵ带 ���� �� junit �ڵ嵵 ������ ������ ���
// �⺻���� �������� ��ġ�� �����ϰ� �Ǿ��ִ� 

public class UserDaoTest extends ModelTestConfig{

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="dataSource")
	private DataSource dataSource;
	
	@Before
	public void setup() {
		
		UserVo userVo = new UserVo("test","�������","test", new Date(), "���߿� m", "������ �߱� �߾ӷ�76","4�� ������簳�߿�","34940","brown.png","uuid-generated-filename.png");
		userDao.registerUser(userVo);
		// �ű� �Է� �׽�Ʈ�� ���� �׽�Ʈ �������� �Էµ� �����͸� ���� 
		// "ddit_n"�̶�� ����ڴ� ������ ������ �Ѵ� 
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
		
		//db���� ���� �������� - �����ϸ� ���� ������ 
//		assertEquals("����", userVo.getUsernm());
		assertEquals("����", userVo.getUsernm());
		
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
//	// ����� ����¡ ��ȸ �׽�Ʈ 
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
		
		UserVo userVo = new UserVo("test","�������","����",  "���߿� m", "������ �߱� �߾ӷ�76","4�� ������簳�߿�","34940","brown.png","uuid-generated-filename.png",new Date(), new Date(),1);
		int modifyCnt = userDao.modifyUser(userVo);
		
		/***Then***/
		assertEquals(1, modifyCnt);
		
	}
	
	// ���� �׽�Ʈ
		@Test
		public void deleteUserTest() {
			/***Given***/
			// �ش� �׽�Ʈ�� ���� �� ���� testUser ��� ����ڰ� before �޼ҵ忡 ���� ����� �� ���� 
			String userid = "test";
			
			/***When***/
			int delteCnt = userDao.deleteUser(userid);
			
			/***Then***/
			assertEquals(1, delteCnt);
		}
	
	
	

}

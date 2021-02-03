package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/kr/or/ddit/ioc/component-scan.xml",
		"classpath:/kr/or/ddit/config/spring/datasource-context.xml"})
public class ComponentScanTest {

	// @Repository ������̼��� ������ userDaoImpl ������ ���� ���������� ������ �����̳ʿ� ��� �Ǿ����� Ȯ��
	@Resource(name = "userDao")
	private UserDao userDao;

	@Resource(name = "userService")
	private UserService userService;

	@Test
	public void userDaoImplSpringBeantTest() {
		assertNotNull(userDao);

		UserVo userVo = userDao.selectUser("brown");
//		assertEquals("����", userVo.getUsernm());
		assertEquals("����", userVo.getUsernm());

	}

	// userServiceImpl ������ ���� ���������� �����̳ʿ� ��� �Ǿ����� Ȯ��
	@Test
	public void userServiceImplSptringBeantTest() {
		assertNotNull(userService);

		UserVo userVo = userService.selectUser("brown");
//		assertEquals("����", userVo.getUsernm());
		assertEquals("����", userVo.getUsernm());
	}

}

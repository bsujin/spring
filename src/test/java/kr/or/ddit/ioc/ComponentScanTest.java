package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/kr/or/ddit/ioc/component-scan.xml")
public class ComponentScanTest {

	// @Repository ������̼��� ������ userDaoImpl ������ ���� ���������� ������ �����̳ʿ� ��� �Ǿ����� Ȯ��
	@Resource(name = "userDao")
	private UserDao userDao;

	@Resource(name = "userServiceImpl")
	private UserService userService;

	@Test
	public void userDaoImplSpringBeantTest() {
		assertNotNull(userDao);

		UserVo userVo = userDao.getUser("brown");
		assertEquals("����", userVo.getUsernm());

	}

	// userServiceImpl ������ ���� ���������� �����̳ʿ� ��� �Ǿ����� Ȯ��
	@Test
	public void userServiceImplSptringBeantTest() {
		assertNotNull(userService);

		UserVo userVo = userService.getUser("brown");
		assertEquals("����", userVo.getUsernm());
	}

}

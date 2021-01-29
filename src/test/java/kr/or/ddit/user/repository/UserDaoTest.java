package kr.or.ddit.user.repository;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.model.UserVo;

// ������ ȯ�濡�� junit �ڵ带 ���� �� junit �ڵ嵵 ������ ������ ���
// �⺻���� �������� ��ġ�� �����ϰ� �Ǿ��ִ� 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/kr/or/ddit/ioc/Ioc.xml")
public class UserDaoTest {

	@Resource(name="userDao")
	private UserDao userDao;
	
	
	@Test
	public void getUserTest() {
		/***Given***/
		String userid = "brown";

		/***When***/
		UserVo userVo = userDao.getUser(userid);

		/***Then***/
		assertEquals("����", userVo.getUsernm());
		
	}

}
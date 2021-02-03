package kr.or.ddit.ioc.config;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.DBConfig;
import kr.or.ddit.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {IocJavaConfig.class})
public class iocJavaConfigTest {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "userService")
	private UserService userService2;

	@Resource(name = "userServiceCons")
	private UserService userServiceCons;

	@Resource(name = "userServicePrototype")
	private UserService userServicePrototype;

	@Resource(name = "userServicePrototype")
	private UserService userServicePrototype2;

	@Resource(name = "dbConfig")
	private DBConfig dbConfig;

	// userServiceCons ������ ���� ���������� ���� �Ǿ����� �׽�Ʈ
	@Test
	public void userServiceConsTest() {
		/*** Given ***/

		/*** When ***/

		/*** Then ***/
		assertNotNull(userServiceCons);
	}

	@Test
	public void beanScopeTest() {

		// ������ ������ singleton �������� ���� �ΰ��� ��ü�� �Ѱ��� Ŭ������ ���� �������Ƿ� �����ؾ� �Ѵ�
		// ������ �������� singleton ������ bean ������Ʈ�� �������� �ϳ��� ��ü�� �����ȴ�

//		assertEquals(userService, userServiceCons);
		assertNotEquals(userService, userServiceCons);
		// ==> �����ϸ� id�� ������ �ϹǷ� �������� �ʴ� (������ ������ �ƴ϶� spring�̹Ƿ� )

	}

	@Test
	public void beanScopeTest2() {
//		���� ������ ������ ���� ���Թ޾����Ƿ� userService, userService�� ���� ��ü�� 
		assertEquals(userService, userService2);
		// ==> Resource�� ���� name�� �޾����Ƿ� �����ϴ�
		// ==> �� ������ ���� �ĺ��� id�� �Ѵ�
	}

	@Test
	public void beanScopePrototypeTest() {
		// ������ UserServicePrototype ���� ���� (scope : prototype)
		assertNotEquals(userServicePrototype, userServicePrototype2);
	}

	@Test
	public void propertyPlaceHolderTest() {
		assertEquals("sem", dbConfig.getUsername());
		assertEquals("java", dbConfig.getPassword());
		assertEquals("jdbc:oracle:thin:@localhost:1521:xe", dbConfig.getUrl());
		assertEquals("oracle.jdbc.driver.OracleDriver", dbConfig.getDriverClassName());
	}

}

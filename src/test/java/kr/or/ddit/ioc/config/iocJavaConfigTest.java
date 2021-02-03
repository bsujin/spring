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

	// userServiceCons 스프링 빈이 정상적으로 생성 되었는지 테스트
	@Test
	public void userServiceConsTest() {
		/*** Given ***/

		/*** When ***/

		/*** Then ***/
		assertNotNull(userServiceCons);
	}

	@Test
	public void beanScopeTest() {

		// 디자인 패턴의 singleton 개념으로 보면 두개의 객체는 한개의 클래스로 부터 나왔으므로 동일해야 한다
		// 하지만 스프링의 singleton 개념은 bean 엘레멘트를 기준으로 하나의 객체가 생성된다

//		assertEquals(userService, userServiceCons);
		assertNotEquals(userService, userServiceCons);
		// ==> 실행하면 id당 관리를 하므로 동일하지 않다 (디자인 패턴이 아니라 spring이므로 )

	}

	@Test
	public void beanScopeTest2() {
//		만약 동일한 스프링 빈을 주입받았으므로 userService, userService는 같은 객체다 
		assertEquals(userService, userService2);
		// ==> Resource에 같은 name을 받았으므로 동일하다
		// ==> 즉 스프링 빈의 식별은 id로 한다
	}

	@Test
	public void beanScopePrototypeTest() {
		// 동일한 UserServicePrototype 빈을 주입 (scope : prototype)
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

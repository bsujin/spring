package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/kr/or/ddit/ioc/Ioc.xml",
"classpath:/kr/or/ddit/config/spring/datasource-context.xml"})
public class iocTest {

//	@Resource(name="userService")
//	@Autowired  // ������ �� �߿� ���� ������ Ÿ���� ������ ���� �����Ѵ�
				// ���� ������ Ÿ���� ������ ���� ������ ���� ��� @Qulifier ������̼��� ���� Ư�� ������ ���� �̸��� ��Ī�� �� �ִ� 
//	private UserService userService;
	
//	@Resource(name="userService")
//	@Autowired
//	private UserService userService2;
	
//	@Resource(name="userServiceCons")
//	@Autowired
//	private UserService userServiceCons;
	
//	@Resource(name="userServicePrototype")
	@Autowired
	private UserService userServicePrototype;
//	
//	@Resource(name="userServicePrototype")
//	@Autowired
//	private UserService userServicePrototype2;
	
	
	// userServiceCons ������ ���� ���������� ���� �Ǿ����� �׽�Ʈ
	@Test
	public void userServiceConsTest() {
		/***Given***/
		

		/***When***/

		
		/***Then***/
		assertNotNull(userServicePrototype);
		assertNotNull(userServicePrototype);
//		assertNotNull(userServicePrototype2);
//		assertNotNull(userServiceCons);
	}
	
//	@Test
//	public void beanScopeTest() {
//		
//	// ������ ������ singleton �������� ���� �ΰ��� ��ü�� �Ѱ��� Ŭ������ ���� �������Ƿ� �����ؾ� �Ѵ�
//	// ������ �������� singleton ������ bean ������Ʈ�� �������� �ϳ��� ��ü�� �����ȴ�
//		
////		assertEquals(userService, userServiceCons);
//		assertNotEquals(userService, userServiceCons);
//		// ==> �����ϸ� id�� ������ �ϹǷ� �������� �ʴ� (������ ������ �ƴ϶� spring�̹Ƿ� ) 
//		
//	}
//	
//	@Test
//	public void beanScopeTest2() {
////		���� ������ ������ ���� ���Թ޾����Ƿ� userService, userService�� ���� ��ü�� 
//		assertEquals(userService, userService2);
//		// ==> Resource�� ���� name�� �޾����Ƿ� �����ϴ� 
//		// ==> �� ������ ���� �ĺ��� id�� �Ѵ� 
//	}
//	
//	@Test
//	public void beanScopePrototypeTest() {
//		// ������ UserServicePrototype ���� ���� (scope : prototype)
//		assertNotEquals(userServicePrototype, userServicePrototype2);
//	}
//	
	
	

}

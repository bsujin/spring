package kr.or.ddit.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.service.UserService;

public class IocMain {

	private static final Logger logger = LoggerFactory.getLogger(IocMain.class);
	
	public static void main(String[] args) {
		// 1. ������ ���� ������ �̿��Ͽ� ������ �����̳ʸ� ����
			// 1) ������ �������� ���� - �����÷��̽����� ��ġ �� (kr/or/ddit/ioc/ioc.xml)����
			// 2) ������ �����̳� Ÿ�� : ApplicationContext
		//ApplicationContext extend EncironmentCapable  - interface�� interface�� ��� �������� extend

		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/kr/or/ddit/ioc/Ioc.xml");

		// 2. ������ �����̳ʿ��� ������� ������ ���� ��û (������ ��- ��ü)
		// DL(Dependency Lookup) : ������ �����̳ʿ��� ������ ���� ��û�ϴ� ���� 
		
		// 1) ioc�� ���� bean �������� 
		//UserDao userDao = new UserDaoImpl(); - ���� 
		UserDao userDao = (UserDao) context.getBean("userDao");
		
		// 3. ������ �����̳ʿ��� �����ǰ� �ִ� ���� �� ����� ������ Ȯ��
		UserVo userVo = userDao.selectUser("brown");
		
//		logger.debug(" dao - userVo : {}", userVo);
		
		// ������ �����̳ʷκ��� userService ������ ���� DL�� ���� ������ 
		// getUser �޼ҵ带 call, ��ȯ�� ��(userVo)�� logger�� ���� ���
		
		UserService userService = (UserService) context.getBean("userService");
		UserVo vo = userService.selectUser("brown");
		
//		logger.debug(" service - userVo :{}", vo);
		
		// �����Ǿ��ִ� ���� �̸��� �����´� 
		for(String beanName : context.getBeanDefinitionNames()) {
			logger.debug("beanName : {}" , beanName);
		}
		
		

		

	}

}

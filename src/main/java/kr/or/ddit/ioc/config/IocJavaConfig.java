package kr.or.ddit.ioc.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.ioc.CollectionBean;
import kr.or.ddit.ioc.DBConfig;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.repository.UserDaoImpl;
import kr.or.ddit.user.service.UserServiceImpl;

// ������ ������ ��ũ���� �ش� �ڹ� ������ ������ ���� �������� �˷��ش�
@Configuration
// db�� PropertySource�� ���

// �ٸ� ���� �߰��Ҷ� ��� 
@ImportResource("classpath:/kr/or/ddit/config/spring/datasource-context.xml")
@PropertySource(value = {"classpath:/kr/or/ddit/config/db/dbinfo.properties" })
public class IocJavaConfig {
	
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;
	/*
	 * �޼ҵ� : ������ ������ ���� ��ü�� ��ȯ�ϴ� �޼ҵ带 ���� �޼ҵ忡 @Bean ������̼��� ����
	 * 
	 * @Bean ������̼ǿ� ���ٸ� �ɼ��� �������� ������ ������ ������ ���� �̸��� �޼��� �̸����� �����Ѵ� 
	 * (@Bean ������̼��� name �Ӽ��� ���� ������ �� �̸� ���� ����)
	 */
	
	// ������������ ���� ����� ���� �˾Ƽ� �ѹ��� ȣ���� �Ѵ�

	// �� ���� xml -> �ڹٷ� �ٲٱ� 
	// <bean id="userDao" class="kr.or.ddit.user.repository.UserDaoImpl" />
	@Bean
	public UserDao userDao() {
		return new UserDaoImpl();
	}

	/*  xml -> �ڹٷ� �ٲٱ� 
	 	<bean id="userService" class="kr.or.ddit.user.service.UserServiceImpl">
		<property name="userDao" ref="userDao" />
		</bean>
		==>  property - filed, setter ����
		UserService userService = new UserServiceImpl();
		userService.setUserDao(userDao);

	- �������̽����� setter�� ��� impl�� �� 
	*/
	@Bean
	public UserServiceImpl userService() {
		UserServiceImpl userService = new UserServiceImpl();
		
		// ������ �ƴ϶� ���� �ϸ� �ȴ� - ���� ������ bean�� �����´� 
		userService.setUserDao(userDao());
		return userService;
	}
	
	
	/*
	 <!-- ������ ���� -->
	<bean id="userServiceCons" class="kr.or.ddit.user.service.UserServiceImpl">
		<constructor-arg ref="userDao"></constructor-arg>
	</bean>
	
	 */
	@Bean
	public UserServiceImpl userServiceCons() {
		return new UserServiceImpl(userDao());
	}
	/*
	 <!-- Prototype : �ش� ���� dl, di �� �� ���� �Ź� ���Ӱ� ���� ��ü�� ��ȯ  -->
	 ��ȯ�ϴ°��� class, ������ setter (ref ���) 
	  
		<bean id="userServicePrototype" class="kr.or.ddit.user.service.UserServiceImpl" scope="prototype">
		<property name="userDao" ref="userDao" />
		</bean>
	 */
	
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//	@Scope("prototype")
	@Bean
	public UserServiceImpl userServicePrototype() {
		UserServiceImpl userService = new UserServiceImpl();
		userService.setUserDao(userDao());
		return userService;
	}
	
	/*
	 <context:property-placeholder location = "classpath:/kr/or/ddit/config/db/dbinfo.properties"	/>
	
<!-- 	${}�ȿ� Ű���� �־���� �Ѵ� - dbinfo.properties���� ���� �������� ����   -->
	<bean id="dbConfig" class="kr.or.ddit.ioc.DBConfig">
			<property name="driverClassName" value="${jdbc.driverClassName}"/>	
			<property name="url" value="${jdbc.url}"/>	
			<property name="username" value="${jdbc.username}"/>	
			<property name="password" value="${jdbc.password}"/>	
	</bean>
	
	 */
	
	
	/*
	 <!--  list, set, map �÷��� ��ü�� ������ ������ ���  : xml�� �ش� ��ü�� ���� �� �ִ��� Ȯ�� -->
	<!-- 	property�� ����ϸ� setter �� ������ �ϰڴٴ� �ǹ�  -->
	<bean id="collectionBean" class="kr.or.ddit.ioc.CollectionBean">
		<property name="list">
			<list>
				<value>brown</value>
				<value>sally</value>
				<value>cony</value>
			</list>
		</property>
	</bean>	
	 */
	
	@Bean
	public CollectionBean collectionBean() {
	CollectionBean collectionBean = new CollectionBean();
	List<String> list = new ArrayList<String>();
	list.add("brown");
	list.add("sally");
	list.add("cony");
	collectionBean.setList(list);
	return collectionBean;
	
	}
	
	@Bean
	public DBConfig dbConfig() {
		DBConfig dbConfig = new DBConfig();
		dbConfig.setDriverClassName(driverClassName);
		dbConfig.setUrl(url);
		dbConfig.setUsername(username);
		dbConfig.setPassword(password);
		return dbConfig;
	}
	
	
	
	
	
}

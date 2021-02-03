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

// 스프링 프레임 워크에게 해당 자바 파일이 스프링 설정 파일임을 알려준다
@Configuration
// db는 PropertySource를 사용

// 다른 파일 추가할때 사용 
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
	 * 메소드 : 스프링 빈으로 만들 객체를 반환하는 메소드를 생성 메소드에 @Bean 어노테이션을 적용
	 * 
	 * @Bean 어노테이션에 별다른 옵션을 적용하지 않으면 생성된 스프링 빈의 이름은 메서드 이름으로 적용한다 
	 * (@Bean 어노테이션의 name 속성을 통해 스프링 빈 이름 설정 가능)
	 */
	
	// 스프링에서는 빈을 만드는 것을 알아서 한번만 호출을 한다

	// 빈 선언 xml -> 자바로 바꾸기 
	// <bean id="userDao" class="kr.or.ddit.user.repository.UserDaoImpl" />
	@Bean
	public UserDao userDao() {
		return new UserDaoImpl();
	}

	/*  xml -> 자바로 바꾸기 
	 	<bean id="userService" class="kr.or.ddit.user.service.UserServiceImpl">
		<property name="userDao" ref="userDao" />
		</bean>
		==>  property - filed, setter 주입
		UserService userService = new UserServiceImpl();
		userService.setUserDao(userDao);

	- 인테페이스에는 setter가 없어서 impl로 함 
	*/
	@Bean
	public UserServiceImpl userService() {
		UserServiceImpl userService = new UserServiceImpl();
		
		// 주입이 아니라 콜을 하면 된다 - 위에 선언한 bean을 가져온다 
		userService.setUserDao(userDao());
		return userService;
	}
	
	
	/*
	 <!-- 생성자 주입 -->
	<bean id="userServiceCons" class="kr.or.ddit.user.service.UserServiceImpl">
		<constructor-arg ref="userDao"></constructor-arg>
	</bean>
	
	 */
	@Bean
	public UserServiceImpl userServiceCons() {
		return new UserServiceImpl(userDao());
	}
	/*
	 <!-- Prototype : 해당 빈을 dl, di 할 때 마다 매번 새롭게 만든 객체를 반환  -->
	 반환하는것이 class, 주입은 setter (ref 사용) 
	  
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
	
<!-- 	${}안에 키값을 넣어줘야 한다 - dbinfo.properties안의 값을 가져오기 위함   -->
	<bean id="dbConfig" class="kr.or.ddit.ioc.DBConfig">
			<property name="driverClassName" value="${jdbc.driverClassName}"/>	
			<property name="url" value="${jdbc.url}"/>	
			<property name="username" value="${jdbc.username}"/>	
			<property name="password" value="${jdbc.password}"/>	
	</bean>
	
	 */
	
	
	/*
	 <!--  list, set, map 컬렉션 객체를 스프링 빈으로 등록  : xml로 해당 객체를 만들 수 있는지 확인 -->
	<!-- 	property를 사용하면 setter 로 주입을 하겠다는 의미  -->
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

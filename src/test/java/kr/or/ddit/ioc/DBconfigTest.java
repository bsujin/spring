package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/kr/or/ddit/ioc/Ioc.xml")
public class DBconfigTest {

	// db �� ���������� ����Ǿ����� Ȯ���ϱ� 
	// 1) ���Թޱ�
	@Resource(name="dbConfig")
	private DBConfig dbConfig;
	
	@Test
	public void propertyPlaceHolderTest() {
		assertEquals("sem", dbConfig.getUsername());
		assertEquals("java", dbConfig.getPassword());
		assertEquals("jdbc:oracle:thin:@localhost:1521:xe", dbConfig.getUrl());
		assertEquals("oracle.jdbc.driver.OracleDriver", dbConfig.getDriverClassName());
	}

}

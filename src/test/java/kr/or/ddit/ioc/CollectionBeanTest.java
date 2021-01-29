package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:/kr/or/ddit/ioc/Ioc.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CollectionBeanTest {

	// collectionBean 스프링 빈이 정상적으로 생성 되었는지 확인하기
	// 1) 컬렉션빈을 받아온다
	@Resource(name="collectionBean")
	private CollectionBean collectionBean;
	
	@Test
	public void collectionBean_ListTest() {
		assertNotNull(collectionBean);
		assertNotNull(collectionBean.getList());
		assertEquals(3, collectionBean.getList().size());
		assertEquals("sally",collectionBean.getList().get(1));
	}
	@Test
	public void collectionBean_MapTest() {
		assertNotNull(collectionBean);
		assertNotNull(collectionBean.getMap());
		assertEquals("브라운", collectionBean.getMap().get("usernm"));
	}
	

}

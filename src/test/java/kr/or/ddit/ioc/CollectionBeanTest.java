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

	// collectionBean ������ ���� ���������� ���� �Ǿ����� Ȯ���ϱ�
	// 1) �÷��Ǻ��� �޾ƿ´�
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
		assertEquals("����", collectionBean.getMap().get("usernm"));
	}
	

}

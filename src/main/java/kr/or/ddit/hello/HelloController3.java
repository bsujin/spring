package kr.or.ddit.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// ��Ʈ�ѷ����� @Controller �ٿ���� �Ѵ� 
//1) 
//@RequestMapping("hello")
@Controller
public class HelloController3 {

	private static final Logger logger = LoggerFactory.getLogger(HelloController3.class);
	// localhost/hellow/view (�̻��·� �ϰ������ Mapping�� �����ֱ�)  ==>> localhost/view
//	@RequestMapping("hellow/view")
	
	// localhost/hello/view.do --> .do 2000 ��� �ʹݿ� ����
	
	//2)
	@RequestMapping("view.do")
	private String view() {
		logger.debug("HelloController.view");
		return "hello";
	}
//	
//	view�� ���������� �������ִ� �������̽�
//	�츮�� �����ϴ� ���� ���ڿ� - view name 
	
}

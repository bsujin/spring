package kr.or.ddit.batch.ranger;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class RangerReader implements ItemReader<String>{

	private static final Logger logger = LoggerFactory.getLogger(RangerReader.class);
	
//	처리할 데이터를 조회하는 역할 - 가짜 데이터를 만들어서 사용
//	생성자를 만들어 리스트 객체를 생성
	
	private List<String> rangers;
	private int index = 0;	//인덱스를 관리하는 변수가 필요
	
	public RangerReader() {
		rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("sally");
		rangers.add("cony");
		rangers.add("con");
		rangers.add("moon");
		rangers.add("james");
	}
	
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//		 read를 하면 인덱스가 증가하여 순차적으로 콜을 할 수 있다 
		// 더이상 읽을 데이터가 없다고 알려주는 방법 : null 
		if(rangers.size() > index) {
			String ranger = rangers.get(index++);
			logger.debug("reader : {}", ranger);
//			return rangers.get(index++);	
			return ranger;	
		}else {
			index=0;
			return null;
		}
	}

}

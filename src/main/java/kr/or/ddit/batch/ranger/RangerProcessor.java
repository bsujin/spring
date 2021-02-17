package kr.or.ddit.batch.ranger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class RangerProcessor implements ItemProcessor<String, String>{
private static final Logger logger = LoggerFactory.getLogger(RangerProcessor.class);

	// 순차적으로  brown -> sally -> cony -> con-> moon -> james
	@Override
	public String process(String item) throws Exception {
		//reader를 통해 전달받은 item 을 접미어를 붙여 writer에 전달 
		
		logger.debug("process : {} ===> {}", item, item+"modifiled" );
		
		return item + "modifiled";
	}

}

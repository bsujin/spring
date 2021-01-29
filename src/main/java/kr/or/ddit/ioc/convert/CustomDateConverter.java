package kr.or.ddit.ioc.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

// 1. 개발자가 만드는 converter는  Converter 인터페이스를 구현하여 만든다
public class CustomDateConverter implements Converter<String, Date>{

	//setter 생성하기 
	private String dateFormat;
	
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	// source : 2021-01-11, yyyy-mm-dd
	@Override
	public Date convert(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = null;	 
		try {
			//date라는 지역변수에 담긴다
			date = 	sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}

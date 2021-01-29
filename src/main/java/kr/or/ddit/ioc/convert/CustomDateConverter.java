package kr.or.ddit.ioc.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

// 1. �����ڰ� ����� converter��  Converter �������̽��� �����Ͽ� �����
public class CustomDateConverter implements Converter<String, Date>{

	//setter �����ϱ� 
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
			//date��� ���������� ����
			date = 	sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}

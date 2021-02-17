package kr.or.ddit.batch.yogurt;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

public class yogurtProcessor {

	@Test
	public void test() {
		Calendar cal = Calendar.getInstance();
		String dt="202111";
		int day = 2;
		int year = Integer.parseInt(dt.substring(0,4));
		int month = Integer.parseInt(dt.substring(3,5));
		int maxday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(year, month-1, maxday); 
		int days= (cal.get(Calendar.DAY_OF_WEEK)-1);
		
		System.out.println("year : "+ year);
		System.out.println("month : "+ month);
		System.out.println("maxday : "+maxday);
		if(days==day) {
			
		}
	}


	@Test
	public void dayTest() {
//		SimpleDateFormat sft = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		String dt="202111";
		int year = Integer.parseInt(dt.substring(0,4));
		int month = Integer.parseInt(dt.substring(3,5));
		int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(year, month-1, day); 
		int days= (cal.get(Calendar.DAY_OF_WEEK)-1);
		
		System.out.println("year : "+ year);
		System.out.println("month : "+ month);
		System.out.println("day : "+ day);
		System.out.println("days : "+ days);
		
	}
}

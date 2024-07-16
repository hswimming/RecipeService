package com.shinhan.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	// new 하지 않아도 사용하도록
	public static Date getUtilDate(String d) { // 문자가 들어오면 util의 Date로 변경해서 보내기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 형태 작성할때 주의 -> 들어온거랑 다르면 오류남
		Date result = null;
		
		try {
			result = sdf.parse(d); // result에 담아서 return
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static java.sql.Date getSQLDate(String d) { // 문자가 들어오면 util의 Date로 변경해서 보내기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 형태 작성할때 주의 -> 들어온거랑 다르면 오류남
		java.sql.Date result = null;
		
		try {
			Date d2 = sdf.parse(d); // Date return type 무조건 util
			result = new java.sql.Date(d2.getTime()); // SQL Date로 변경
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
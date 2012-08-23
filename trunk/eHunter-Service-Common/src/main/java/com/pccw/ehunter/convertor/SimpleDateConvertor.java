package com.pccw.ehunter.convertor;

import java.util.Calendar;
import java.util.Date;

import com.pccw.ehunter.dto.SimpleDateDTO;
import com.pccw.ehunter.utility.DateUtils;

public class SimpleDateConvertor {
	
	public static SimpleDateDTO toSimpleDate(Date date){
		if(date == null) return null;
		
		SimpleDateDTO dto = new SimpleDateDTO();
		Calendar cal = DateUtils.toCalendar(date);
		
		dto.setYear(String.valueOf(cal.get(Calendar.YEAR)));
		dto.setMonth(String.valueOf(cal.get(Calendar.MONTH) + 1));
		dto.setDay(String.valueOf(cal.get(Calendar.DATE)));
		
		return dto;
	}
	
	public static Date toDate(SimpleDateDTO dto){
		if(dto == null) return null;
		
		Date date = null;
		
		try {
			date = DateUtils.parseDate(dto.getYear(), dto.getMonth(), dto.getDay());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
}

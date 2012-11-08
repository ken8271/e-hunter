package com.pccw.ehunter.convertor;

import java.util.Date;

import com.pccw.ehunter.dto.SimpleDateTimeDTO;
import com.pccw.ehunter.utility.DateUtils;

public class SimpleDateTimeConvertor {

	public static Date toDate(SimpleDateTimeDTO dto){
		if(dto == null) return null;
		
		Date datetime = null;
		
		try {
			datetime = DateUtils.parseDate(dto.getYear(), dto.getMonth(), dto.getDay() , dto.getHour() , dto.getMinute());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return datetime;
	}
}

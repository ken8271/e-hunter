package cn.org.polaris.utility;

import java.util.Date;

import cn.org.polaris.constant.DateFormatConstant;

public class IDGenerator {
	
	public static String generateUUID(){
		return DateUtils.formatDateTime(DateFormatConstant.DATE_TIME_UUID	, new Date());
	}
	
}

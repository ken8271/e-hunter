package com.pccw.ehunter.constant;

import com.pccw.ehunter.utility.StringUtils;

public class CommonConstant {
	
	public static final String YES = "Y";
	public static final String NO = "N";
	
	public static final String USER_ACCOUNT_ACTIVE = "AC";
	public static final String USER_ACCOUNT_INACTIVE = "IN";
	
	public static final String DESC_USER_ACCOUNT_ACTIVE = "正常/活跃";
	public static final String DESC_USER_ACCOUNT_INACTIVE = "禁止/锁定";

	public static final String ANOYMOUS_INTERNAL_USER = "ANONY";
	
	public static final boolean ENABLE_HDIV = true;
	
	public static final String HYPHEN = "-";
	public static final String COMMA = ",";
	
	public static final String STATUS_IN_SERVICE = "IS";//在职
	public static final String STATUS_OUT_SERVICE = "OS";//离职
	
	public static final String STATUS_IN_SERVICE_DESC = "在职";
	public static final String STATUS_OUT_SERVICE_DESC = "离职";
	
	public static final String PREFIX_CUSTOMER_ID = "C";
	public static final String PREFIX_TALENT_ID = "T";
	public static final String PREFIX_PROJECT_ID = "P";
	public static final String PREFIX_RESUME_ID = "R";
	
	public static final String SUFFIX_PDF = ".pdf";
	
	public static final String SUFFIX_SWF = ".swf";
	
	public static final String TYPE_OF_NUMBER = "N";
	public static final String TYPE_OF_STRING = "S";
	public static final String TYPE_OF_DATE = "D";
	
	public static String getDescriptionOfStatus(String status){
		if(USER_ACCOUNT_ACTIVE.equals(status)){
			return DESC_USER_ACCOUNT_ACTIVE;
		}else if(USER_ACCOUNT_INACTIVE.equals(status)){
			return DESC_USER_ACCOUNT_INACTIVE;
		}
		
		return StringUtils.EMPTY_STRING;
	}
	
}

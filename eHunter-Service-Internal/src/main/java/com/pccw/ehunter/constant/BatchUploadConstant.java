package com.pccw.ehunter.constant;

import java.util.ArrayList;
import java.util.List;

import com.pccw.ehunter.utility.StringUtils;

public class BatchUploadConstant {
	public static final String TALENT_BATCH_UPLOAD_FILE_NAME = StringUtils.toUtf8String("人才批量导入-模版");
	
	public static final String CHINESE_NAME = "中文名";
	public static final String ENGLISH_NAME = "英文名";
	public static final String MOBILE_PHONE_NUMBER = "手机";
	public static final String CURRENT_EMPLOYMENT_COMPANY = "现任职公司";
	public static final String CURRENT_POSITION = "职位";
	public static final String CURRENT_LEAVE_PLACE = "现居地";
	
	public static final List<String> TEMPLATE_TITLE = new ArrayList<String>();
	
	static {
		TEMPLATE_TITLE.add(CHINESE_NAME);
		TEMPLATE_TITLE.add(ENGLISH_NAME);
		TEMPLATE_TITLE.add(MOBILE_PHONE_NUMBER);
		TEMPLATE_TITLE.add(CURRENT_EMPLOYMENT_COMPANY);
		TEMPLATE_TITLE.add(CURRENT_POSITION);
		TEMPLATE_TITLE.add(CURRENT_LEAVE_PLACE);
	}
}

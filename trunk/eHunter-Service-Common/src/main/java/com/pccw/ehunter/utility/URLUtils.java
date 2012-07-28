package com.pccw.ehunter.utility;

import javax.servlet.http.HttpServletRequest;

import org.hdiv.util.EncodingUtil;
import org.hdiv.util.SpringRequestUtilsHDIV;

import com.pccw.ehunter.constant.CommonConstant;

public class URLUtils {
	
	public static final String getHDIVUrl(HttpServletRequest request, String url){
		if(CommonConstant.ENABLE_HDIV){
			return SpringRequestUtilsHDIV.addHDIVParameterIfNecessary(request, url);
		}else {
			return url;
		}
	}
	
	public static String urlEncode(String encode){
		try {
			EncodingUtil eu = new EncodingUtil();
			return eu.encode64(encode);
		} catch (Exception e) {
			return "";
		}
	}
	
	public static String urlDecode(String decode){
		try {
			EncodingUtil eu = new EncodingUtil();
			return eu.decode64(decode).toString();
		} catch (Exception e) {
			return "";
		}
	}
}

package com.pccw.ehunter.utility;

import java.util.StringTokenizer;

public class StringUtils {
	public static final String EMPTY_STRING = "";
	
	public static final String COMMA = ",";
	
	public static String toString(Object obj){
		if(null == obj){
			return EMPTY_STRING;
		}else {
			return obj.toString();
		}
	}
	
	public static boolean isEmpty(String s){
		return (s == null || (s.trim()).equals(EMPTY_STRING));
	}
	
	public static String trim(String s) {
		if (s == null) {
			return EMPTY_STRING;
		} else {
			return s.trim();
		}
	}
	
	public static String[] tokenize(String str, String delim) {
		String[] strs = null;
		StringTokenizer tokens = null;
		
		if(null != str){
			if(null != delim){
				tokens = new StringTokenizer(str , delim);
			}else {
				tokens = new StringTokenizer(str);
			}
			
			strs = new String[tokens.countTokens()];
			
			for (int i = 0; i < strs.length && tokens.hasMoreTokens(); i++) {
				strs[i] = tokens.nextToken();
			}
		}
		return strs;
	}
}

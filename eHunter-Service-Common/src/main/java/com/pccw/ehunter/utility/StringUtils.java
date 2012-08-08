package com.pccw.ehunter.utility;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public static boolean isAllDigit(String s) {
		if (s == null || s.equals(EMPTY_STRING)) {
			return false;
		} else {
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (!Character.isDigit(c)) {
					return false;
				}
			}
			return true;
		}
	}
	
	public static boolean isAllAlphanumeric(String s) {
		
		if (s == null || s.equals(EMPTY_STRING)) {
			return false;
		} 
		
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*");

		Matcher matcher = pattern.matcher(s);
		
		if(matcher.matches()){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isMatched(String input, String regex){
		Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(input);
		return m.find();
	}
	
	public static String addLeadingZero(String s, int padding) {
		return addLeadingCharacter(s, '0', padding);
	}
	
	public static String addLeadingCharacter(String s, char c, int padding) {
		if (s != null) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < padding; i++) {
				sb.append(c);
			}
			sb.append(s);
			return sb.toString();
		} else {
			return null;
		}
	}
}

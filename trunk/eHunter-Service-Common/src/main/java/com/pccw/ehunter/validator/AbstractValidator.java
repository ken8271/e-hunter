package com.pccw.ehunter.validator;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pccw.ehunter.utility.StringUtils;

public abstract class AbstractValidator implements Validator{
	
	public void validateRequired(Errors errors, String fieldName, String filedValue , String filedLabel){		
		if(StringUtils.isEmpty(filedValue)){
			errors.rejectValue(fieldName, "EHT-E-0002", new String[]{filedLabel}, filedLabel +  " is required. [EHT-E-0002]");
		}
	}
	
	public void validateStringLength(Object dto, Errors errors, String fieldName, String fieldLabel, int maxLength){
		String property = null;
		try {
			property = BeanUtils.getProperty(dto, fieldName);
		} catch (Exception e) {
			
		}
		
		if(property==null||"".equals(property.trim())){
			return;
		}
		//check chs and replace with **
		String chsRegex = "[\u4e00-\u9fa5]";
		if(StringUtils.isMatched(property, chsRegex)){
			property = property.replaceAll(chsRegex, "**");
		}
		if(property.trim().length()>maxLength){
			errors.rejectValue(fieldName,"EHT-E-0003", new String[]{fieldLabel, "" + maxLength},fieldLabel+" out of the max length(" + maxLength + ") [EHT-E-0003]"); 
		}
	}
	
	public void validateOnlyNumberic(Object dto, Errors errors, String fieldName, String fieldLabel){
		String property = null;
		try {
			property = BeanUtils.getProperty(dto, fieldName);
		} catch (Exception e) {
			
		}
		
		if(property==null||"".equals(property)){
			return;
		}
		if(!StringUtils.isAllDigit(property)){
			errors.rejectValue(fieldName,"EHT-E-0004", new String[]{fieldLabel}, fieldLabel+" - Invalid input [EHT-E-0004]"); 
		}
	}
	
	public void validateOnlyAlphanumeric(Object dto, Errors errors, String fieldName, String fieldLabel){
		String property = null;
		try {
			property = BeanUtils.getProperty(dto, fieldName);
		} catch (Exception e) {
			
		}
		
		if(property==null||"".equals(property)){
			return;
		}
		if(!StringUtils.isAllAlphanumeric(property)){
			errors.rejectValue(fieldName,"EHT-E-0004", new String[]{fieldLabel}, fieldLabel+" - Invalid input [EHT-E-0004]"); 
		}
	}
	
	public void validateSpecialCharacter(Object dto, Errors errors, String fieldName, String fieldLabel){
		String property = null;
		
		try {
			property = BeanUtils.getProperty(dto, fieldName);
		} catch (Exception e) {
		}
		
		if(property == null || "".equals(property.trim())){
			return ;
		}
		
		String regex = "\\pP|\\pS";
		
		if(StringUtils.isMatched(property, regex)){
			errors.rejectValue(fieldName,"EHT-E-0004", new String[]{fieldLabel}, fieldLabel+" - Invalid input [EHT-E-0004]"); 
		}
	} 
}

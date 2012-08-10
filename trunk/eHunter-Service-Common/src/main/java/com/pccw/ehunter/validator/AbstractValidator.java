package com.pccw.ehunter.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pccw.ehunter.utility.StringUtils;

public abstract class AbstractValidator implements Validator{
	
	public void validateRequired(Errors errors, String fieldName, String filedValue , String filedLabel){		
		if(StringUtils.isEmpty(filedValue)){
			errors.rejectValue(fieldName, "EHT-E-0002", new String[]{filedLabel}, filedLabel +  " is required. [EHT-E-0002]");
		}
	}
	
	public void validateStringLength(Errors errors, String fieldName,String fieldValue , String fieldLabel, int maxLength){
		if(StringUtils.isEmpty(fieldValue)){
			return ;
		}
		//check chs and replace with **
		String chsRegex = "[\u4e00-\u9fa5]";
		if(StringUtils.isMatched(fieldValue, chsRegex)){
			fieldValue = fieldValue.replaceAll(chsRegex, "**");
		}
		if(fieldValue.trim().length()>maxLength){
			errors.rejectValue(fieldName,"EHT-E-0003", new String[]{fieldLabel, "" + maxLength},fieldLabel+" out of the max length(" + maxLength + ") [EHT-E-0003]"); 
		}
	}
	
	public void validateOnlyNumberic(Errors errors, String fieldName, String fieldValue , String fieldLabel){
		if(StringUtils.isEmpty(fieldValue)){
			return ;
		}
		if(!StringUtils.isAllDigit(fieldValue)){
			errors.rejectValue(fieldName,"EHT-E-0004", new String[]{fieldLabel}, fieldLabel+" - Invalid input [EHT-E-0004]"); 
		}
	}
	
	public void validateOnlyAlphanumeric(Errors errors, String fieldName, String fieldValue , String fieldLabel){
		if(StringUtils.isEmpty(fieldValue)){
			return ;
		}
		
		if(!StringUtils.isAllAlphanumeric(fieldValue)){
			errors.rejectValue(fieldName,"EHT-E-0004", new String[]{fieldLabel}, fieldLabel+" - Invalid input [EHT-E-0004]"); 
		}
	}
	
	public void validateSpecialCharacter(Errors errors, String fieldName,String fieldValue , String fieldLabel){
		if(StringUtils.isEmpty(fieldValue)){
			return ;
		}
		
		String regex = "\\pP|\\pS";
		
		if(StringUtils.isMatched(fieldValue, regex)){
			errors.rejectValue(fieldName,"EHT-E-0004", new String[]{fieldLabel}, fieldLabel+" - Invalid input [EHT-E-0004]"); 
		}
	} 
	
	public void validateEmail(Errors errors , String fieldName , String fieldValue , String fieldLabel){
		if(StringUtils.isEmpty(fieldValue)){
			return ;
		}
		
		String regex = "^(\\w+((-\\w+)|(\\.\\w+))*)\\+\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
		
		if(StringUtils.isMatched(fieldValue, regex)){
			errors.rejectValue(fieldName,"EHT-E-0004", new String[]{fieldLabel}, fieldLabel+" - Invalid input [EHT-E-0004]"); 
		}
	}
}

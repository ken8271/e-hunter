package com.pccw.ehunter.validator;

import java.util.Date;

import org.apache.commons.validator.EmailValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pccw.ehunter.convertor.SimpleDateConvertor;
import com.pccw.ehunter.convertor.SimpleDateTimeConvertor;
import com.pccw.ehunter.dto.SimpleDateDTO;
import com.pccw.ehunter.dto.SimpleDateTimeDTO;
import com.pccw.ehunter.utility.DateUtils;
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
	
	public void validateOnlyAlphabet(Errors errors, String fieldName, String fieldValue , String fieldLabel){
		if(StringUtils.isEmpty(fieldValue)){
			return ;
		}
		if(!StringUtils.isAllAlphabet(fieldValue)){
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

		if(!EmailValidator.getInstance().isValid(fieldValue)){
			errors.rejectValue(fieldName,"EHT-E-0004", new String[]{fieldLabel}, fieldLabel+" - Invalid input [EHT-E-0004]"); 
		}
	}
	
	public void validateDate(Errors errors, String YY,String MON,String DD,String fieldName, String fieldLabel) {
		if(StringUtils.isEmpty(YY) && StringUtils.isEmpty(MON) && StringUtils.isEmpty(DD)){
			return;
		}
		
		if(YY.trim().length()!=4){
			errors.rejectValue(fieldName,"EHT-E-0004", new String[]{fieldLabel}, fieldLabel+" - Invalid input [EHT-E-0004]");
			return;
		}
		try {
			Date parseDate = DateUtils.parseDate(YY,MON,DD);
			Integer.parseInt(DD);
			if (parseDate==null){
				errors.rejectValue(fieldName,"EHT-E-0004", new String[]{fieldLabel}, fieldLabel+" - Invalid input [EHT-E-0004]");
			}
		} catch (Exception e) {
			errors.rejectValue(fieldName,"EHT-E-0004", new String[]{fieldLabel}, fieldLabel+" - Invalid input [EHT-E-0004]");
		} 
		
	}
	
	public void validateDate(Errors errors,  String filedName , SimpleDateDTO dto , String fieldLabel) {
		if(dto == null){
			return ;
		}
		
		validateDate(errors, dto.getYear() , dto.getMonth() , dto.getDay() , filedName , fieldLabel);
	}

	public void validateDateTime(Errors errors,
			String YY,String MON,String DD,String HH,String MM, String fieldName, String fieldLabel) {
		if(StringUtils.isEmpty(YY)&&StringUtils.isEmpty(MON)&&StringUtils.isEmpty(DD)&&StringUtils.isEmpty(HH)&&StringUtils.isEmpty(MM)){
			return;
		}
		if(YY.trim().length()!=4){
			errors.rejectValue(fieldName,"EHT-E-0004", new String[]{fieldLabel}, fieldLabel+" - Invalid input [EHT-E-0004]");
			return;
		}
		try {
			Date parseDate = DateUtils.parseDate(YY,MON,DD,HH,MM,"00");
			if (parseDate==null){
				errors.rejectValue(fieldName,"EHT-E-0004", new String[]{fieldLabel}, fieldLabel+" - Invalid input [EHT-E-0004]");
			}
		} catch (Exception e) {
			errors.rejectValue(fieldName,"EHT-E-0004", new String[]{fieldLabel}, fieldLabel+" - Invalid input [EHT-E-0004]");
		}
	}
	
	public void validateDateTime(Errors errors,  String filedName , SimpleDateTimeDTO dto , String fieldLabel) {
		if(dto == null){
			return ;
		}
		
		validateDateTime(errors, dto.getYear() , dto.getMonth() , dto.getDay() , dto.getHour() , dto.getMinute() , filedName , fieldLabel);
	}

	public static void compareDate(Errors errors, String fieldName, 
			SimpleDateDTO fromDateDto,SimpleDateDTO toDateDto,
			String messageStart, String messageEnd){
		if(fromDateDto == null || toDateDto == null){
			return ;
		}
		
		Date fromDate = null;
		Date toDate = null;
		try {
			fromDate = SimpleDateConvertor.toDate(fromDateDto);
			toDate = SimpleDateConvertor.toDate(toDateDto);
			
			if (fromDate==null || toDate==null){
				return;
			}
			
			if(fromDate.after(toDate)){
				errors.rejectValue(fieldName, "EHT-E-0008", new String[]{messageEnd, messageStart}, "");
			}
		}catch (Exception e) {
		}
	}
	
	public static void compareDateTime(Errors errors, String fieldName, 
			SimpleDateTimeDTO fromDateDto,SimpleDateTimeDTO toDateDto,
			String messageStart, String messageEnd){
		if(fromDateDto == null || toDateDto == null){
			return ;
		}
		
		Date fromDate = null;
		Date toDate = null;
		try {
			fromDate = SimpleDateTimeConvertor.toDate(fromDateDto);
			toDate = SimpleDateTimeConvertor.toDate(toDateDto);
			
			if (fromDate==null || toDate==null){
				return;
			}
			
			if(fromDate.after(toDate)){
				errors.rejectValue(fieldName, "EHT-E-0008", new String[]{messageEnd, messageStart}, "");
			}
		}catch (Exception e) {
		}
	}
}

package cn.org.polaris.validator;

import cn.org.polaris.error.Errors;
import cn.org.polaris.error.ValidationErrors;
import cn.org.polaris.utility.StringUtils;

public abstract class AbstractValidator implements CabordersValidator{
	
	@Override
	public  Errors validate(Object target) {
		Errors errors = initErrors();
		return doInternalValidate(target , errors);
	}
	
	public Errors initErrors() {
		return new ValidationErrors();
	}

	public void validateRequired(Errors errors, String fieldName, String filedValue , String filedLabel){		
		if(StringUtils.isEmpty(filedValue)){
			errors.rejectValue("WPS-E-0001", new String[]{filedLabel}, filedLabel +  " is required. [WPS-E-0001]");
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
			errors.rejectValue("WPS-E-0002", new String[]{fieldLabel, "" + maxLength},fieldLabel+" out of the max length(" + maxLength + ") [WPS-E-0002]"); 
		}
	}
	
	public void validateOnlyNumberic(Errors errors, String fieldName, String fieldValue , String fieldLabel){
		if(StringUtils.isEmpty(fieldValue)){
			return ;
		}
		if(!StringUtils.isAllDigit(fieldValue)){
			errors.rejectValue("WPS-E-0003", new String[]{fieldLabel}, fieldLabel+" - Invalid input [WPS-E-0003]"); 
		}
	}
	
	public void validateOnlyAlphabet(Errors errors, String fieldName, String fieldValue , String fieldLabel){
		if(StringUtils.isEmpty(fieldValue)){
			return ;
		}
		if(!StringUtils.isAllAlphabet(fieldValue)){
			errors.rejectValue("WPS-E-0003", new String[]{fieldLabel}, fieldLabel+" - Invalid input [WPS-E-0003]"); 
		}
	}
	
	public void validateOnlyAlphanumeric(Errors errors, String fieldName, String fieldValue , String fieldLabel){
		if(StringUtils.isEmpty(fieldValue)){
			return ;
		}
		
		if(!StringUtils.isAllAlphanumeric(fieldValue)){
			errors.rejectValue("WPS-E-0003", new String[]{fieldLabel}, fieldLabel+" - Invalid input [WPS-E-0003]"); 
		}
	}
	
	public void validateSpecialCharacter(Errors errors, String fieldName,String fieldValue , String fieldLabel){
		if(StringUtils.isEmpty(fieldValue)){
			return ;
		}
		
		String regex = "\\pP|\\pS";
		
		if(StringUtils.isMatched(fieldValue, regex)){
			errors.rejectValue("WPS-E-0003", new String[]{fieldLabel}, fieldLabel+" - Invalid input [WPS-E-0003]"); 
		}
	} 

}

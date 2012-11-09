package com.pccw.ehunter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.ChangePasswordDTO;
import com.pccw.ehunter.utility.SecurityUtils;
import com.pccw.ehunter.utility.StringEncryptUtils;
import com.pccw.ehunter.utility.StringUtils;

@Component("changePasswordValidator")
public class ChangePasswordValidator  extends AbstractValidator{

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return ChangePasswordDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {		
		ChangePasswordDTO dto = (ChangePasswordDTO)target;
		
		validateRequired(errors, "oldPassword", dto.getOldPassword(), "原密码");
		validateRequired(errors, "newPassword", dto.getNewPassword(), "新密码");
		validateRequired(errors, "confirmPassword", dto.getConfirmPassword(), "确认密码");
		
		String currPassword = SecurityUtils.getUser().getPassword();
		
		if(!StringUtils.isEmpty(dto.getOldPassword())){
			if( !currPassword.equals(StringEncryptUtils.encryptDefault(dto.getOldPassword()))){				
				errors.rejectValue("oldPassword","EHT-E-0016", null,"Incorrect old password , please check and input again. [EHT-E-0016]"); 
			}else {
				if(!StringUtils.isEmpty(dto.getNewPassword()) && dto.getNewPassword().equals(dto.getOldPassword())){
					errors.rejectValue("newPassword","EHT-E-0017", null,"The new password should not be same with the old password. [EHT-E-0017]"); 	
				}
			}
		}
		
		if(!StringUtils.isEmpty(dto.getNewPassword()) 
				&& !StringUtils.isEmpty(dto.getConfirmPassword())
				&& !dto.getConfirmPassword().equals(dto.getNewPassword())){
			errors.rejectValue("confirmPassword","EHT-E-0013", null,"The passwords you input are not the same. [EHT-E-0013]"); 
			return ;
		}
		
		if(!isBothAlphanumeric(dto.getNewPassword())){
			errors.rejectValue("newPassword","EHT-E-0014", null,"The Password violates the password policy. [EHT-E-0014]"); 
			return ;
		}
		
		if(dto.getNewPassword().length() != 8){
			errors.rejectValue("password","EHT-E-0014", null,"The Password violates the password policy. [EHT-E-0014]"); 
			return ;
		}
	}
	
	private boolean isBothAlphanumeric(String input){
		if(StringUtils.isEmpty(input)){
			return false;
		}
		
		int countOfAlpha = 0;
		int countOfNumeric = 0;
		for(int i=0 ; i<input.length() ; i++){
			char c = input.charAt(i);
			if(c>='0' && c<='9'){
				countOfNumeric++;
			}else if(c>='A'&&c<='Z' || c>='a'&&c<='z'){
				countOfAlpha++;
			}
		}
		
		if(countOfAlpha>0 && countOfNumeric>0 && countOfAlpha + countOfNumeric == input.length()){
			return true;
		}
		return false;
	}
}

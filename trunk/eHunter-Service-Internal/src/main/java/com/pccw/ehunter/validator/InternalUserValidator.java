package com.pccw.ehunter.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.InternalUserDTO;
import com.pccw.ehunter.service.InternalUserManagementService;
import com.pccw.ehunter.utility.StringUtils;

@Component("internalUserValidator")
public class InternalUserValidator extends AbstractValidator{
	
	@Autowired
	private InternalUserManagementService internalUserService;

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return InternalUserDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		InternalUserDTO dto = (InternalUserDTO)target;
		
		validateRequired(errors, "loginId", dto.getLoginId(), "登录名");
		validateStringLength(errors, "loginId", dto.getLoginId(), "登录名", 20);
		
		String loginID = StringUtils.isEmpty(dto.getLoginId()) ? StringUtils.EMPTY_STRING : dto.getLoginId();
		validateSpecialCharacter(errors, "loginId", loginID.replaceAll("\\.", loginID), "登录名");
		
		if(!StringUtils.isEmpty(dto.getLoginId())){
			InternalUserDTO temp = internalUserService.getInternalUserByLoginID(dto.getLoginId());			
			if(temp != null && !temp.getUserRecId().equals(dto.getUserRecId())){
				errors.rejectValue("loginId", "EHT-E-0006", new String[]{"登录名"}, "The login id has been regited [EHT-E-0006]");
			}
		}
		
		validateOnlyAlphanumeric(errors, "staffId", dto.getStaffId(), "员工号");
		validateStringLength(errors, "staffId", dto.getStaffId(), "员工号", 10);
		
		validateRequired(errors, "cnName", dto.getCnName(), "中文名");
		validateStringLength(errors, "cnName", dto.getCnName(), "中文名", 30);
		validateSpecialCharacter(errors, "cnName", dto.getCnName(), "中文名");
		
		validateStringLength(errors, "enName", dto.getEnName(), "英文名", 30);
		if(!StringUtils.isEmpty(dto.getEnName())){
			validateOnlyAlphanumeric(errors, "enName", dto.getEnName().replaceAll("\\.", "").replaceAll("\\s", ""), "英文名");
		}
		
		validateRequired(errors, "email", dto.getEmail(), "邮箱");
		validateStringLength(errors, "email", dto.getEmail(), "邮箱", 50);
		validateEmail(errors, "email", dto.getEmail(), "邮箱");
		
		validateStringLength(errors, "mobile", dto.getMobile(), "手机", 12);
		validateOnlyNumberic(errors, "mobile", dto.getMobile(), "手机");
		
		validateRequired(errors, "roleStr", dto.getRoleStr(), "用户角色");
	}
	
	public void validatePassword(Object target , Errors errors){
		if(target == null){
			return ;
		}
		
		InternalUserDTO dto = (InternalUserDTO)target;
	
		validateRequired(errors, "password", dto.getPassword(), "用户密码");
		validateRequired(errors, "confirmPassword", dto.getConfirmPassword(), "确认密码");
		
		if(!StringUtils.isEmpty(dto.getPassword()) 
				&& !StringUtils.isEmpty(dto.getConfirmPassword())
				&& !dto.getConfirmPassword().equals(dto.getPassword())){
			errors.rejectValue("confirmPassword","EHT-E-0013", null,"The passwords you input are not the same. [EHT-E-0013]"); 
			return ;
		}
		
		if(!isBothAlphanumeric(dto.getPassword())){
			errors.rejectValue("password","EHT-E-0014", null,"The Password violates the password policy. [EHT-E-0014]"); 
			return ;
		}
		
		if(dto.getPassword().length() != 8){
			errors.rejectValue("password","EHT-E-0014", null,"The Password violates the password policy. [EHT-E-0014]"); 
			return ;
		}
		
	}
	
	public void validateSave(Object target , Errors errors){
		validate(target, errors);
		validatePassword(target, errors);
	}
	
	public void ValidateUpate(Object target , Errors errors){
		InternalUserDTO dto = (InternalUserDTO)target;
		validate(target, errors);
		validateRequired(errors, "accountStatus", dto.getAccountStatus(), "帐号状态");
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

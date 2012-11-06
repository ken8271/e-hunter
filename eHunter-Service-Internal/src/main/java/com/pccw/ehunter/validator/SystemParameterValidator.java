package com.pccw.ehunter.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.dto.SystemParameterDTO;
import com.pccw.ehunter.service.SystemParameterService;
import com.pccw.ehunter.utility.StringUtils;

@Component("systemParameterValidator")
public class SystemParameterValidator extends AbstractValidator{
	
	@Autowired
	private SystemParameterService systemParameterService;

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return SystemParameterDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SystemParameterDTO dto = (SystemParameterDTO)target;
		
		validateRequired(errors, "parameterKey", dto.getParameterKey(), "参数代号");
		validateStringLength(errors, "parameterKey", dto.getParameterKey(), "参数代号", 35);
		
		validateRequired(errors, "valueType", dto.getValueType(), "参数类型");
		
		validateRequired(errors, "value", dto.getValue(), "参数值");
		validateStringLength(errors, "value", dto.getValue(), "参数值", 100);
		
		if(CommonConstant.TYPE_OF_NUMBER.equals(dto.getValueType())){
			validateOnlyNumberic(errors, "value", dto.getValue(), "参数值");
		}else if(CommonConstant.TYPE_OF_STRING.equals(dto.getValueType())){
			validateOnlyAlphanumeric(errors, "value", dto.getValue(), "参数值");
		}else if(CommonConstant.TYPE_OF_DATE.equals(dto.getValueType())){
			
		}
		
		validateStringLength(errors, "description", dto.getDescription(), "参数描述", 100);
	}
	
	public void validateSave(SystemParameterDTO dto , Errors errors){
		validate(dto, errors);
		if(!StringUtils.isEmpty(dto.getParameterKey())){
			SystemParameterDTO temp = systemParameterService.getSystemParameterByKey(dto.getParameterKey());
			if(temp != null){
				errors.rejectValue("parameterKey", "EHT-E-0006", new String[]{"参数代号"}, "The parameter key has been regited [EHT-E-0006]");
			}
		}
	}
	
	public void validateUpadate(SystemParameterDTO dto ,Errors errors){
		validate(dto, errors);
	}

}

package com.pccw.ehunter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.CertDTO;

@Component("certValidator")
public class CertValidator extends AbstractValidator{

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return CertDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CertDTO dto = (CertDTO)target;
		
		validateRequired(errors, "certName", dto.getCertName(), "证书名称");
		validateStringLength(errors, "certName", dto.getCertName(), "证书名称", 50);
		
		if(dto.getGainedDateDto() != null){
			validateRequired(errors, "gainedDateDto.day", dto.getGainedDateDto().getDay(), "获得时间");			
		}else {
			errors.rejectValue("gainedDateDto.day", "EHT-E-0002", new String[]{"获得时间"}, "Gained Time is required. [EHT-E-0002]");
		}
		
		validateDate(errors, "gainedDateDto.day",dto.getGainedDateDto(), "获得时间");
		
		validateStringLength(errors, "description", dto.getDescription(), "说明", 500);
	}

}

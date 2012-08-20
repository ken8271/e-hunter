package com.pccw.ehunter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.TrainingExperienceDTO;

@Component("trnExpValidator")
public class TrainingExperienceValidator extends AbstractValidator{

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return TrainingExperienceDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TrainingExperienceDTO dto = (TrainingExperienceDTO)target;
		
		if(dto.getFromDateDto() != null){
			validateRequired(errors, "fromDateDto.day", dto.getFromDateDto().getDay(), "起始时间");			
		}else {
			errors.rejectValue("fromDateDto.day", "EHT-E-0002", new String[]{"起始时间"}, "Begin Time is required. [EHT-E-0002]");
		}
		
		validateDate(errors, "fromDateDto.day", dto.getFromDateDto(), "起始时间");
		
		if(dto.getToDateDto() != null){
			validateRequired(errors, "toDateDto.day", dto.getToDateDto().getDay(), "结束时间");			
		}else {
			errors.rejectValue("toDateDto.day", "EHT-E-0002", new String[]{"结束时间"}, "End Time is required. [EHT-E-0002]");
		}
		
		validateDate(errors, "toDateDto.day", dto.getToDateDto(), "结束时间");
		
		validateRequired(errors, "organization", dto.getOrganization(), "培训机构");
		validateStringLength(errors, "organization", dto.getOrganization(), "培训机构", 50);
		
		validateStringLength(errors, "address", dto.getAddress(), "培训地点", 300);
		
		validateRequired(errors, "course", dto.getCourse(), "培训课程");
		validateStringLength(errors, "course", dto.getCourse(), "培训课程", 50);
		
		validateStringLength(errors, "cert", dto.getCert(), "获得证书", 50);
		
		validateStringLength(errors, "description", dto.getDescription(), "详细描述", 100);
	}

}

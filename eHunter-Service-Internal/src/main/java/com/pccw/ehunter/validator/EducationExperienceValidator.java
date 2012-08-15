package com.pccw.ehunter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.EducationExperienceDTO;

@Component("eduExpValidator")
public class EducationExperienceValidator extends AbstractValidator{

	@Override
	public boolean supports(Class clazz) {
		return EducationExperienceDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		EducationExperienceDTO dto = (EducationExperienceDTO)object;
		
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
		
		validateRequired(errors, "school", dto.getSchool(), "学校");
		validateStringLength(errors, "school", dto.getSchool(), "学校", 50);
		
		validateRequired(errors, "major", dto.getMajor(), "专业");
		validateRequired(errors, "degree", dto.getDegree(), "学位");
	}

}

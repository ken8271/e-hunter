package com.pccw.ehunter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.ProjectExperienceDTO;

@Component("prjExpValidator")
public class ProjectExperienceValidator extends AbstractValidator{

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return ProjectExperienceDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProjectExperienceDTO dto = (ProjectExperienceDTO)target;
		
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
		
		validateRequired(errors, "name", dto.getName(), "项目名称");
		validateStringLength(errors, "name", dto.getName(), "项目名称", 50);
		
		validateRequired(errors, "duty", dto.getDuty(), "职责描述");
		validateStringLength(errors, "duty", dto.getDuty(), "职责描述", 300);
		
		validateRequired(errors, "description", dto.getDescription(), "项目描述");
		validateStringLength(errors, "description", dto.getDescription(), "项目描述", 1000);
	}

}

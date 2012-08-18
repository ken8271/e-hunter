package com.pccw.ehunter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.ResumeDTO;

@Component("resumeValidator")
public class ResumeValidator extends AbstractValidator{

	@Override
	public boolean supports(Class clazz) {
		return ResumeDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(target == null){
			return ;
		}
		
		ResumeDTO dto = (ResumeDTO)target;
		
		validateRequired(errors, "resumeDto.name", dto.getName(), "简历名称");
		validateRequired(errors, "resumeDto.language", dto.getLanguage(), "语言版本");
	}

}

package com.pccw.ehunter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.ProfessionalSkillDTO;

@Component("skillValidator")
public class ProfessionalSkillValidator extends AbstractValidator{

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return ProfessionalSkillDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProfessionalSkillDTO dto = (ProfessionalSkillDTO)target;
		
		validateRequired(errors, "categoryCode", dto.getCategoryCode(), "技能类型");
		validateRequired(errors, "skillName", dto.getSkillName(), "技能名称");
		validateStringLength(errors, "skillName", dto.getSkillName(), "技能名称", 50);
		
		validateRequired(errors, "duration", dto.getDuration(), "使用时间");
		validateOnlyNumberic(errors, "duration", dto.getDuration()	, "使用时间");
		
		validateRequired(errors, "levelCode", dto.getLevelCode(), "掌握程度");
	}

}

package com.pccw.ehunter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.LanguageAbilityDTO;

@Component("languageValidator")
public class LanguageAbilityValidator extends AbstractValidator{

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return LanguageAbilityDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LanguageAbilityDTO dto = (LanguageAbilityDTO)target;
		
		validateRequired(errors, "languageCategory", dto.getLanguageCategory(), "外语语种");
		validateRequired(errors, "ablitityOfRW", dto.getAblitityOfRW(), "读写能力");
		validateRequired(errors, "ablitityOfLS", dto.getAblitityOfLS(), "听说能力");
	}

}

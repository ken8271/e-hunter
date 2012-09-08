package com.pccw.ehunter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.PositionRequirementDTO;

@Component("postRequireValidator")
public class PositionRequirementValidator extends AbstractValidator{

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return PositionRequirementDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PositionRequirementDTO dto = (PositionRequirementDTO)target;
		
	}

}

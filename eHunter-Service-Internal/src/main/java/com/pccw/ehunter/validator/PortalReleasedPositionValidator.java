package com.pccw.ehunter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.portal.PortalReleasedPositionDTO;

@Component("releasedPositionValidator")
public class PortalReleasedPositionValidator  extends AbstractValidator{

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return PortalReleasedPositionDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PortalReleasedPositionDTO dto = (PortalReleasedPositionDTO)target;
		
		validateRequired(errors, "title", dto.getTitle(), "标题");
		validateStringLength(errors, "title", dto.getTitle(), "标题", 100);
		
		validateRequired(errors, "content", dto.getContent(), "内容");
	}
	
}

package com.pccw.ehunter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.portal.PortalInformationCenterDTO;

@Component("infoCenterValidator")
public class PortalInformationCenterValidator  extends AbstractValidator{

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return PortalInformationCenterDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PortalInformationCenterDTO dto = (PortalInformationCenterDTO)target;
		
		validateRequired(errors, "title", dto.getTitle(), "标题");
		validateStringLength(errors, "title", dto.getTitle(), "标题", 100);
		
		//validateRequired(errors, "content", dto.getContent(), "内容");
	}
	
}

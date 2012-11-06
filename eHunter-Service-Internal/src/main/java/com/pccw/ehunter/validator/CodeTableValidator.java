package com.pccw.ehunter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.CodeTableDTO;

@Component("codetableValidator")
public class CodeTableValidator extends AbstractValidator{

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return CodeTableDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CodeTableDTO dto = (CodeTableDTO)target;
		
		validateRequired(errors, "name", dto.getName(), "代码表名称");
		validateStringLength(errors, "name", dto.getName(), "", 50);
		
		validateRequired(errors, "description", dto.getDescription(), "代码表描述");
		validateStringLength(errors, "description", dto.getDescription(), "代码表描述", 100);
		
		validateRequired(errors, "reference", dto.getReference(), "数据库表名称");
		validateStringLength(errors, "reference", dto.getReference(), "数据库表名称", 50);
		
		validateRequired(errors, "activeIndicator", dto.getActiveIndicator(), "是否启用");
	}

}

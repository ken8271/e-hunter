package com.pccw.ehunter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.CustomerContactHistoryDTO;

@Component("customerContactHistoryValidator")
public class CustomerContactHistoryValidator extends AbstractValidator{

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return CustomerContactHistoryDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CustomerContactHistoryDTO dto = (CustomerContactHistoryDTO)target;
		
		if(dto.getCustomerDto() != null){			
			validateRequired(errors, "customerDto.systemCustRefNum", dto.getCustomerDto().getSystemCustRefNum(), "客户编号");
		}else {
			errors.rejectValue("customerDto.systemCustRefNum", "EHT-E-0002", new String[]{"客户编号"}, "Customer No. is required. [EHT-E-0002]");
		}
		
		if(dto.getResponsePersonDto() != null){			
			validateRequired(errors, "responsePersonDto.systemRespRefNum", dto.getResponsePersonDto().getSystemRespRefNum(), "客户公司联系人");
		}else {
			errors.rejectValue("responsePersonDto.systemRespRefNum", "EHT-E-0002", new String[]{"客户公司联系人"}, "Customer Response Person is required. [EHT-E-0002]");
		}
		
		validateRequired(errors, "content", dto.getContent(), "详细记录");
		validateStringLength(errors, "content", dto.getContent(), "详细记录", 4000);
		
		validateStringLength(errors, "remark", dto.getRemark(), "备注", 300);
	}
	
}

package com.pccw.ehunter.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.constant.CustomerIndicator;
import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.dto.CustomerGroupDTO;
import com.pccw.ehunter.dto.CustomerResponsePersonDTO;
import com.pccw.ehunter.service.CustomerRegistrationService;
import com.pccw.ehunter.utility.StringUtils;

@Component("customerValidator")
public class CustomerValidator extends AbstractValidator{
	
	@Autowired
	private CustomerRegistrationService custRegtService;
	
	@Override
	public boolean supports(Class clazz) {
		return CustomerDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		CustomerDTO customerDto = (CustomerDTO)object;
		CustomerGroupDTO groupDto = customerDto.getCustGroup();
		CustomerResponsePersonDTO rpDto = customerDto.getCustRespPerson();
		
		String custIndicator = customerDto.getGroupIndicator();
		
		if(StringUtils.isEmpty(custIndicator)){
			errors.rejectValue("groupIndicator", "EHT-E-0002", new String[]{"客户类型"}, "");
		}else if(CustomerIndicator.CUSTOMER_GROUP.equals(custIndicator)){
//			validateRequired(errors, "custGroup.fullName", groupDto.getFullName(), "集团名称");
//			validateRequired(errors, "custGroup.shortName", groupDto.getShortName(), "集团简称");	
			
			if(!StringUtils.isEmpty(groupDto.getFullName()) &&  custRegtService.getCountOfGroupsByFullName(groupDto.getFullName())>0){
				errors.rejectValue("custGroup.fullName", "EHT-E-0005", null , "The group full name is existed.[EHT-E-0005]");
			}
		}else if(CustomerIndicator.CUSTOMER_SUBSIDIARY.equals(custIndicator)){
//			validateRequired(errors, "custGroup.fullName", groupDto.getFullName(), "集团名称");
//			validateRequired(errors, "custGroup.shortName", groupDto.getShortName(), "集团简称");
		}
		
		validateRequired(errors, "fullName", customerDto.getFullName(), "公司名称");
		validateRequired(errors, "type", customerDto.getType(), "公司性质");
		validateRequired(errors, "size", customerDto.getSize(), "公司规模");
		validateRequired(errors, "grade", customerDto.getGrade(), "客户等级");
		validateRequired(errors, "status", customerDto.getStatus(), "客户状态");
		
		validateCustomerResponsePerson(rpDto,errors);
	}

	private void validateCustomerResponsePerson(
			CustomerResponsePersonDTO rpDto, Errors errors) {
		validateRequired(errors, "custRespPerson.name", rpDto.getName(), "联系人姓名");
		validateRequired(errors, "custRespPerson.positionType", rpDto.getPositionType(), "联系人职位类型");
		validateRequired(errors, "custRespPerson.positionName", rpDto.getPositionName(), "联系人职位");
		validateRequired(errors, "custRespPerson.telephoneDto.phoneNumber", rpDto.getTelephoneDto().getPhoneNumber(), "联系人手机");
		validateRequired(errors, "custRespPerson.email", rpDto.getEmail(), "联系人邮箱");
		validateRequired(errors, "custRespPerson.status", rpDto.getStatus(), "联系人状态");
		
		validateOnlyNumberic(rpDto, errors, "custRespPerson.telephoneDto.phoneNumber", "联系人手机");
	}

}

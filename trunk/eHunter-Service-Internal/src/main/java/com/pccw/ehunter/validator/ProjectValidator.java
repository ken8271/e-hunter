package com.pccw.ehunter.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.CustomerPagedCriteria;
import com.pccw.ehunter.dto.ProjectDTO;
import com.pccw.ehunter.service.CustomerCommonService;

@Component("projectValidator")
public class ProjectValidator extends AbstractValidator{
	
	@Autowired
	private CustomerCommonService custCommonService;

	@Override
	public boolean supports(Class clazz) {
		return ProjectDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProjectDTO dto = (ProjectDTO)target;
		validateRequired(errors, "projectName", dto.getProjectName(), "项目名称");
		validateStringLength(errors, "projectName", dto.getProjectName(), "项目名称", 50);
		
		validateRequired(errors, "systemCustRefNum", dto.getSystemCustRefNum(), "客户编号");
		
		CustomerPagedCriteria pagedCriteria = new CustomerPagedCriteria();
		pagedCriteria.setSystemCustRefNum(dto.getSystemCustRefNum());
		int count = custCommonService.getCustomersCountByCriteria(pagedCriteria);
		
		if(count == 0){
			errors.rejectValue("systemCustRefNum", "EHT-E-0007", new String[]{"客户编号"}, "Customer ID" +  " is not existed. [EHT-E-0007]");
		}
	}

}

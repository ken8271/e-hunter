package com.pccw.ehunter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.JobIntentionDTO;

@Component("intentionValidator")
public class JobIntentionValidator extends AbstractValidator{

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return JobIntentionDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		JobIntentionDTO dto = (JobIntentionDTO)target;
		
		validateRequired(errors, "employmentCategory", dto.getEmploymentCategory(), "期望工作性质");
		
		validateRequired(errors, "expectAddress", dto.getExpectAddress(), "期望工作地点");
		validateStringLength(errors, "expectAddress", dto.getExpectAddress(), "期望工作地点", 50);
		
		validateRequired(errors, "expectPosition", dto.getExpectPosition(), "期望从事职业");

		validateRequired(errors, "expectIndustry", dto.getExpectIndustry(), "期望从事行业");
		
		validateRequired(errors, "expectSalary", dto.getExpectSalary(), "期望月薪");
		validateStringLength(errors, "expectSalary", dto.getExpectSalary(), "期望月薪", 10);
		validateOnlyNumberic(errors, "expectSalary", dto.getExpectSalary(), "期望月薪");
	}

}

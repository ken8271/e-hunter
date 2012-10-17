package com.pccw.ehunter.validator;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.convertor.SimpleDateConvertor;
import com.pccw.ehunter.dto.EmploymentHistoryDTO;
import com.pccw.ehunter.utility.StringUtils;

@Component("employmentHistoryValidator")
public class EmploymentHistoryValidator extends AbstractValidator{

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class arg0) {
		return false;
	}

	@Override
	public void validate(Object object, Errors errors) {
		
	}
	
	public void validateEmploymentHistory(EmploymentHistoryDTO target, Errors errors){
		if(target == null) return ;
		
		if(target.getBeginTimeDto()!= null){
			validateRequired(errors, "beginTimeDto.day", target.getBeginTimeDto().getDay(), "入职时间");			
		}else {
			errors.rejectValue("beginTimeDto.day", "EHT-E-0002", new String[]{"入职时间"}, "Begin Time is required. [EHT-E-0002]");
		}
		
		validateDate(errors, "beginTimeDto.day", target.getBeginTimeDto(), "入职时间");
		
		if(target.getEndTimeDto() != null && !StringUtils.isEmpty(target.getEndTimeDto().getDay())){			
			validateDate(errors, "endTimeDto.day", target.getEndTimeDto(), "离职时间");
		}
		
		if(target.getBeginTimeDto() != null && !StringUtils.isEmpty(target.getBeginTimeDto().getDay())){
			compareDate(errors, "beginTimeDto.day", target.getBeginTimeDto(), SimpleDateConvertor.toSimpleDate(new Date()), "入职时间", "当前时间");
		}
		
		if(target.getEndTimeDto() != null && !StringUtils.isEmpty(target.getEndTimeDto().getDay())){
			compareDate(errors, "endTimeDto.day", target.getEndTimeDto(), SimpleDateConvertor.toSimpleDate(new Date()), "离职时间", "当前时间");
		}
		
		if(target.getBeginTimeDto() != null && target.getEndTimeDto() != null
				&& !StringUtils.isEmpty(target.getBeginTimeDto().getDay())
				&& !StringUtils.isEmpty(target.getEndTimeDto().getDay())){
			compareDate(errors, "endTimeDto.day", target.getBeginTimeDto(), target.getEndTimeDto(), "入职时间", "离职时间");
		}
		
		validateRequired(errors, "companyName", target.getCompanyName(), "企业名称");
		validateStringLength(errors, "companyName", target.getCompanyName(), "企业名称"	, 50);
		
		validateRequired(errors, "industry", target.getIndustryCategory(), "行业类型");
		validateRequired(errors, "industry", target.getIndustry(), "行业类型");
		
		validateRequired(errors, "positions", target.getPositions(), "职位类型");
	}

}

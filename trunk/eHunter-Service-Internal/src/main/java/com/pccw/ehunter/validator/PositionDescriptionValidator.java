package com.pccw.ehunter.validator;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.convertor.SimpleDateConvertor;
import com.pccw.ehunter.dto.PositionDescriptionDTO;

@Component("postDescValidator")
public class PositionDescriptionValidator extends AbstractValidator{

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return PositionDescriptionDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PositionDescriptionDTO dto = (PositionDescriptionDTO)target;
		
		validateRequired(errors, "positionCategory", dto.getPositionCategory(), "职位分类");
		validateRequired(errors, "position", dto.getPosition(), "职位分类");
		
		validateRequired(errors, "positionName", dto.getPositionName(), "职位名称");
		validateStringLength(errors, "positionName", dto.getPositionName(), "职位名称", 30);
		
		validateRequired(errors, "department", dto.getDepartment(), "部门名称");
		validateStringLength(errors, "department", dto.getDepartment(), "部门名称", 50);
		
		validateRequired(errors, "reportTarget", dto.getReportTarget(), "汇报对象");
		validateStringLength(errors, "reportTarget", dto.getReportTarget(), "汇报对象", 50);
		
		if(dto.getExpiryDateDto() != null){
			validateRequired(errors, "expiryDateDto.day", dto.getExpiryDateDto().getDay(), "截止日期");			
		}else {
			errors.rejectValue("expiryDateDto.day", "EHT-E-0002", new String[]{"截止日期"}, "Expiry Date is required. [EHT-E-0002]");
		}
		validateDate(errors, "expiryDateDto.day", dto.getExpiryDateDto(), "截止日期");
		
		Date expiryDate = SimpleDateConvertor.toDate(dto.getExpiryDateDto());
		if(expiryDate != null && new Date().after(expiryDate)){
			errors.rejectValue("expiryDateDto.day", "EHT-E-0008", new String[]{"截至日期", "当前日期"}, "");
		}
		
		validateRequired(errors, "salaryTo", dto.getSalaryFrom(), "起始年薪");
		validateOnlyNumberic(errors, "salaryTo", dto.getSalaryFrom(), "起始年薪");
		validateRequired(errors, "salaryTo", dto.getSalaryTo(), "最高年薪");
		validateOnlyNumberic(errors, "salaryTo", dto.getSalaryTo(), "最高年薪");
		
		if(dto.getSalaryCategory()==null || dto.getSalaryCategory().length == 0){
			errors.rejectValue("salaryCategory", "EHT-E-0002", new String[]{"薪资构成"}, "Salary Category is required. [EHT-E-0002]");
		}
		
		validateRequired(errors, "dutyDescription", dto.getDutyDescription(), "职责描述");
		validateStringLength(errors,"dutyDescription", dto.getDutyDescription(), "职责描述" , 4000);
	}

}

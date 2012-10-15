package com.pccw.ehunter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.JobExperienceDTO;

@Component("jobExpValidator")
public class JobExperienceValidator extends AbstractValidator{

	@Override
	public boolean supports(Class clazz) {
		return JobExperienceDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(target == null){
			return ;
		}
		
		JobExperienceDTO dto = (JobExperienceDTO)target;
		
		if(dto.getFromDateDto() != null){
			validateRequired(errors, "fromDateDto.day", dto.getFromDateDto().getDay(), "起始时间");			
		}else {
			errors.rejectValue("fromDateDto.day", "EHT-E-0002", new String[]{"起始时间"}, "Begin Time is required. [EHT-E-0002]");
		}
		
		validateDate(errors, "fromDateDto.day", dto.getFromDateDto(), "起始时间");
		
//		if(dto.getToDateDto() != null){
//			validateRequired(errors, "toDateDto.day", dto.getToDateDto().getDay(), "结束时间");			
//		}else {
//			errors.rejectValue("toDateDto.day", "EHT-E-0002", new String[]{"结束时间"}, "End Time is required. [EHT-E-0002]");
//		}
		
		validateDate(errors, "toDateDto.day", dto.getToDateDto(), "结束时间");
		
		validateRequired(errors, "companyName", dto.getCompanyName(), "企业名称");
		
		if(dto.getCompanyCategoryDto()!= null){
			validateRequired(errors, "companyCategoryDto.categoryCode", dto.getCompanyCategoryDto().getCategoryCode(), "企业性质");
		}
		
		if(dto.getCompanySizeDto() != null){
			validateRequired(errors, "companySizeDto.sizeCode", dto.getCompanySizeDto().getSizeCode(), "企业规模");
		}
		
		if(dto.getIndustryCategoryDto() != null){
			validateRequired(errors, "industryCategoryDto.categoryCode", dto.getIndustryCategoryDto().getCategoryCode()	, "行业类型");
		}
		
		if(dto.getIndustryDto() != null){
			validateRequired(errors, "industryDto.industryCode", dto.getIndustryDto().getIndustryCode(), "行业类型");
		}
		
		validateRequired(errors, "department", dto.getDepartment(), "部门名称");
		
		if(dto.getPositionCategoryDto() != null){
			validateRequired(errors, "positionCategoryDto.typeCode", dto.getPositionCategoryDto().getTypeCode(), "职位类型");
		}
		
		if(dto.getPositionDto() != null){
			validateRequired(errors, "positionDto.typeCode", dto.getPositionDto().getTypeCode(), "职位类型");
		}
		
		validateRequired(errors, "jobDescription", dto.getJobDescription(), "职位描述");
	}

}

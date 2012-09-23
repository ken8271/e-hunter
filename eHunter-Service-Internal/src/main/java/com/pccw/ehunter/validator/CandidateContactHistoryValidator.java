package com.pccw.ehunter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.CandidateContactHistoryDTO;

@Component("candidateContactHistoryValidator")
public class CandidateContactHistoryValidator extends AbstractValidator{

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return CandidateContactHistoryDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CandidateContactHistoryDTO dto = (CandidateContactHistoryDTO)target;
		
		if(dto.getTalentDto() != null){
			validateRequired(errors, "talentDto.talentID", dto.getTalentDto().getTalentID(), "人才编号");
		}else {
			errors.rejectValue("talentDto.talentID", "EHT-E-0002", new String[]{"人才编号"}, "Talent No. is required. [EHT-E-0002]");
		}
		
		if(dto.getProjectDto() != null){
			validateRequired(errors, "projectDto.systemProjectRefNum", dto.getProjectDto().getSystemProjectRefNum(), "项目编号");
		}else {
			errors.rejectValue("projectDto.systemProjectRefNum", "EHT-E-0002", new String[]{"项目编号"}, "Talent No. is required. [EHT-E-0002]");
		}
		
		validateRequired(errors, "record", dto.getRecord(), "详细记录");
		validateStringLength(errors,"record", dto.getRecord(), "详细记录", 4000);
		
		validateStringLength(errors, "remark", dto.getRemark(), "备注", 300);
	}

}

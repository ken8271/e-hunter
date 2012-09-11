package com.pccw.ehunter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.PositionRequirementDTO;
import com.pccw.ehunter.utility.StringUtils;

@Component("postRequireValidator")
public class PositionRequirementValidator extends AbstractValidator{

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return PositionRequirementDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PositionRequirementDTO dto = (PositionRequirementDTO)target;
		
		validateRequired(errors, "workExperience", dto.getWorkExperience(), "总工作年限");
		validateOnlyNumberic(errors, "workExperience", dto.getWorkExperience(), "总工作年限");
		
		validateRequired(errors, "ftEduIndicator", dto.getFtEduIndicator(), "是否统招全日制");
		
		if(dto.getLanguage()==null || dto.getLanguage().length == 0){
			errors.rejectValue("language", "EHT-E-0002", new String[]{"语言要求"}, "Language requirement is required. [EHT-E-0002]");
		}
		
		validateRequired(errors, "duty", dto.getDuty(), "任职资格");
		validateStringLength(errors, "duty", dto.getDuty(), "任职资格", 1000);
		
		if(dto.getKeyWords()==null || dto.getKeyWords().length == 0){
			errors.rejectValue("keyWords[4]", "EHT-E-0009", new String[]{"关键词"}, "At least one keyword is required. [EHT-E-0009]");
		}else {
			boolean isNothingInput = true;
			String[] keywords = dto.getKeyWords();
			for(int i=0 ; i<keywords.length ; i++){
				if(!StringUtils.isEmpty(keywords[i])){
					isNothingInput = false;
				}
				validateStringLength(errors, "keyWords[" + i + "]", keywords[i], "关键词" + i	, 20);
			}
			
			if(isNothingInput){
				errors.rejectValue("keyWords[4]", "EHT-E-0009", new String[]{"关键词"}, "At least one keyword is required. [EHT-E-0009]");
			}
		}
		
		validateRequired(errors, "expectIndustries", dto.getExpectIndustries(), "期望行业");
	}

}

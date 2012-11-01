package com.pccw.ehunter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.pccw.ehunter.dto.UploadedCurriculumVitaeDTO;
import com.pccw.ehunter.utility.FileUtils;

@Component("cvUploadValidator")
public class UploadedCurriculumVitaeValidator extends AbstractValidator{

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return UploadedCurriculumVitaeDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UploadedCurriculumVitaeDTO dto = (UploadedCurriculumVitaeDTO)target;
		
		validateRequired(errors, "talentID", dto.getTalentID(), "人才编号");
		
		validateRequired(errors, "cvName", dto.getCvName(), "简历名称");
		validateStringLength(errors, "cvName", dto.getCvName(), "简历名称", 50);
		
		validateRequired(errors, "language", dto.getLanguage(), "简历语言版本");
		
		if(!FileUtils.WORD_FILE_EXT.equals(dto.getType()) && !FileUtils.PDF_FILE_EXT.equals(dto.getType())){
			errors.rejectValue("type", "EHT-E-0010", null , "\u53ea\u80fd\u4e0a\u4f20word,pdf\u683c\u5f0f\u7684\u7b80\u5386\u6587\u4ef6\u3002 [EHT-E-0010]");
		}
		
		if(Long.parseLong(dto.getSize()) > 5*1024*1024){
			errors.rejectValue("size", "EHT-E-0011", new String[]{Integer.toString(5*1024*1024)}, "\u4e0a\u4f20\u7684\u7b80\u5386\u5927\u5c0f\u8d85{0}\u5b57\u8282\uff0c\u8bf7\u4fee\u6539\u540e\u91cd\u65b0\u4e0a\u4f20\u3002 [EHT-E-0011]");
		}
	}

}

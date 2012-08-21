package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.LanguageCategory;
import com.pccw.ehunter.dto.LanguageCategoryDTO;

public class LanguageCategoryConvertor {
	
	public static LanguageCategoryDTO toDto(LanguageCategory po){
		if(po == null){
			return null;
		}
		
		LanguageCategoryDTO dto = new LanguageCategoryDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
}

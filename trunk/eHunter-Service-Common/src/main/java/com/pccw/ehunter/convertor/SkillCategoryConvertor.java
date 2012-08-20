package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.SkillCategory;
import com.pccw.ehunter.dto.SkillCategoryDTO;

public class SkillCategoryConvertor {
	
	public static SkillCategoryDTO toDto(SkillCategory po){
		if(po == null){
			return null;
		}
		
		SkillCategoryDTO dto = new SkillCategoryDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
}

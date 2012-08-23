package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.SkillLevel;
import com.pccw.ehunter.dto.SkillLevelDTO;

public class SkillLevelConvertor {
	
	public static SkillLevelDTO toDto(SkillLevel po){
		if(po == null){
			return null;
		}
		
		SkillLevelDTO dto = new SkillLevelDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static SkillLevel toPo(SkillLevelDTO dto){
		if(dto == null) return null;
		
		SkillLevel po = new SkillLevel();
		
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
}

package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.internal.PositionRequirement;
import com.pccw.ehunter.dto.PositionRequirementDTO;
import com.pccw.ehunter.utility.StringUtils;

public class PositionRequirementConvertor {
	
	public static PositionRequirementDTO toDto(PositionRequirement po){
		if(po == null) return null;
		
		PositionRequirementDTO dto = new PositionRequirementDTO();
		BeanUtils.copyProperties(po, dto);
		
		if(po.getAgeFrom() != null){
			dto.setAgeFromStr(String.valueOf(po.getAgeFrom()));
		}else {
			dto.setAgeFromStr(StringUtils.EMPTY_STRING);
		}
		
		if(po.getAgeTo() != null){
			dto.setAgeToStr(String.valueOf(po.getAgeTo()));
		}else {
			dto.setAgeToStr(StringUtils.EMPTY_STRING);
		}
		
		if(po.getWorkExperience() != null){
			dto.setWorkExperienceStr(String.valueOf(po.getWorkExperience()));
		}else {
			dto.setWorkExperienceStr(StringUtils.EMPTY_STRING);
		}
		
		return dto;
	}

	public static PositionRequirement toPo(PositionRequirementDTO dto){
		if(dto == null) return null;
		
		PositionRequirement po = new PositionRequirement();
		
		BeanUtils.copyProperties(dto, po);
		
		if(!StringUtils.isEmpty(dto.getAgeFromStr())){
			po.setAgeFrom(Integer.valueOf(dto.getAgeFromStr()));			
		}
		
		if(!StringUtils.isEmpty(dto.getAgeToStr())){
			po.setAgeTo(Integer.valueOf(dto.getAgeToStr()));			
		}
		
		if(!StringUtils.isEmpty(dto.getWorkExperienceStr())){
			po.setWorkExperience(Integer.valueOf(dto.getWorkExperienceStr()));			
		}
		
		return po;
	}
}

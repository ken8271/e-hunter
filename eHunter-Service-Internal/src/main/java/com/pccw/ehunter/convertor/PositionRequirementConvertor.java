package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.internal.PositionRequirement;
import com.pccw.ehunter.dto.PositionRequirementDTO;
import com.pccw.ehunter.utility.StringUtils;

public class PositionRequirementConvertor {

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

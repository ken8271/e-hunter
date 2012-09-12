package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.internal.PositionRequirement;
import com.pccw.ehunter.dto.PositionRequirementDTO;

public class PositionRequirementConvertor {

	public static PositionRequirement toPo(PositionRequirementDTO dto){
		if(dto == null) return null;
		
		PositionRequirement po = new PositionRequirement();
		
		BeanUtils.copyProperties(dto, po);
		
		po.setAgeFrom(Integer.valueOf(dto.getAgeFromStr()));
		po.setAgeTo(Integer.valueOf(dto.getAgeToStr()));
		po.setWorkExperience(Integer.valueOf(dto.getAgeToStr()));
		
		return po;
	}
}

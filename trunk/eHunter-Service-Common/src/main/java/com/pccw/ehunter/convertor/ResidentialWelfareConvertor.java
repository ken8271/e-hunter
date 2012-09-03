package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.ResidentialWelfare;
import com.pccw.ehunter.dto.ResidentialWelfareDTO;

public class ResidentialWelfareConvertor {

	public static ResidentialWelfareDTO toDto(ResidentialWelfare po){
		if(po == null) return null;
		
		ResidentialWelfareDTO dto = new ResidentialWelfareDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
}

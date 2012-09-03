package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.SocietyWelfare;
import com.pccw.ehunter.dto.SocietyWelfareDTO;

public class SocietyWelfareConvertor {

	public static SocietyWelfareDTO toDto(SocietyWelfare po){
		if(po == null) return null;
		
		SocietyWelfareDTO dto = new SocietyWelfareDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
}

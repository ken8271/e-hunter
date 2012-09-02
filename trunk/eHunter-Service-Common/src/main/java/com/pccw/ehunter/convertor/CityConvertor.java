package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.City;
import com.pccw.ehunter.dto.CityDTO;

public class CityConvertor {
	
	public static CityDTO toDto(City po){
		if(po == null) return null;
		
		CityDTO dto = new CityDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
}

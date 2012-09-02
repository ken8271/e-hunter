package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.Province;
import com.pccw.ehunter.dto.ProvinceDTO;

public class ProvinceConvertor {
	
	public static ProvinceDTO toDto(Province po){
		if(po == null ) return null;
		
		ProvinceDTO dto = new ProvinceDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
}

package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.IndustryCategory;
import com.pccw.ehunter.dto.IndustryCategoryDTO;

public class IndustryCategoryConvertor {
	
	public static IndustryCategoryDTO toDto(IndustryCategory po){
		if(po == null){
			return null;
		}
		
		IndustryCategoryDTO dto = new IndustryCategoryDTO();
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static IndustryCategory toPo(IndustryCategoryDTO dto){
		if(dto == null) return null;
		
		IndustryCategory po = new IndustryCategory();
		
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
}

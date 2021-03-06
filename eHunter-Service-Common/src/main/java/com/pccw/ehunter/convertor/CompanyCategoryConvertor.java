package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.CompanyCategory;
import com.pccw.ehunter.dto.CompanyCategoryDTO;

public class CompanyCategoryConvertor {
	
	public static CompanyCategoryDTO toDto(CompanyCategory po){
		if(po == null){
			return null;
		}
		
		CompanyCategoryDTO dto = new CompanyCategoryDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static CompanyCategory toPo(CompanyCategoryDTO dto){
		if(dto == null) return null;
		
		CompanyCategory po = new CompanyCategory();
		
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
}

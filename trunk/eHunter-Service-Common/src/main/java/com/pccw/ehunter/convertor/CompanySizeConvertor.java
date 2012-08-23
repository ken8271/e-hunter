package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.CompanySize;
import com.pccw.ehunter.dto.CompanySizeDTO;

public class CompanySizeConvertor {
	
	public static CompanySizeDTO toDto(CompanySize po){
		if(po == null){
			return null;
		}
		
		CompanySizeDTO dto = new CompanySizeDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static CompanySize toPo(CompanySizeDTO dto){
		if(dto == null) return null;
		
		CompanySize po = new CompanySize();
		
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
}

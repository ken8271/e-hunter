package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.Industry;
import com.pccw.ehunter.dto.IndustryDTO;

public class IndustryConvertor {
	
	public static IndustryDTO toDto(Industry po){
		if(po == null){
			return null;
		}
		
		IndustryDTO dto = new IndustryDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static Industry toPo(IndustryDTO dto){
		if(dto == null) return null;
		
		Industry po = new Industry();
		
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
}

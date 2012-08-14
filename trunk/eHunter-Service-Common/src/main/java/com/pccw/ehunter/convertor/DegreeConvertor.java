package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.Degree;
import com.pccw.ehunter.dto.DegreeDTO;

public class DegreeConvertor {
	
	public static DegreeDTO toDto(Degree po){
		if(po == null){
			return null;
		}
		
		DegreeDTO dto = new DegreeDTO();
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static Degree toPo(DegreeDTO dto){
		if(dto == null){
			return null;
		}
		
		Degree po = new Degree();
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
}

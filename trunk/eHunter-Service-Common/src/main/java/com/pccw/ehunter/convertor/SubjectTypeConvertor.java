package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.SubjectType;
import com.pccw.ehunter.dto.SubjectTypeDTO;

public class SubjectTypeConvertor {
	
	public static SubjectTypeDTO toDto(SubjectType po){
		if(po == null){
			return null;
		}
		
		SubjectTypeDTO dto = new SubjectTypeDTO();
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static SubjectType toPo(SubjectTypeDTO dto){
		if(dto == null){
			return null;
		}
		
		SubjectType po = new SubjectType();
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
}

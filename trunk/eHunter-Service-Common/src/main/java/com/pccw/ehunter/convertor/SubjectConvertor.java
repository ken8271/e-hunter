package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.Subject;
import com.pccw.ehunter.dto.SubjectDTO;

public class SubjectConvertor {
	
	public static SubjectDTO toDto(Subject po){
		if(po == null){
			return null;
		}
		
		SubjectDTO dto = new SubjectDTO();
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static Subject toPo(SubjectDTO dto){
		if(dto == null){
			return null;
		}
		
		Subject po = new Subject();
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
}

package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.SubjectCategory;
import com.pccw.ehunter.dto.SubjectCategoryDTO;

public class SubjectCategoryConvertor {
	
	public static SubjectCategoryDTO toDto(SubjectCategory po){
		if(po == null){
			return null;
		}
		
		SubjectCategoryDTO dto = new SubjectCategoryDTO();
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static SubjectCategory toPo(SubjectCategoryDTO dto){
		if(dto == null){
			return null;
		}
		
		SubjectCategory po = new SubjectCategory();
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
}

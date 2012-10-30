package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.internal.UploadedCurriculumVitae;
import com.pccw.ehunter.dto.UploadedCurriculumVitaeDTO;

public class UploadedCurriculumVitaeConvertor {
	
	public static UploadedCurriculumVitaeDTO toDto(UploadedCurriculumVitae po){
		if(po == null) return null;
		
		UploadedCurriculumVitaeDTO dto = new UploadedCurriculumVitaeDTO();
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static UploadedCurriculumVitae toPo(UploadedCurriculumVitaeDTO dto){
		if(dto == null) return null;
		
		UploadedCurriculumVitae po = new UploadedCurriculumVitae();
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
}

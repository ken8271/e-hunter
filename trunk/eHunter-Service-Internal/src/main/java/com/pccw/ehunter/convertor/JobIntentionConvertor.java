package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.internal.JobIntention;
import com.pccw.ehunter.dto.JobIntentionDTO;

public class JobIntentionConvertor {
	
	public static JobIntentionDTO toDto(JobIntention po){
		if(po == null) return null;
		
		JobIntentionDTO dto = new JobIntentionDTO();
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static JobIntention toPo(JobIntentionDTO dto){
		if(dto == null) return null;
		
		JobIntention po = new JobIntention();
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
}

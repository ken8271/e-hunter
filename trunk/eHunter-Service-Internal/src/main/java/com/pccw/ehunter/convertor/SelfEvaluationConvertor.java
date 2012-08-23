package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.internal.SelfEvaluation;
import com.pccw.ehunter.dto.SelfEvaluationDTO;

public class SelfEvaluationConvertor {
	
	public static SelfEvaluationDTO toDto(SelfEvaluation po){
		if(po == null) return null;
		
		SelfEvaluationDTO dto = new SelfEvaluationDTO();
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static SelfEvaluation toPo(SelfEvaluationDTO dto){
		if(dto == null) return null;
		
		SelfEvaluation po = new SelfEvaluation();
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
}

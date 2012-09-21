package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.CandidateStatus;
import com.pccw.ehunter.dto.CandidateStatusDTO;

public class CandidateStatusConvertor {

	public static CandidateStatusDTO toDto(CandidateStatus po){
		if(po == null ) return null;
		
		CandidateStatusDTO dto = new CandidateStatusDTO();
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
}

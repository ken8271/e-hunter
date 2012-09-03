package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.AnnualLeaveWelfare;
import com.pccw.ehunter.dto.AnnualLeaveWelfareDTO;

public class AnnualLeaveWelfareConvertor {
	
	public static AnnualLeaveWelfareDTO toDto(AnnualLeaveWelfare po){
		if(po == null) return null;
		
		AnnualLeaveWelfareDTO dto = new AnnualLeaveWelfareDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
}

package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.SalaryCategory;
import com.pccw.ehunter.dto.SalaryCategoryDTO;

public class SalaryCategoryConvertor {

	public static SalaryCategoryDTO toDto(SalaryCategory po){
		if(po == null) return null;
		
		SalaryCategoryDTO dto = new SalaryCategoryDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
}

package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.CustomerGrade;
import com.pccw.ehunter.dto.CustomerGradeDTO;

public class CustomerGradeConvertor {

	public static CustomerGradeDTO toDto(CustomerGrade po){
		if(po == null) return null;
		
		CustomerGradeDTO dto = new CustomerGradeDTO();
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
}

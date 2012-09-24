package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.CustomerStatus;
import com.pccw.ehunter.dto.CustomerStatusDTO;

public class CustomerStatusConvertor {

	public static CustomerStatusDTO toDto(CustomerStatus po){
		if(po == null ) return null;
		
		CustomerStatusDTO dto = new CustomerStatusDTO();
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
}

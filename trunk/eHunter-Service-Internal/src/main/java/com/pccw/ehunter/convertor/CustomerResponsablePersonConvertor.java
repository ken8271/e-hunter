package com.pccw.ehunter.convertor;


import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.internal.CustomerResponsablePerson;
import com.pccw.ehunter.dto.CustomerResponsePersonDTO;

public class CustomerResponsablePersonConvertor {
	
	public static CustomerResponsablePerson toPo(CustomerResponsePersonDTO dto){
		CustomerResponsablePerson po = new CustomerResponsablePerson();
		
		if(null == dto){
			return po;
		}
		
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
	
	public static CustomerResponsePersonDTO toDto(CustomerResponsablePerson po){
		CustomerResponsePersonDTO dto = new CustomerResponsePersonDTO();
		
		if(null == po){
			return dto;
		}
		
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
}

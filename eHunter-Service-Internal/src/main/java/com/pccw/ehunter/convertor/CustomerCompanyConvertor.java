package com.pccw.ehunter.convertor;


import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.internal.CustomerCompany;
import com.pccw.ehunter.dto.CustomerDTO;

public class CustomerCompanyConvertor {
	
	public static CustomerCompany toPo(CustomerDTO dto){
		CustomerCompany po = new CustomerCompany();
		
		if(null == dto){
			return po;
		}
		
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
	
}

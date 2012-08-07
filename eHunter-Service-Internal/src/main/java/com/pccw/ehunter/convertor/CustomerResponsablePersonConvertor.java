package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.domain.internal.CustomerResponsablePerson;
import com.pccw.ehunter.dto.CustomerResponsePersonDTO;
import com.pccw.ehunter.dto.MobilePhoneDTO;

public class CustomerResponsablePersonConvertor {
	
	public static CustomerResponsePersonDTO toDto(CustomerResponsablePerson po){
		
		if(null == po){
			return null;
		}
		
		CustomerResponsePersonDTO dto = new CustomerResponsePersonDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		MobilePhoneDTO mobile = new MobilePhoneDTO();
		mobile.setPhoneNumber(po.getTelephone());
		
		return dto;
	}
	
	public static CustomerResponsablePerson toPo(CustomerResponsePersonDTO dto){
		if(null == dto){
			return null;
		}
		
		CustomerResponsablePerson po = new CustomerResponsablePerson();
		
		BeanUtils.copyProperties(dto, po);
		
		po.setTelephone(dto.getTelephoneDto().toString());
		
		return po;
	}
	
	public static List<CustomerResponsePersonDTO> toDtos(List<CustomerResponsablePerson> pos){
		List<CustomerResponsePersonDTO> dtos = new ArrayList<CustomerResponsePersonDTO>();
		
		if(CollectionUtils.isEmpty(pos)){
			return dtos;
		}
		
		for(CustomerResponsablePerson po : pos){
			CustomerResponsePersonDTO dto = toDto(po);
			if(null != dto){				
				dtos.add(dto);
			}
		}
		
		return dtos;
	}
	
	public static List<CustomerResponsablePerson> toPos(List<CustomerResponsePersonDTO> dtos){
		List<CustomerResponsablePerson> pos = new ArrayList<CustomerResponsablePerson>();
		
		if(CollectionUtils.isEmpty(dtos)){
			return pos;
		}
		
		for(CustomerResponsePersonDTO dto : dtos){
			CustomerResponsablePerson po = toPo(dto);
			if(null != po){
				pos.add(po);
			}
		}
		
		return pos;
	}
}

package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.internal.InternalUser;
import com.pccw.ehunter.dto.InternalUserDTO;

public class InternalUserConvertor {
	
	public static InternalUserDTO toDto(InternalUser po){
		InternalUserDTO dto = new InternalUserDTO();
		
		if(null == po){
			return dto;
		}
		
		BeanUtils.copyProperties(po, dto);
		
		dto.setActiveRoles(InternalRoleConvertor.toDtos(po.getActiveRoles()));
		
		return dto;
	}
	
	public static InternalUser toPo(InternalUserDTO dto){
		InternalUser po = new InternalUser();
		
		if(null == dto){
			return po;
		}
		
		BeanUtils.copyProperties(dto, po);
		
		po.setActiveRoles(InternalRoleConvertor.toPos(dto.getActiveRoles()));
		
		return po;
	}
}

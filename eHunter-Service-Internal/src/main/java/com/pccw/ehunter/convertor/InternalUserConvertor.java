package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.internal.InternalUser;
import com.pccw.ehunter.dto.InternalUserDTO;
import com.pccw.ehunter.dto.InternalUserEnquireDTO;
import com.pccw.ehunter.dto.InternalUserPagedCriteria;
import com.pccw.ehunter.utility.StringEncryptUtils;

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
		po.setPassword(StringEncryptUtils.encryptDefault(dto.getPassword()));			
		
		return po;
	}
	
	public static InternalUserPagedCriteria toPagedCriteria(InternalUserEnquireDTO enquireDto){
		InternalUserPagedCriteria pagedCriteria = new InternalUserPagedCriteria();
		
		if(enquireDto == null) return pagedCriteria;
		
		BeanUtils.copyProperties(enquireDto, pagedCriteria);
		
		if(enquireDto.getJmesaDto() != null && enquireDto.getJmesaDto().getRowSelect() != null){
			pagedCriteria.getPageFilter().setRowStart(enquireDto.getJmesaDto().getRowSelect().getRowStart());
			pagedCriteria.getPageFilter().setRowEnd(enquireDto.getJmesaDto().getRowSelect().getRowEnd());
		}
		
		return pagedCriteria;
	}
}

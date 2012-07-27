package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.domain.internal.InternalRole;
import com.pccw.ehunter.dto.InternalRoleDTO;

public class InternalRoleConvertor {
	
	public static InternalRoleDTO toDto(InternalRole po){
		InternalRoleDTO dto = new InternalRoleDTO();
		
		if(null == po){
			return dto;
		}
		
		BeanUtils.copyProperties(po, dto);
		return dto;
	}
	
	public static InternalRole toPo(InternalRoleDTO dto){
		InternalRole po = new InternalRole();
		
		if(null == dto){
			return po;
		}
		
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
	
	public static List<InternalRoleDTO> toDtos(List<InternalRole> pos){
		List<InternalRoleDTO> dtos = new ArrayList<InternalRoleDTO>();
		
		if(CollectionUtils.isEmpty(pos)){
			return dtos;
		}
		
		for(InternalRole po : pos){
			dtos.add(toDto(po));
		}
		
		return dtos;
	}
	
	public static List<InternalRole> toPos(List<InternalRoleDTO> dtos){
		List<InternalRole> pos = new ArrayList<InternalRole>();
		
		if(CollectionUtils.isEmpty(dtos)){
			return pos;
		}
		
		for(InternalRoleDTO dto : dtos){
			pos.add(toPo(dto));
		}
		return pos;
	}
}

package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.domain.common.SystemParameter;
import com.pccw.ehunter.dto.SystemParameterDTO;

public class SystemParameterConvertor {

	public static SystemParameterDTO toDto(SystemParameter po){
		if(po == null) return null;
		
		SystemParameterDTO dto = new SystemParameterDTO();
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static SystemParameter toPo(SystemParameterDTO dto){
		if(dto == null) return null;
		
		SystemParameter po = new SystemParameter();
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
	
	public static List<SystemParameterDTO> toDtos(List<SystemParameter> pos){
		List<SystemParameterDTO> dtos = new ArrayList<SystemParameterDTO>();
		
		if(!CollectionUtils.isEmpty(pos)){
			for(SystemParameter po : pos){
				dtos.add(toDto(po));
			}
		}
		
		return dtos;
	}
}

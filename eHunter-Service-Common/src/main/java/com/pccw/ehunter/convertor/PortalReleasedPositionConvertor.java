package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.domain.portal.PortalReleasedPosition;
import com.pccw.ehunter.dto.portal.PortalReleasedPositionDTO;

public class PortalReleasedPositionConvertor {

	public static PortalReleasedPositionDTO toDto(PortalReleasedPosition po){
		if(po == null) return null;
		
		PortalReleasedPositionDTO dto = new PortalReleasedPositionDTO();
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static PortalReleasedPosition toPo(PortalReleasedPositionDTO dto){
		if(dto == null) return null;
		
		PortalReleasedPosition po = new PortalReleasedPosition();
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
	
	public static List<PortalReleasedPositionDTO> toDtos(List<PortalReleasedPosition> pos){
		List<PortalReleasedPositionDTO> dtos = new ArrayList<PortalReleasedPositionDTO>();
		
		if(!CollectionUtils.isEmpty(pos)){
			for(PortalReleasedPosition po : pos){
				dtos.add(toDto(po));
			}
		}
		
		return dtos;
	}
}

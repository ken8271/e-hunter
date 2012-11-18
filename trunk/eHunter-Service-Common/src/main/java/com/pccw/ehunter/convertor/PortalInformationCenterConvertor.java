package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.domain.portal.PortalInformationCenter;
import com.pccw.ehunter.dto.portal.PortalInformationCenterDTO;

public class PortalInformationCenterConvertor {

	public static PortalInformationCenterDTO toDto(PortalInformationCenter po){
		if(po == null) return null;
		
		PortalInformationCenterDTO dto = new PortalInformationCenterDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static PortalInformationCenter toPo(PortalInformationCenterDTO dto){
		if(dto == null) return null;
		
		PortalInformationCenter po = new PortalInformationCenter();
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
	
	public static List<PortalInformationCenterDTO> toDtos(List<PortalInformationCenter> pos){
		List<PortalInformationCenterDTO> dtos = new ArrayList<PortalInformationCenterDTO>();
		
		if(!CollectionUtils.isEmpty(pos)){
			for(PortalInformationCenter po : pos){
				dtos.add(toDto(po));
			}
		}
		
		return dtos;
	}
}

package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.domain.internal.ProjectExperience;
import com.pccw.ehunter.domain.internal.ProjectExperiencePK;
import com.pccw.ehunter.dto.ProjectExperienceDTO;

public class ProjectExperienceConvertor {
	
	public static ProjectExperienceDTO toDto(ProjectExperience po){
		if(po == null) return null;
		
		ProjectExperienceDTO dto = new ProjectExperienceDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		dto.setItemNumber(po.getPk().getItemNumber());
		dto.setFromDateDto(SimpleDateConvertor.toSimpleDate(po.getFromDate()));
		dto.setToDateDto(SimpleDateConvertor.toSimpleDate(po.getToDate()));
		
		return dto;
	}
	
	public static ProjectExperience toPo(ProjectExperienceDTO dto){
		if(dto == null) return null;
		
		ProjectExperience po = new ProjectExperience();
		
		BeanUtils.copyProperties(dto, po);
		
		ProjectExperiencePK pk = new ProjectExperiencePK();
		pk.setItemNumber(dto.getItemNumber());
		po.setPk(pk);
		
		po.setFromDate(SimpleDateConvertor.toDate(dto.getFromDateDto()));
		po.setToDate(SimpleDateConvertor.toDate(dto.getToDateDto()));
		
		return po;
	}
	
	public static List<ProjectExperienceDTO> toDtos(List<ProjectExperience> pos){
		if(CollectionUtils.isEmpty(pos)) return null;
		
		List<ProjectExperienceDTO> dtos = new ArrayList<ProjectExperienceDTO>();
		
		for(ProjectExperience po : pos){
			dtos.add(toDto(po));
		}
		
		return dtos;
	}
	
	public static List<ProjectExperience> toPos(List<ProjectExperienceDTO> dtos){
		if(CollectionUtils.isEmpty(dtos)) return null;
		
		List<ProjectExperience> pos = new ArrayList<ProjectExperience>();
		
		for(ProjectExperienceDTO dto : dtos){
			pos.add(toPo(dto));
		}
		
		return pos;
		
	}
}

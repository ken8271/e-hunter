package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.domain.internal.EducationExperience;
import com.pccw.ehunter.domain.internal.EducationExperiencePK;
import com.pccw.ehunter.dto.EducationExperienceDTO;

public class EducationExperienceConvertor {
	
	public static EducationExperienceDTO toDto(EducationExperience po){
		if(po == null) return null;
		
		EducationExperienceDTO dto = new EducationExperienceDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		dto.setItemNumber(po.getPk().getItemNumber());
		dto.setFromDateDto(SimpleDateConvertor.toSimpleDate(po.getFromDate()));
		dto.setToDateDto(SimpleDateConvertor.toSimpleDate(po.getToDate()));
		
		return dto;
	}
	
	public static EducationExperience toPo(EducationExperienceDTO dto){
		if(dto == null) return null;
		
		EducationExperience po = new EducationExperience();
		
		BeanUtils.copyProperties(dto, po);
		
		EducationExperiencePK pk = new EducationExperiencePK();
		pk.setItemNumber(dto.getItemNumber());
		
		po.setPk(pk);
		po.setFromDate(SimpleDateConvertor.toDate(dto.getFromDateDto()));
		po.setToDate(SimpleDateConvertor.toDate(dto.getToDateDto()));
		
		return po;
	}
	
	public List<EducationExperienceDTO> toDtos(List<EducationExperience> pos){
		if(CollectionUtils.isEmpty(pos)) return null;
		
		List<EducationExperienceDTO> dtos = new ArrayList<EducationExperienceDTO>();
		
		for(EducationExperience po : pos){
			dtos.add(toDto(po));
		}
		
		return dtos;
	}
	
	public List<EducationExperience> toPos(List<EducationExperienceDTO> dtos){
		if(CollectionUtils.isEmpty(dtos)) return null;
		
		List<EducationExperience> pos = new ArrayList<EducationExperience>();
		
		for(EducationExperienceDTO dto : dtos){
			pos.add(toPo(dto));
		}
		
		return pos;
	}
}

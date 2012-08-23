package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.domain.internal.TrainingExperience;
import com.pccw.ehunter.domain.internal.TrainingExperiencePK;
import com.pccw.ehunter.dto.TrainingExperienceDTO;

public class TrainingExperienceConvertor {
	
	public static TrainingExperienceDTO toDto(TrainingExperience po){
		if(po == null) return null;
		
		TrainingExperienceDTO dto = new TrainingExperienceDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		dto.setItemNumber(po.getPk().getItemNumber());
		dto.setFromDateDto(SimpleDateConvertor.toSimpleDate(po.getFromDate()));
		dto.setToDateDto(SimpleDateConvertor.toSimpleDate(po.getToDate()));
		
		return dto;
	}
	
	public static TrainingExperience toPo(TrainingExperienceDTO dto){
		if(dto == null) return null;
		
		TrainingExperience po = new TrainingExperience();
		
		BeanUtils.copyProperties(dto, po);
		
		TrainingExperiencePK pk = new TrainingExperiencePK();
		pk.setItemNumber(dto.getItemNumber());
		po.setPk(pk);
		
		po.setFromDate(SimpleDateConvertor.toDate(dto.getFromDateDto()));
		po.setToDate(SimpleDateConvertor.toDate(dto.getToDateDto()));
		
		return po;
	}
	
	public static List<TrainingExperienceDTO> toDtos(List<TrainingExperience> pos){
		if(CollectionUtils.isEmpty(pos)) return null;
		
		List<TrainingExperienceDTO> dtos = new ArrayList<TrainingExperienceDTO>();
		
		for(TrainingExperience po : pos){
			dtos.add(toDto(po));
		}
		
		return dtos;
	}
	
	public static List<TrainingExperience> toPos(List<TrainingExperienceDTO> dtos){
		if(CollectionUtils.isEmpty(dtos)) return null;
		
		List<TrainingExperience> pos = new ArrayList<TrainingExperience>();
		
		for(TrainingExperienceDTO dto : dtos){
			pos.add(toPo(dto));
		}
		
		return pos;
	}
}

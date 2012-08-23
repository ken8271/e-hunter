package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.domain.internal.JobExperience;
import com.pccw.ehunter.domain.internal.JobExperiencePK;
import com.pccw.ehunter.dto.CompanyCategoryDTO;
import com.pccw.ehunter.dto.CompanySizeDTO;
import com.pccw.ehunter.dto.IndustryDTO;
import com.pccw.ehunter.dto.JobExperienceDTO;
import com.pccw.ehunter.dto.PositionDTO;

public class JobExperienceConvertor {
	
	public static JobExperienceDTO toDto(JobExperience po){
		if(po == null) return null;
		
		JobExperienceDTO dto = new JobExperienceDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		dto.setItemNumber(po.getPk().getItemNumber());
		dto.setFromDateDto(SimpleDateConvertor.toSimpleDate(po.getFromDate()));
		dto.setToDateDto(SimpleDateConvertor.toSimpleDate(po.getToDate()));
		
		CompanyCategoryDTO cc = new CompanyCategoryDTO();
		cc.setCategoryCode(po.getCompanyCategory());
		dto.setCompanyCategoryDto(cc);
		
		CompanySizeDTO cs = new CompanySizeDTO();
		cs.setSizeCode(po.getCompanySize());
		dto.setCompanySizeDto(cs);
		
		IndustryDTO industryDto = new IndustryDTO();
		industryDto.setIndustryCode(po.getIndustry());
		dto.setIndustryDto(industryDto);
		
		PositionDTO positionDto = new PositionDTO();
		positionDto.setTypeCode(po.getPositionCategory());
		dto.setPositionDto(positionDto);
		
		return dto;
	}
	
	public static JobExperience toPo(JobExperienceDTO dto){
		if(dto == null) return null;
		
		JobExperience po = new JobExperience();
		
		BeanUtils.copyProperties(dto, po);
		
		JobExperiencePK pk = new JobExperiencePK();
		pk.setItemNumber(dto.getItemNumber());
		po.setPk(pk);
		
		po.setFromDate(SimpleDateConvertor.toDate(dto.getFromDateDto()));
		po.setToDate(SimpleDateConvertor.toDate(dto.getToDateDto()));
		
		if(dto.getCompanyCategoryDto() != null){			
			po.setCompanyCategory(dto.getCompanyCategoryDto().getCategoryCode());
		}
		
		if(dto.getCompanySizeDto() != null){
			po.setCompanySize(dto.getCompanySizeDto().getSizeCode());
		}
		
		if(dto.getIndustryDto() != null){
			po.setIndustry(dto.getIndustryDto().getIndustryCode());
		}
		
		if(dto.getPositionDto() != null){
			po.setPositionCategory(dto.getPositionDto().getTypeCode());
		}
		
		return po;
	}
	
	public static List<JobExperienceDTO> toDtos(List<JobExperience> pos){
		if(CollectionUtils.isEmpty(pos)) return null;
		
		List<JobExperienceDTO> dtos = new ArrayList<JobExperienceDTO>();
		
		for(JobExperience po : pos){
			dtos.add(toDto(po));
		}
		
		return dtos;
	}
	
	public static List<JobExperience> toPos(List<JobExperienceDTO> dtos){
		if(CollectionUtils.isEmpty(dtos)) return null;
		
		List<JobExperience> pos = new ArrayList<JobExperience>();
		
		for(JobExperienceDTO dto : dtos){
			pos.add(toPo(dto));
		}
		
		return pos;
	}
}

package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.domain.internal.ProfessionalSkill;
import com.pccw.ehunter.domain.internal.ProfessionalSkillPK;
import com.pccw.ehunter.dto.ProfessionalSkillDTO;

public class ProfessionalSkillConvertor {
	
	public static ProfessionalSkillDTO toDto(ProfessionalSkill po){
		if(po == null) return null;
		
		ProfessionalSkillDTO dto = new ProfessionalSkillDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		dto.setItemNumber(po.getPk().getItemNumber());
		
		return dto;
	}
	
	public static ProfessionalSkill toPo(ProfessionalSkillDTO dto){
		if(dto == null) return null;
		
		ProfessionalSkill po = new ProfessionalSkill();
		
		BeanUtils.copyProperties(dto, po);
		
		ProfessionalSkillPK pk = new ProfessionalSkillPK();
		pk.setItemNumber(dto.getItemNumber());
		po.setPk(pk);
		
		return po;
	}
	
	public static List<ProfessionalSkillDTO> toDtos(List<ProfessionalSkill> pos){
		if(CollectionUtils.isEmpty(pos)) return null;
		
		List<ProfessionalSkillDTO> dtos = new ArrayList<ProfessionalSkillDTO>();
		
		for(ProfessionalSkill po : pos){
			dtos.add(toDto(po));
		}
		
		return dtos;
 	}
	
	public static List<ProfessionalSkill> toPos(List<ProfessionalSkillDTO> dtos){
		if(CollectionUtils.isEmpty(dtos)) return null;
		
		List<ProfessionalSkill> pos = new ArrayList<ProfessionalSkill>();
		
		for(ProfessionalSkillDTO dto : dtos){
			pos.add(toPo(dto));
		}
		
		return pos;
	}
}

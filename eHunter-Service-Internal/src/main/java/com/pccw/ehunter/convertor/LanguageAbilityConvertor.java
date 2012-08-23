package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.domain.internal.LanguageAbility;
import com.pccw.ehunter.domain.internal.LanguageAbilityPK;
import com.pccw.ehunter.dto.LanguageAbilityDTO;

public class LanguageAbilityConvertor {
	
	public static LanguageAbilityDTO toDto(LanguageAbility po){
		if(po == null) return null;
		
		LanguageAbilityDTO dto = new LanguageAbilityDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		dto.setItemNumber(po.getPk().getItemNumber());
		
		return dto;
	}
	
	public static LanguageAbility toPo(LanguageAbilityDTO dto){
		if(dto == null) return null;
		
		LanguageAbility po = new LanguageAbility();
		
		BeanUtils.copyProperties(dto, po);
		
		LanguageAbilityPK pk = new LanguageAbilityPK();
		pk.setItemNumber(dto.getItemNumber());
		po.setPk(pk);
		
		return po;
	}
	
	public static List<LanguageAbilityDTO> toDtos(List<LanguageAbility> pos){
		if(CollectionUtils.isEmpty(pos)) return null;
		
		List<LanguageAbilityDTO> dtos = new ArrayList<LanguageAbilityDTO>();
		
		for(LanguageAbility po : pos){
			dtos.add(toDto(po));
		}
		
		return dtos;
	}
	
	public static List<LanguageAbility> toPos(List<LanguageAbilityDTO> dtos){
		if(CollectionUtils.isEmpty(dtos)) return null;
		
		List<LanguageAbility> pos = new ArrayList<LanguageAbility>();
		
		for(LanguageAbilityDTO dto : dtos){
			pos.add(toPo(dto));
		}
		
		return pos;
	}
}

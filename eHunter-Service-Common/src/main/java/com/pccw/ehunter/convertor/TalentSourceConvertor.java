package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.TalentSource;
import com.pccw.ehunter.dto.TalentSourceDTO;

public class TalentSourceConvertor {
	
	public static TalentSourceDTO toDto(TalentSource po){
		if(po == null){
			return null;
		}
		
		TalentSourceDTO dto = new TalentSourceDTO();
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static TalentSource toPo(TalentSourceDTO dto){
		if(dto == null){
			return null;
		}
		
		TalentSource po = new TalentSource();
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
}

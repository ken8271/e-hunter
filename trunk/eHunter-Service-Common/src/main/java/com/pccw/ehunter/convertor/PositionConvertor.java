package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.Position;
import com.pccw.ehunter.dto.BaseLabelValueDTO;
import com.pccw.ehunter.dto.PositionDTO;

public class PositionConvertor {
	
	public static PositionDTO toDto(Position po){	
		if(null == po){
			return null;
		}
		
		PositionDTO dto = new PositionDTO();
		
		BeanUtils.copyProperties(po, dto);
		return dto;
	}
	
	public static BaseLabelValueDTO toSelectOption(Position po){
		if(po == null){
			return null;
		}
		
		BaseLabelValueDTO lv = new BaseLabelValueDTO();
		lv.setLabel(po.getDisplayName());
		lv.setValue(po.getTypeCode());
		
		return lv;
	}
}

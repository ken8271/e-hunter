package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.PositionSubType;
import com.pccw.ehunter.domain.common.PositionType;
import com.pccw.ehunter.dto.BaseLabelValueDTO;
import com.pccw.ehunter.dto.PositionTypeDTO;

public class PositionTypeConvertor {
	
	public static PositionTypeDTO toDto(PositionType po){
		PositionTypeDTO dto = new PositionTypeDTO();
		
		if(po == null){
			return dto;
		}
		
		BeanUtils.copyProperties(po, dto);
		return dto;
	}
	
	public static PositionTypeDTO toDto(PositionSubType po){
		PositionTypeDTO dto = new PositionTypeDTO();
		
		if(null == po){
			return dto;
		}
		
		BeanUtils.copyProperties(po, dto);
		return dto;
	}
	
	public static BaseLabelValueDTO toSelectOption(PositionType po){
		if(po == null){
			return null;
		}
		
		BaseLabelValueDTO dto = new BaseLabelValueDTO();
		dto.setLabel(po.getDisplayName());
		dto.setValue(po.getTypeCode());
		
		return dto;
	}
	
	public static BaseLabelValueDTO toSelectOption(PositionSubType po){
		if(po == null){
			return null;
		}
		
		BaseLabelValueDTO lv = new BaseLabelValueDTO();
		lv.setLabel(po.getDisplayName());
		lv.setValue(po.getTypeCode());
		
		return lv;
	}
}

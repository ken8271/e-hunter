package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.PositionCategory;
import com.pccw.ehunter.dto.BaseLabelValueDTO;
import com.pccw.ehunter.dto.PositionCategoryDTO;

public class PositionCategoryConvertor {
	
	public static PositionCategoryDTO toDto(PositionCategory po){
		if(po == null){
			return null;
		}
		
		PositionCategoryDTO dto = new PositionCategoryDTO();
		
		BeanUtils.copyProperties(po, dto);
		return dto;
	}
	
	public static BaseLabelValueDTO toSelectOption(PositionCategory po){
		if(po == null){
			return null;
		}
		
		BaseLabelValueDTO dto = new BaseLabelValueDTO();
		dto.setLabel(po.getDisplayName());
		dto.setValue(po.getTypeCode());
		
		return dto;
	}
}

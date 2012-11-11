package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.internal.PositionDescription;
import com.pccw.ehunter.dto.PositionDescriptionDTO;
import com.pccw.ehunter.utility.StringUtils;

public class PositionDescriptionConvertor {
	
	public static PositionDescriptionDTO toDto(PositionDescription po){
		if(po == null) return null;
		
		PositionDescriptionDTO dto = new PositionDescriptionDTO();
		BeanUtils.copyProperties(po, dto);
		
		dto.setExpectNumberStr(String.valueOf(po.getExpectNumber()));
		dto.setExpiryDateDto(SimpleDateConvertor.toSimpleDate(po.getExpiryDate()));
		
		if(po.getSalaryFrom() != null){			
			dto.setSalaryFromStr(String.valueOf(po.getSalaryFrom()));
		}
		
		if(po.getSalaryTo() != null){
			dto.setSalaryToStr(String.valueOf(po.getSalaryTo()));
		}
		
		return dto;
	}
	
	public static PositionDescription toPo(PositionDescriptionDTO dto , String transactionIndicator){
		if(dto == null) return null;
		
		PositionDescription po = new PositionDescription();
		BeanUtils.copyProperties(dto, po);
		
		po.setExpiryDate(SimpleDateConvertor.toDate(dto.getExpiryDateDto()));
		
		if(!StringUtils.isEmpty(dto.getSalaryFromStr())){			
			po.setSalaryFrom(Integer.valueOf(dto.getSalaryFromStr()));
		}
		
		if(!StringUtils.isEmpty(dto.getSalaryToStr())){			
			po.setSalaryTo(Integer.valueOf(dto.getSalaryToStr()));
		}
		
		if(!StringUtils.isEmpty(dto.getExpectNumberStr())){			
			po.setExpectNumber(Integer.valueOf(dto.getExpectNumberStr()));
		}
		
		return po;
	}
}

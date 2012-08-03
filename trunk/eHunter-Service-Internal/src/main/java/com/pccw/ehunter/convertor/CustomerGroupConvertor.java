package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.domain.internal.CustomerGroup;
import com.pccw.ehunter.dto.BaseLabelValueDTO;
import com.pccw.ehunter.dto.CustomerGroupDTO;

public class CustomerGroupConvertor {
	
	public static CustomerGroupDTO toDto(CustomerGroup po){
		CustomerGroupDTO dto = new CustomerGroupDTO();
		
		if(null == po){
			return dto;
		}
		
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static CustomerGroup toPo(CustomerGroupDTO dto){
		CustomerGroup po = new CustomerGroup();
		
		if(null == dto){
			return po;
		}
		
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
	
	public static List<CustomerGroupDTO> toDtos(List<CustomerGroup> pos){
		List<CustomerGroupDTO> dtos = new ArrayList<CustomerGroupDTO>();
		
		if(CollectionUtils.isEmpty(pos)){
			return dtos;
		}
		
		for(CustomerGroup po : pos){
			dtos.add(toDto(po));
		}
		
		return dtos;
	}
	
	public static List<CustomerGroup> toPos(List<CustomerGroupDTO> dtos){
		List<CustomerGroup> pos = new ArrayList<CustomerGroup>();
		
		if(CollectionUtils.isEmpty(dtos)){
			return pos;
		}
		
		for(CustomerGroupDTO dto : dtos){
			pos.add(toPo(dto));
		}
		
		return pos;
	}
	
	public static List<BaseLabelValueDTO> toSelectOptions(List<CustomerGroupDTO> dtos){
		List<BaseLabelValueDTO> lvs = new ArrayList<BaseLabelValueDTO>();
		
		if(CollectionUtils.isEmpty(dtos)){
			return lvs;
		}
		
		for(CustomerGroupDTO dto : dtos){
			BaseLabelValueDTO lv = toOption(dto);
			if(null != lv){
				lvs.add(lv);
			}
		}
		
		return lvs;
	}
	
	public static BaseLabelValueDTO toOption(CustomerGroupDTO dto){
		if(null == dto){
			return null;
		}
		
		BaseLabelValueDTO lv = new BaseLabelValueDTO();
		lv.setLabel(dto.getFullName());
		lv.setValue(dto.getSystemGroupRefNum());
		
		return lv;
	}
}

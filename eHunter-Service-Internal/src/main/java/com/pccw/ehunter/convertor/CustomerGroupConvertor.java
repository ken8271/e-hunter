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
		
		if(null == po){
			return null;
		}
		
		CustomerGroupDTO dto = new CustomerGroupDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static CustomerGroup toPo(CustomerGroupDTO dto){
		if(null == dto){
			return null;
		}
		
		CustomerGroup po = new CustomerGroup();
		
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
	
	public static List<CustomerGroupDTO> toDtos(List<CustomerGroup> pos){
		List<CustomerGroupDTO> dtos = new ArrayList<CustomerGroupDTO>();
		
		if(CollectionUtils.isEmpty(pos)){
			return dtos;
		}
		
		for(CustomerGroup po : pos){
			CustomerGroupDTO dto = toDto(po);
			if(null != dto){				
				dtos.add(dto);
			}
		}
		
		return dtos;
	}
	
	public static List<CustomerGroup> toPos(List<CustomerGroupDTO> dtos){
		List<CustomerGroup> pos = new ArrayList<CustomerGroup>();
		
		if(CollectionUtils.isEmpty(dtos)){
			return pos;
		}
		
		for(CustomerGroupDTO dto : dtos){
			CustomerGroup po = toPo(dto);
			if(null != po){				
				pos.add(po);
			}
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

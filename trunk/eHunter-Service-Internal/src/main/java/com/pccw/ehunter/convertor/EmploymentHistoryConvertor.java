package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.domain.internal.EmploymentHistory;
import com.pccw.ehunter.domain.internal.EmploymentHistoryPK;
import com.pccw.ehunter.dto.EmploymentHistoryDTO;

public class EmploymentHistoryConvertor {
	
	public static EmploymentHistoryDTO toDto(EmploymentHistory po){
		if(po == null) return null;
		
		EmploymentHistoryDTO dto = new EmploymentHistoryDTO();
		BeanUtils.copyProperties(po, dto);
		
		dto.setItemNumber(po.getPk().getItemNumber());
		dto.setBeginTimeDto(SimpleDateConvertor.toSimpleDate(po.getBeginTime()));
		dto.setEndTimeDto(SimpleDateConvertor.toSimpleDate(po.getEndTime()));
		
		return dto;
	}

	public static EmploymentHistory toPo(EmploymentHistoryDTO dto){
		if(dto == null) return null;
		
		EmploymentHistory po = new EmploymentHistory();
		BeanUtils.copyProperties(dto, po);
		
		EmploymentHistoryPK pk = new EmploymentHistoryPK();
		pk.setItemNumber(dto.getItemNumber());
		po.setPk(pk);
		
		if(dto.getBeginTimeDto() != null){
			dto.getBeginTimeDto().setDay("1");
		}
		
		if(dto.getEndTimeDto() != null){
			dto.getEndTimeDto().setDay("1");
		}
		po.setBeginTime(SimpleDateConvertor.toDate(dto.getBeginTimeDto()));
		po.setEndTime(SimpleDateConvertor.toDate(dto.getEndTimeDto()));
		
		return po;
	}
	
	public static List<EmploymentHistory> toPos(List<EmploymentHistoryDTO> dtos){
		if(CollectionUtils.isEmpty(dtos)) return null;
		
		List<EmploymentHistory> pos = new ArrayList<EmploymentHistory>();
		
		for(EmploymentHistoryDTO dto : dtos){
			pos.add(toPo(dto));
		}
		
		return pos;
	}
	
	public static List<EmploymentHistoryDTO> toDtos(List<EmploymentHistory> pos){
		if(CollectionUtils.isEmpty(pos)) return null;
		
		List<EmploymentHistoryDTO> dtos = new ArrayList<EmploymentHistoryDTO>();
		
		for(EmploymentHistory po : pos){
			dtos.add(toDto(po));
		}
		
		return dtos;
	}
}

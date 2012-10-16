package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

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
}

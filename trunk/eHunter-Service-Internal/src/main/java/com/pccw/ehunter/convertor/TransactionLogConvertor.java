package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.internal.TransactionLog;
import com.pccw.ehunter.dto.TransactionLogDTO;
import com.pccw.ehunter.dto.TransactionLogEnquireDTO;
import com.pccw.ehunter.dto.TransactionLogPagedCriteria;

public class TransactionLogConvertor {

	public static TransactionLogDTO toDto(TransactionLog po){
		if(po == null) return null;
		
		TransactionLogDTO dto = new TransactionLogDTO();
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static TransactionLog toPo(TransactionLogDTO dto){
		if(dto == null) return null;
		
		TransactionLog po = new TransactionLog();
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
	
	public static TransactionLogPagedCriteria toPagedCriteria(TransactionLogEnquireDTO dto){
		TransactionLogPagedCriteria pagedCriteria = new TransactionLogPagedCriteria();
		
		if(dto == null) return pagedCriteria;
		
		BeanUtils.copyProperties(dto, pagedCriteria);
		
		if(dto.getFromDto() != null){
			pagedCriteria.setFromDttm(SimpleDateTimeConvertor.toDate(dto.getFromDto()));
		}
		
		if(dto.getToDto() != null){
			pagedCriteria.setToDttm(SimpleDateTimeConvertor.toDate(dto.getToDto()));
		}
		
		if(dto.getJmesaDto() != null && dto.getJmesaDto().getRowSelect() != null){
			pagedCriteria.getPageFilter().setRowStart(dto.getJmesaDto().getRowSelect().getRowStart());
			pagedCriteria.getPageFilter().setRowEnd(dto.getJmesaDto().getRowSelect().getRowEnd());
		}
		
		return pagedCriteria;
	}
	
}

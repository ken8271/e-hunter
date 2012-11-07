package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.internal.TransactionLog;
import com.pccw.ehunter.dto.TransactionLogDTO;

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
	
}

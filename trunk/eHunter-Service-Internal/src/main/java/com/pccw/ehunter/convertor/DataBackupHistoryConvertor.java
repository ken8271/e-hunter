package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.internal.DataBackupHistory;
import com.pccw.ehunter.dto.DataBackupHistoryDTO;

public class DataBackupHistoryConvertor {

	public static DataBackupHistory toPo(DataBackupHistoryDTO dto){
		if(dto == null) return null;
		
		DataBackupHistory po = new DataBackupHistory();
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
	
	public static DataBackupHistoryDTO toDto(DataBackupHistory po){
		if(po==null) return null;
		
		DataBackupHistoryDTO dto  = new DataBackupHistoryDTO();
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
}

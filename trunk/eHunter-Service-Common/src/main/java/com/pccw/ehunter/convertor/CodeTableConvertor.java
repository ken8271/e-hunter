package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.domain.common.CodeTable;
import com.pccw.ehunter.dto.CodeTableDTO;

public class CodeTableConvertor {

	public static CodeTableDTO toDto(CodeTable po){
		if(po == null) return null;
		
		CodeTableDTO dto = new CodeTableDTO();
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
	
	public static CodeTable toPo(CodeTableDTO dto){
		if(dto == null) return null;
		
		CodeTable po = new CodeTable();
		BeanUtils.copyProperties(dto, po);
		
		return po;
	}
	
	public static List<CodeTableDTO> toDtos(List<CodeTable> pos){
		if(CollectionUtils.isEmpty(pos)) return null;
		
		List<CodeTableDTO> dtos = new ArrayList<CodeTableDTO>();
		
		for(CodeTable po : pos){
			dtos.add(toDto(po));
		}
		
		return dtos;
	}
}

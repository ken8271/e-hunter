package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.common.ProjectStatus;
import com.pccw.ehunter.dto.ProjectStatusDTO;

public class ProjectStatusConvertor {
	
	public static ProjectStatusDTO toDto(ProjectStatus po){
		if(po == null) return null;
		
		ProjectStatusDTO dto = new ProjectStatusDTO();
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}

}

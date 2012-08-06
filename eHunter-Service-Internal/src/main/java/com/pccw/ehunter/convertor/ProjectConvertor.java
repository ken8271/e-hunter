package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.dto.ProjectEnquireDTO;
import com.pccw.ehunter.dto.ProjectPagedCriteria;

public class ProjectConvertor {
	
	public static ProjectPagedCriteria toPagedCriteria(ProjectEnquireDTO enquireDto){
		ProjectPagedCriteria pagedCriteria = new ProjectPagedCriteria();
		
		if(null == enquireDto){
			return pagedCriteria;
		}
		
		BeanUtils.copyProperties(enquireDto, pagedCriteria);
		
		if(enquireDto.getJmesaDto() != null && enquireDto.getJmesaDto().getRowSelect() != null){
			pagedCriteria.getPageFilter().setRowStart(enquireDto.getJmesaDto().getRowSelect().getRowStart());
			pagedCriteria.getPageFilter().setRowEnd(enquireDto.getJmesaDto().getRowSelect().getRowEnd());
		}
		
		return pagedCriteria;
	}
}

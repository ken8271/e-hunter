package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.constant.TransactionIndicator;
import com.pccw.ehunter.domain.internal.ProjectCandidateRepository;
import com.pccw.ehunter.domain.internal.ProjectCandidateRepositoryPK;
import com.pccw.ehunter.dto.ProjectCandidateRepositoryDTO;

public class ProjectCandidateRepositoryConvertor {
	
	public static ProjectCandidateRepositoryDTO toDto(ProjectCandidateRepository po){
		if(po == null) return null;
		
		ProjectCandidateRepositoryDTO dto = new ProjectCandidateRepositoryDTO();
		BeanUtils.copyProperties(po, dto);
		
		dto.setProjectDto(ProjectConvertor.toDto(po.getPk().getProject()));
		dto.setTalentDto(TalentConvertor.toDto(po.getPk().getTalent()));
		
		return dto;
	}
	
	public static ProjectCandidateRepository toPo(ProjectCandidateRepositoryDTO dto){
		if(dto ==  null) return null;
		
		ProjectCandidateRepository po = new ProjectCandidateRepository();
		BeanUtils.copyProperties(dto, po);
		
		ProjectCandidateRepositoryPK pk = new ProjectCandidateRepositoryPK();
		pk.setProject(ProjectConvertor.toPo(dto.getProjectDto(), TransactionIndicator.INSERT));
		pk.setTalent(TalentConvertor.toPo(dto.getTalentDto()));
		po.setPk(pk);
		
		return po;
	}
	
	public static List<ProjectCandidateRepositoryDTO> toDtos(List<ProjectCandidateRepository> pos){
		if(CollectionUtils.isEmpty(pos)) return new ArrayList<ProjectCandidateRepositoryDTO>();
		
		List<ProjectCandidateRepositoryDTO> dtos = new ArrayList<ProjectCandidateRepositoryDTO>();
		for(ProjectCandidateRepository po : pos){
			dtos.add(toDto(po));
		}
		
		return dtos;
	}
	
	public static List<ProjectCandidateRepository> toPos(List<ProjectCandidateRepositoryDTO> dtos){
		if(CollectionUtils.isEmpty(dtos)) return null;
		
		List<ProjectCandidateRepository> pos = new ArrayList<ProjectCandidateRepository>();
		for(ProjectCandidateRepositoryDTO dto : dtos){
			pos.add(toPo(dto));
		}
		
		return pos;
	}
}

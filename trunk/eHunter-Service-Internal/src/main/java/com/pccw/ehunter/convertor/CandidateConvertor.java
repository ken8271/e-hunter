package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.constant.TransactionIndicator;
import com.pccw.ehunter.domain.internal.Candidate;
import com.pccw.ehunter.domain.internal.CandidatePK;
import com.pccw.ehunter.dto.CandidateDTO;

public class CandidateConvertor {
	
	public static CandidateDTO toDto(Candidate po){
		if(po == null) return null;
		
		CandidateDTO dto = new CandidateDTO();
		BeanUtils.copyProperties(po, dto);
		
		dto.setProjectDto(ProjectConvertor.toDto(po.getPk().getProject()));
		dto.setTalentDto(TalentConvertor.toDto(po.getPk().getTalent()));
		
		return dto;
	}
	
	public static Candidate toPo(CandidateDTO dto){
		if(dto ==  null) return null;
		
		Candidate po = new Candidate();
		BeanUtils.copyProperties(dto, po);
		
		CandidatePK pk = new CandidatePK();
		pk.setProject(ProjectConvertor.toPo(dto.getProjectDto(), TransactionIndicator.INSERT));
		pk.setTalent(TalentConvertor.toPo(dto.getTalentDto()));
		po.setPk(pk);
		
		return po;
	}
	
	public static List<CandidateDTO> toDtos(List<Candidate> pos){
		if(CollectionUtils.isEmpty(pos)) return new ArrayList<CandidateDTO>();
		
		List<CandidateDTO> dtos = new ArrayList<CandidateDTO>();
		for(Candidate po : pos){
			dtos.add(toDto(po));
		}
		
		return dtos;
	}
	
	public static List<Candidate> toPos(List<CandidateDTO> dtos){
		if(CollectionUtils.isEmpty(dtos)) return null;
		
		List<Candidate> pos = new ArrayList<Candidate>();
		for(CandidateDTO dto : dtos){
			pos.add(toPo(dto));
		}
		
		return pos;
	}
}

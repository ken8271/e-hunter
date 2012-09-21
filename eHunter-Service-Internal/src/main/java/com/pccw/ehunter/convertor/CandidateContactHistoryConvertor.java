package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.internal.CandidateContactHistory;
import com.pccw.ehunter.domain.internal.InternalUser;
import com.pccw.ehunter.domain.internal.Project;
import com.pccw.ehunter.domain.internal.Talent;
import com.pccw.ehunter.dto.CandidateContactHistoryDTO;

public class CandidateContactHistoryConvertor {
	public static CandidateContactHistory toPo(CandidateContactHistoryDTO dto){
		if(dto == null) return null;
		
		CandidateContactHistory po = new CandidateContactHistory();
		BeanUtils.copyProperties(dto, po);
		
		Talent t = new Talent();
		t.setTalentID(dto.getTalentDto().getTalentID());
		po.setTalent(t);
		
		Project p = new Project();
		p.setSystemProjectRefNum(dto.getProjectDto().getSystemProjectRefNum());
		po.setProject(p);
		
		InternalUser u = new InternalUser();
		u.setUserRecId(dto.getAdviserDto().getUserRecId());
		po.setAdviser(u);
		
		return po;
	}
	
	public static CandidateContactHistoryDTO toDto(CandidateContactHistory po){
		if(po == null) return null;
		
		CandidateContactHistoryDTO dto = new CandidateContactHistoryDTO();
		BeanUtils.copyProperties(po, dto);
		
		return dto;
	}
}

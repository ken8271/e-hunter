package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.EmploymentHistoryDTO;
import com.pccw.ehunter.dto.ProjectDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.dto.TalentPagedCriteria;

public interface TalentCommonService {
	public int getTalentsCountByCriteria(TalentPagedCriteria pagedCriteria);
	public List<TalentDTO> getTalentsByCriteria(TalentPagedCriteria pagedCriteria);
	public int getCandidatesCountByCriteria(TalentPagedCriteria pagedCriteria);
	public List<TalentDTO> getCandidatesByCriteria(TalentPagedCriteria pagedCriteria);
	public List<ProjectDTO> getParticipatedProjectByTalentID(String talentID);
	public List<TalentDTO> getTalentsByPhoneNumber(String phone);
	public List<TalentDTO> getTalentsByEmail(String email);
	public EmploymentHistoryDTO getEmployeeHistory(String id, String talentId);
	public List<TalentDTO> getTalentsByIds(List<String> ids , TalentPagedCriteria pagedCriteria);
	public int getTalentsCountByIds(List<String> ids , TalentPagedCriteria pagedCriteria);
}

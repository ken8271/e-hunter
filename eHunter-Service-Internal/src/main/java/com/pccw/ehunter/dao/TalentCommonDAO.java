package com.pccw.ehunter.dao;

import java.util.List;

import com.pccw.ehunter.domain.internal.EmploymentHistory;
import com.pccw.ehunter.dto.EmploymentHistoryDTO;
import com.pccw.ehunter.dto.TalentPagedCriteria;

public interface TalentCommonDAO {
	public int getTalentsCountByCriteria(TalentPagedCriteria pagedCriteria);
	public List<Object> getTalentsByCriteria(TalentPagedCriteria pagedCriteria);
	public int getCandidatesCountByCriteria(TalentPagedCriteria pagedCriteria);
	public List<Object> getCandidatesByCriteria(TalentPagedCriteria pagedCriteria);
	public List<Object> getParticipatedProjectByTalentID(String talentID);
	public Object getTalentByID(String talentID);
	public List<Object> getEmploymentHistoriesByTalentID(String talentID);
	public List<String> getTalentsByPhoneNumber(String phone);
	public List<String> getTalentsByEmail(String email);
	public Object getEmploymentHistory(String id, String talentId);
}

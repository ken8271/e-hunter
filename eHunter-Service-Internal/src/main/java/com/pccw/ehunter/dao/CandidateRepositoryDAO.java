package com.pccw.ehunter.dao;

import java.util.List;

import com.pccw.ehunter.domain.internal.Candidate;
import com.pccw.ehunter.dto.TalentPagedCriteria;

public interface CandidateRepositoryDAO {
	public void saveCandidateRepository(List<Candidate> repos);
	public int getCandidateRepositoryCountByProjectID(TalentPagedCriteria pagedCriteria);
	public List<Object> getCandidateRepositoryByProjectID(TalentPagedCriteria pagedCriteria);
	public void updateCandidateStatus(Candidate po);
}

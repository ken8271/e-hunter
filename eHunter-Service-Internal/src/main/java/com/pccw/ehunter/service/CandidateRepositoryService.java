package com.pccw.ehunter.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pccw.ehunter.dto.CandidateDTO;
import com.pccw.ehunter.dto.TalentPagedCriteria;

public interface CandidateRepositoryService {
	public void saveCandidateRepository(List<CandidateDTO> repoDtos);
	public int getCandidateRepositoryCountByProjectID(TalentPagedCriteria pagedCriteria);
	public List<CandidateDTO> getCandidateRepositoryByProjectID(HttpServletRequest request , TalentPagedCriteria pagedCriteria);
}

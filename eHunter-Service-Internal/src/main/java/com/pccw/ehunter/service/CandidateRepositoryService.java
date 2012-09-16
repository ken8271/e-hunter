package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.ProjectCandidateRepositoryDTO;

public interface CandidateRepositoryService {
	public void saveCandidateRepository(List<ProjectCandidateRepositoryDTO> repoDtos);
}

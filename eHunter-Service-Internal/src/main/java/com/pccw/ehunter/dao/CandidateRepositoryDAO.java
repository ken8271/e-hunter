package com.pccw.ehunter.dao;

import java.util.List;

import com.pccw.ehunter.domain.internal.ProjectCandidateRepository;

public interface CandidateRepositoryDAO {
	public void saveCandidateRepository(List<ProjectCandidateRepository> repos);
}

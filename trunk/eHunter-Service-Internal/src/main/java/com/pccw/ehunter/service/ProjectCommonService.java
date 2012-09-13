package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.ProjectDTO;
import com.pccw.ehunter.dto.ProjectPagedCriteria;

public interface ProjectCommonService {
	public int getProjectsCountByCriteria(ProjectPagedCriteria pagedCriteria);
	public List<ProjectDTO> getProjectsByCriteria(ProjectPagedCriteria pagedCriteria);
	public ProjectDTO getProjectByID(String id);
}

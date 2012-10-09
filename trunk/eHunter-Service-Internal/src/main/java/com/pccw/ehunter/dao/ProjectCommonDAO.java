package com.pccw.ehunter.dao;

import java.util.List;

import com.pccw.ehunter.dto.ProjectPagedCriteria;

public interface ProjectCommonDAO {
	public int getProjectsCountByCriteria(ProjectPagedCriteria pagedCriteria);
	public List<Object> getProjectsByCriteria(ProjectPagedCriteria pagedCriteria);
	public List<Object> getUnassignedProjectsByCriteria(ProjectPagedCriteria pagedCriteria);
}

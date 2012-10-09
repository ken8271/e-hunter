package com.pccw.ehunter.dao;

import com.pccw.ehunter.domain.internal.PositionDescription;
import com.pccw.ehunter.domain.internal.Project;

public interface ProjectRegistrationDAO {
	public void saveProject(Project project);
	public void updateProject(Project project);
	public void updateProjectStatus(Project project);
	public void updatePositionDescription(PositionDescription pd);
}

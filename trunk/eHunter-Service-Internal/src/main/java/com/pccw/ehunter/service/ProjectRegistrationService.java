package com.pccw.ehunter.service;

import com.pccw.ehunter.dto.ProjectDTO;

public interface ProjectRegistrationService {
	public void submitProject(ProjectDTO projectDto);
	public void updateProject(ProjectDTO projectDto);
	public void updateProjectStatus(ProjectDTO projectDTO);
}

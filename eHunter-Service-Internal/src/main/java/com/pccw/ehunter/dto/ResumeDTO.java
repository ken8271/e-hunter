package com.pccw.ehunter.dto;

import java.util.ArrayList;
import java.util.List;

public class ResumeDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String language;

	private List<EducationExperienceDTO> eduExpDtos = new ArrayList<EducationExperienceDTO>();

	private List<JobExperienceDTO> jobExpDtos = new ArrayList<JobExperienceDTO>();
	
	private List<ProjectExperienceDTO> prjExpDtos = new ArrayList<ProjectExperienceDTO>();
	
	private List<TrainingExperienceDTO> trnExpDtos = new ArrayList<TrainingExperienceDTO>();
	
	private List<ProfessionalSkillDTO> skillDtos = new ArrayList<ProfessionalSkillDTO>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EducationExperienceDTO> getEduExpDtos() {
		return eduExpDtos;
	}

	public void setEduExpDtos(List<EducationExperienceDTO> eduExpDtos) {
		this.eduExpDtos = eduExpDtos;
	}

	public List<JobExperienceDTO> getJobExpDtos() {
		return jobExpDtos;
	}

	public void setJobExpDtos(List<JobExperienceDTO> jobExpDtos) {
		this.jobExpDtos = jobExpDtos;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<ProjectExperienceDTO> getPrjExpDtos() {
		return prjExpDtos;
	}

	public void setPrjExpDtos(List<ProjectExperienceDTO> prjExpDtos) {
		this.prjExpDtos = prjExpDtos;
	}

	public List<TrainingExperienceDTO> getTrnExpDtos() {
		return trnExpDtos;
	}

	public void setTrnExpDtos(List<TrainingExperienceDTO> trnExpDtos) {
		this.trnExpDtos = trnExpDtos;
	}

	public List<ProfessionalSkillDTO> getSkillDtos() {
		return skillDtos;
	}

	public void setSkillDtos(List<ProfessionalSkillDTO> skillDtos) {
		this.skillDtos = skillDtos;
	}
}

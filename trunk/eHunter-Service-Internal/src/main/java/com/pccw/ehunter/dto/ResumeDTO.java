package com.pccw.ehunter.dto;

import java.util.ArrayList;
import java.util.List;

public class ResumeDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;
	
	private int itemNumber;

	private String name;
	
	private String language;
	
	private SelfEvaluationDTO selfEvaluationDto;
	
	private JobIntentionDTO intentionDto;

	private List<EducationExperienceDTO> eduExpDtos = new ArrayList<EducationExperienceDTO>(0);

	private List<JobExperienceDTO> jobExpDtos = new ArrayList<JobExperienceDTO>(0);
	
	private List<ProjectExperienceDTO> prjExpDtos = new ArrayList<ProjectExperienceDTO>(0);
	
	private List<TrainingExperienceDTO> trnExpDtos = new ArrayList<TrainingExperienceDTO>(0);
	
	private List<ProfessionalSkillDTO> skillDtos = new ArrayList<ProfessionalSkillDTO>(0);
	
	private List<LanguageAbilityDTO> languageDtos = new ArrayList<LanguageAbilityDTO>(0);
	
	private List<CertDTO> certDtos = new ArrayList<CertDTO>(0);

	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

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
	
	public SelfEvaluationDTO getSelfEvaluationDto() {
		return selfEvaluationDto;
	}
	
	public void setSelfEvaluationDto(SelfEvaluationDTO selfEvaluationDto) {
		this.selfEvaluationDto = selfEvaluationDto;
	}
	
	public JobIntentionDTO getIntentionDto() {
		return intentionDto;
	}
	
	public void setIntentionDto(JobIntentionDTO intentionDto) {
		this.intentionDto = intentionDto;
	}

	public List<LanguageAbilityDTO> getLanguageDtos() {
		return languageDtos;
	}

	public void setLanguageDtos(List<LanguageAbilityDTO> languageDtos) {
		this.languageDtos = languageDtos;
	}

	public List<CertDTO> getCertDtos() {
		return certDtos;
	}

	public void setCertDtos(List<CertDTO> certDtos) {
		this.certDtos = certDtos;
	}
}

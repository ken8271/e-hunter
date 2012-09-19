package com.pccw.ehunter.dto;

public class CandidateDTO extends BaseDTO {
	private static final long serialVersionUID = -4551330524096114734L;

	private ProjectDTO projectDto;
	private TalentDTO talentDto;
	private String candidateStatus;

	public ProjectDTO getProjectDto() {
		return projectDto;
	}

	public void setProjectDto(ProjectDTO projectDto) {
		this.projectDto = projectDto;
	}

	public TalentDTO getTalentDto() {
		return talentDto;
	}

	public void setTalentDto(TalentDTO talentDto) {
		this.talentDto = talentDto;
	}

	public String getCandidateStatus() {
		return candidateStatus;
	}

	public void setCandidateStatus(String candidateStatus) {
		this.candidateStatus = candidateStatus;
	}

}

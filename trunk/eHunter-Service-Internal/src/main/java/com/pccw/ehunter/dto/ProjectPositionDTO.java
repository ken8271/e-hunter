package com.pccw.ehunter.dto;

public class ProjectPositionDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String positionID;

	private PositionDescriptionDTO descriptionDto;
	private PositionRequirementDTO requirementDto;

	public String getPositionID() {
		return positionID;
	}

	public void setPositionID(String positionID) {
		this.positionID = positionID;
	}

	public PositionDescriptionDTO getDescriptionDto() {
		return descriptionDto;
	}

	public void setDescriptionDto(PositionDescriptionDTO descriptionDto) {
		this.descriptionDto = descriptionDto;
	}

	public PositionRequirementDTO getRequirementDto() {
		return requirementDto;
	}

	public void setRequirementDto(PositionRequirementDTO requirementDto) {
		this.requirementDto = requirementDto;
	}

}

package com.pccw.ehunter.dto;

import java.util.List;

public class ProjectDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String systemProjectRefNum;
	private String projectName;
	private String systemCustRefNum;
	private String status;
	private ProjectStatusDTO statusDto;
	private InternalUserDTO adviserDto;
	private CustomerDTO customerDto;

	private PositionDescriptionDTO postDescDto;
	private PositionRequirementDTO postRequireDto;
	
	private List<CandidateDTO> cddtRepoDtos;

	public String getSystemProjectRefNum() {
		return systemProjectRefNum;
	}

	public void setSystemProjectRefNum(String systemProjectRefNum) {
		this.systemProjectRefNum = systemProjectRefNum;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getSystemCustRefNum() {
		return systemCustRefNum;
	}

	public void setSystemCustRefNum(String systemCustRefNum) {
		this.systemCustRefNum = systemCustRefNum;
	}

	public InternalUserDTO getAdviserDto() {
		return adviserDto;
	}

	public void setAdviserDto(InternalUserDTO adviserDto) {
		this.adviserDto = adviserDto;
	}

	public CustomerDTO getCustomerDto() {
		return customerDto;
	}

	public void setCustomerDto(CustomerDTO customerDto) {
		this.customerDto = customerDto;
	}

	public PositionDescriptionDTO getPostDescDto() {
		return postDescDto;
	}

	public void setPostDescDto(PositionDescriptionDTO postDescDto) {
		this.postDescDto = postDescDto;
	}

	public PositionRequirementDTO getPostRequireDto() {
		return postRequireDto;
	}

	public void setPostRequireDto(PositionRequirementDTO postRequireDto) {
		this.postRequireDto = postRequireDto;
	}

	public List<CandidateDTO> getCddtRepoDtos() {
		return cddtRepoDtos;
	}

	public void setCddtRepoDtos(List<CandidateDTO> cddtRepoDtos) {
		this.cddtRepoDtos = cddtRepoDtos;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ProjectStatusDTO getStatusDto() {
		return statusDto;
	}

	public void setStatusDto(ProjectStatusDTO statusDto) {
		this.statusDto = statusDto;
	}

}

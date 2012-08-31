package com.pccw.ehunter.dto;

import java.util.ArrayList;
import java.util.List;

public class ProjectDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String systemProjectRefNum;
	private String projectName;
	private String systemCustRefNum;
	private InternalUserDTO adviserDto;
	private CustomerDTO customerDto;

	private List<ProjectPositionDTO> postions = new ArrayList<ProjectPositionDTO>();

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

	public List<ProjectPositionDTO> getPostions() {
		return postions;
	}

	public void setPostions(List<ProjectPositionDTO> postions) {
		this.postions = postions;
	}

}

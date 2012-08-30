package com.pccw.ehunter.dto;

import java.util.ArrayList;
import java.util.List;

public class ProjectDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String systemProjectRefNum;
	private String projectName;
	private InternalUserDTO adviserDto;
	private CustomerDTO customerDto;
	private CustomerEnquireDTO enquireDto;

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

	public InternalUserDTO getAdviserDto() {
		return adviserDto;
	}

	public void setAdviserDto(InternalUserDTO adviserDto) {
		this.adviserDto = adviserDto;
	}

	public CustomerEnquireDTO getEnquireDto() {
		return enquireDto;
	}

	public void setEnquireDto(CustomerEnquireDTO enquireDto) {
		this.enquireDto = enquireDto;
	}
}

package com.pccw.ehunter.dto;

import java.util.ArrayList;
import java.util.List;

public class ProjectDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String systemProjectRefNum;
	private String projectName;
	private CustomerDTO customer;

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

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public List<ProjectPositionDTO> getPostions() {
		return postions;
	}

	public void setPostions(List<ProjectPositionDTO> postions) {
		this.postions = postions;
	}

}

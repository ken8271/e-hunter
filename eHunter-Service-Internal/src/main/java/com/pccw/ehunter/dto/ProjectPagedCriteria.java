package com.pccw.ehunter.dto;

public class ProjectPagedCriteria extends PagedCriteria {
	private static final long serialVersionUID = 1L;

	private String systemProjectRefNum;
	private String projectName;
	private String systemCustRefNum;

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

}

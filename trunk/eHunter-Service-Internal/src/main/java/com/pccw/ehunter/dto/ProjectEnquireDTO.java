package com.pccw.ehunter.dto;

import java.io.Serializable;

public class ProjectEnquireDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String systemProjectRefNum;
	private String projectName;
	private String systemCustRefNum;
	private String customerName;
	private String projectStatus;
	private String systemTalentRefNum;

	private SimpleDateDTO fromDateDto;
	private SimpleDateDTO toDateDto;

	private JmesaCheckBoxDTO jmesaDto;

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

	public JmesaCheckBoxDTO getJmesaDto() {
		return jmesaDto;
	}

	public void setJmesaDto(JmesaCheckBoxDTO jmesaDto) {
		this.jmesaDto = jmesaDto;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public SimpleDateDTO getFromDateDto() {
		return fromDateDto;
	}

	public void setFromDateDto(SimpleDateDTO fromDateDto) {
		this.fromDateDto = fromDateDto;
	}

	public SimpleDateDTO getToDateDto() {
		return toDateDto;
	}

	public void setToDateDto(SimpleDateDTO toDateDto) {
		this.toDateDto = toDateDto;
	}

	public String getSystemTalentRefNum() {
		return systemTalentRefNum;
	}

	public void setSystemTalentRefNum(String systemTalentRefNum) {
		this.systemTalentRefNum = systemTalentRefNum;
	}
}

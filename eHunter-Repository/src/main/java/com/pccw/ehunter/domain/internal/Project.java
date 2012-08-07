package com.pccw.ehunter.domain.internal;

import java.util.ArrayList;
import java.util.List;

import com.pccw.ehunter.domain.BaseEntity;

public class Project extends BaseEntity {
	private String systemProjectRefNum;
	private String projectName;
	private String customerId;

	private List<ProjectPosition> positions = new ArrayList<ProjectPosition>();

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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public List<ProjectPosition> getPositions() {
		return positions;
	}

	public void setPositions(List<ProjectPosition> positions) {
		this.positions = positions;
	}

}

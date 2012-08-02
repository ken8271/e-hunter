package com.pccw.ehunter.domain.internal;

import com.pccw.ehunter.domain.BaseEntity;

public class CustomerGroup extends BaseEntity {

	private String systemGroupRefNum;
	private String shortName;
	private String fullName;

	public String getSystemGroupRefNum() {
		return systemGroupRefNum;
	}

	public void setSystemGroupRefNum(String systemGroupRefNum) {
		this.systemGroupRefNum = systemGroupRefNum;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}

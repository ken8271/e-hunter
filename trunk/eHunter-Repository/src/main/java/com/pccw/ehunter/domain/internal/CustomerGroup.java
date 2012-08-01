package com.pccw.ehunter.domain.internal;

import com.pccw.ehunter.domain.BaseEntity;

public class CustomerGroup extends BaseEntity {

	private String sysRefGroup;
	private String shortName;
	private String fullName;

	public String getSysRefGroup() {
		return sysRefGroup;
	}

	public void setSysRefGroup(String sysRefGroup) {
		this.sysRefGroup = sysRefGroup;
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

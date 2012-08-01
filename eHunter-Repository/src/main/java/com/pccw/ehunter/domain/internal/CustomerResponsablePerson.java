package com.pccw.ehunter.domain.internal;

import com.pccw.ehunter.domain.BaseEntity;

public class CustomerResponsablePerson extends BaseEntity {

	private String sysRefRp;
	private String name;
	private String positionType;
	private String positionName;
	private String telephone;
	private String email;
	private String status;

	public String getSysRefRp() {
		return sysRefRp;
	}

	public void setSysRefRp(String sysRefRp) {
		this.sysRefRp = sysRefRp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPositionType() {
		return positionType;
	}

	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

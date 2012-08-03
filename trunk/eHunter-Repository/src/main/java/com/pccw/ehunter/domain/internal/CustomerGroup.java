package com.pccw.ehunter.domain.internal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name="T_CUST_GP")
public class CustomerGroup extends BaseEntity {

	private String systemGroupRefNum;
	private String shortName;
	private String fullName;

	@Id
	@Column(name="SYS_REF_GP")
	public String getSystemGroupRefNum() {
		return systemGroupRefNum;
	}

	public void setSystemGroupRefNum(String systemGroupRefNum) {
		this.systemGroupRefNum = systemGroupRefNum;
	}

	@Column(name="GP_SHRT_NM")
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name="GP_NM")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}

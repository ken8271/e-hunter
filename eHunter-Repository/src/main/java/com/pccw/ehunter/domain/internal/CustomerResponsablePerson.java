package com.pccw.ehunter.domain.internal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name="T_CUST_RP")
public class CustomerResponsablePerson extends BaseEntity {
	private static final long serialVersionUID = -1825842012826184595L;
	
	private String systemRespRefNum;
	private String name;
	private String positionType;
	private String positionName;
	private String telephone;
	private String email;
	private String status;

	@Id
	@Column(name="SYS_REF_RP")
	public String getSystemRespRefNum() {
		return systemRespRefNum;
	}

	public void setSystemRespRefNum(String systemRespRefNum) {
		this.systemRespRefNum = systemRespRefNum;
	}

	@Column(name="RP_NM")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="RP_POST_TY")
	public String getPositionType() {
		return positionType;
	}

	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}

	@Column(name="RP_POST")
	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	@Column(name="RP_TEL")
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name="RP_EMAIL")
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="RP_STAT")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

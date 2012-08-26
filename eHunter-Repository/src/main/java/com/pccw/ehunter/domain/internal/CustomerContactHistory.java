package com.pccw.ehunter.domain.internal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name="T_CUST_CONT_HST")
public class CustomerContactHistory extends BaseEntity {	
	private static final long serialVersionUID = 8956214459524819069L;
	
	private String systemConatctRefNum;
	private String customerId;
	private String projectId;
	private String contactType;
	private String contactRecord;
	private String contactAdviser;
	private String remark;

	@Id
	@Column(name="SYS_REF_CONT_HST")
	public String getSystemConatctRefNum() {
		return systemConatctRefNum;
	}

	public void setSystemConatctRefNum(String systemConatctRefNum) {
		this.systemConatctRefNum = systemConatctRefNum;
	}

	@Column(name="SYS_REF_CUST")
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Column(name="SYS_REF_PRJ")
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Column(name="CONT_TY")
	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	@Column(name="CONT_REC")
	public String getContactRecord() {
		return contactRecord;
	}

	public void setContactRecord(String contactRecord) {
		this.contactRecord = contactRecord;
	}

	@Column(name="CONT_ADVSR")
	public String getContactAdviser() {
		return contactAdviser;
	}

	public void setContactAdviser(String contactAdviser) {
		this.contactAdviser = contactAdviser;
	}

	@Column(name="CONT_RMRK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

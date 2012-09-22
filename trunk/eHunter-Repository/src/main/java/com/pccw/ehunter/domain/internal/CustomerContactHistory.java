package com.pccw.ehunter.domain.internal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name = "T_CUST_CONT_HST")
public class CustomerContactHistory extends BaseEntity {
	private static final long serialVersionUID = 8956214459524819069L;

	private String systemContactRefNum;
	private CustomerCompany customer;
	private CustomerResponsablePerson responsePerson;
	private String content;
	private String remark;
	private InternalUser adviser;

	@Id
	@Column(name="SYS_REF_CONT_HST")
	public String getSystemContactRefNum() {
		return systemContactRefNum;
	}

	public void setSystemContactRefNum(String systemContactRefNum) {
		this.systemContactRefNum = systemContactRefNum;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SYS_REF_CUST", referencedColumnName = "SYS_REF_CUST", nullable = false)
	public CustomerCompany getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerCompany customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SYS_REF_RP", referencedColumnName = "SYS_REF_RP", nullable = false)
	public CustomerResponsablePerson getResponsePerson() {
		return responsePerson;
	}

	public void setResponsePerson(CustomerResponsablePerson responsePerson) {
		this.responsePerson = responsePerson;
	}

	@Column(name="CONT_REC")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="CONT_RMRK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONT_ADVSR", referencedColumnName = "USR_REC_ID", nullable = false)
	public InternalUser getAdviser() {
		return adviser;
	}

	public void setAdviser(InternalUser adviser) {
		this.adviser = adviser;
	}

}

package com.pccw.ehunter.domain.internal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name = "T_PRJ")
public class Project extends BaseEntity {
	private static final long serialVersionUID = -6586349396029661685L;

	private String systemProjectRefNum;
	private String projectName;
	private String status;

	private CustomerCompany customer;
	private InternalUser adviser;

	private PositionDescription postDescription;
	private PositionRequirement postRequirement;

	@Id
	@Column(name = "SYS_REF_PRJ")
	public String getSystemProjectRefNum() {
		return systemProjectRefNum;
	}

	public void setSystemProjectRefNum(String systemProjectRefNum) {
		this.systemProjectRefNum = systemProjectRefNum;
	}

	@Column(name = "PRJ_NM")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "PRJ_ST")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SYS_REF_CUST" , referencedColumnName="SYS_REF_CUST" , nullable=false)
	public CustomerCompany getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerCompany customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRJ_ADVSR", referencedColumnName = "USR_REC_ID", nullable = false)
	public InternalUser getAdviser() {
		return adviser;
	}

	public void setAdviser(InternalUser adviser) {
		this.adviser = adviser;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "project", cascade = { CascadeType.ALL })
	public PositionDescription getPostDescription() {
		return postDescription;
	}

	public void setPostDescription(PositionDescription postDescription) {
		this.postDescription = postDescription;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "project", cascade = { CascadeType.ALL })
	public PositionRequirement getPostRequirement() {
		return postRequirement;
	}

	public void setPostRequirement(PositionRequirement postRequirement) {
		this.postRequirement = postRequirement;
	}

}

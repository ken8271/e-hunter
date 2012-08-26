package com.pccw.ehunter.domain.internal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name = "T_TLNT_JOB_EXP")
public class JobExperience extends BaseEntity {
	private static final long serialVersionUID = 3474237408789851850L;
	
	private JobExperiencePK pk;
	private Date fromDate;
	private Date toDate;
	private String companyName;
	private String companyCategory;
	private String companySize;
	private String industry;
	private String department;
	private String positionCategory;
	private String positionName;
	private String jobDescription;

	@EmbeddedId
	public JobExperiencePK getPk() {
		return pk;
	}

	public void setPk(JobExperiencePK pk) {
		this.pk = pk;
	}

	@Column(name="BEGN_DTTM")
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@Column(name="LEV_DTTM")
	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Column(name="CO_NM")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name="CO_TY")
	public String getCompanyCategory() {
		return companyCategory;
	}

	public void setCompanyCategory(String companyCategory) {
		this.companyCategory = companyCategory;
	}

	@Column(name="CO_SCOP")
	public String getCompanySize() {
		return companySize;
	}

	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}

	@Column(name="IND_TY")
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@Column(name="DEPT_NM")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name="POST_TY")
	public String getPositionCategory() {
		return positionCategory;
	}

	public void setPositionCategory(String positionCategory) {
		this.positionCategory = positionCategory;
	}

	@Column(name="POST_NM")
	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	@Column(name="POST_DESC")
	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

}

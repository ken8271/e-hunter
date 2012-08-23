package com.pccw.ehunter.domain.internal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name = "T_TLNT_JB_INT")
public class JobIntention extends BaseEntity {
	private Resume resume;
	private String employmentCategory;
	private String expectAddress;
	private String expectPosition;
	private String expectIndustry;
	private String expectSalary;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name = "SYS_REF_RSUM", referencedColumnName = "SYS_REF_RSUM")
	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	@Column(name="JB_TY")
	public String getEmploymentCategory() {
		return employmentCategory;
	}

	public void setEmploymentCategory(String employmentCategory) {
		this.employmentCategory = employmentCategory;
	}

	@Column(name="EXP_ADDR")
	public String getExpectAddress() {
		return expectAddress;
	}

	public void setExpectAddress(String expectAddress) {
		this.expectAddress = expectAddress;
	}

	@Column(name="EXP_POST")
	public String getExpectPosition() {
		return expectPosition;
	}

	public void setExpectPosition(String expectPosition) {
		this.expectPosition = expectPosition;
	}

	@Column(name="EXP_IND")
	public String getExpectIndustry() {
		return expectIndustry;
	}

	public void setExpectIndustry(String expectIndustry) {
		this.expectIndustry = expectIndustry;
	}

	@Column(name="EXP_SALRY")
	public String getExpectSalary() {
		return expectSalary;
	}

	public void setExpectSalary(String expectSalary) {
		this.expectSalary = expectSalary;
	}

}

package com.pccw.ehunter.domain.internal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name="T_PRJ_POST_DESC")
public class ProjectPosition extends BaseEntity {
	private String systemPositionRefNum;
	private String postionType;
	private String postionName;
	private String postionDeparment;
	private String reportTo;
	private int underlingNumber;
	private Date expiryDate;
	private int yearlySalary;
	private String dutyDescription;
	private int ageFrom;
	private int ageTo;
	private String genderRequired;
	private String subjectRequired;
	private String jobExperienceRequired;
	private String educationRequired;
	private String fullTimeEducationRequired;
	private String offerRequiredDesc;
	private String remark;
	
	private Project project;

	@Id
	@Column(name="SYS_REF_POST")
	public String getSystemPositionRefNum() {
		return systemPositionRefNum;
	}

	public void setSystemPositionRefNum(String systemPositionRefNum) {
		this.systemPositionRefNum = systemPositionRefNum;
	}

	@Column(name="POST_TY")
	public String getPostionType() {
		return postionType;
	}

	public void setPostionType(String postionType) {
		this.postionType = postionType;
	}

	@Column(name="POST_NM")
	public String getPostionName() {
		return postionName;
	}

	public void setPostionName(String postionName) {
		this.postionName = postionName;
	}

	@Column(name="POST_DEPT")
	public String getPostionDeparment() {
		return postionDeparment;
	}

	public void setPostionDeparment(String postionDeparment) {
		this.postionDeparment = postionDeparment;
	}

	@Column(name="PEPT_TO")
	public String getReportTo() {
		return reportTo;
	}

	public void setReportTo(String reportTo) {
		this.reportTo = reportTo;
	}

	@Column(name="UDLNG_NBR")
	public int getUnderlingNumber() {
		return underlingNumber;
	}

	public void setUnderlingNumber(int underlingNumber) {
		this.underlingNumber = underlingNumber;
	}

	@Column(name="EXPR_DT")
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Column(name="YRLY_SALRY")
	public int getYearlySalary() {
		return yearlySalary;
	}

	public void setYearlySalary(int yearlySalary) {
		this.yearlySalary = yearlySalary;
	}

	@Column(name="DUTY_DESC")
	public String getDutyDescription() {
		return dutyDescription;
	}

	public void setDutyDescription(String dutyDescription) {
		this.dutyDescription = dutyDescription;
	}

	@Column(name="AGE_REQ_FR")
	public int getAgeFrom() {
		return ageFrom;
	}

	public void setAgeFrom(int ageFrom) {
		this.ageFrom = ageFrom;
	}

	@Column(name="AGE_REQ_TO")
	public int getAgeTo() {
		return ageTo;
	}

	public void setAgeTo(int ageTo) {
		this.ageTo = ageTo;
	}

	@Column(name="SEX_REQ")
	public String getGenderRequired() {
		return genderRequired;
	}

	public void setGenderRequired(String genderRequired) {
		this.genderRequired = genderRequired;
	}

	@Column(name="SUBJ_REQ")
	public String getSubjectRequired() {
		return subjectRequired;
	}

	public void setSubjectRequired(String subjectRequired) {
		this.subjectRequired = subjectRequired;
	}

	@Column(name="JOB_EXP_REQ")
	public String getJobExperienceRequired() {
		return jobExperienceRequired;
	}

	public void setJobExperienceRequired(String jobExperienceRequired) {
		this.jobExperienceRequired = jobExperienceRequired;
	}

	@Column(name="EDU_REQ")
	public String getEducationRequired() {
		return educationRequired;
	}

	public void setEducationRequired(String educationRequired) {
		this.educationRequired = educationRequired;
	}

	@Column(name="FULL_TM_EDU_FLG")
	public String getFullTimeEducationRequired() {
		return fullTimeEducationRequired;
	}

	public void setFullTimeEducationRequired(String fullTimeEducationRequired) {
		this.fullTimeEducationRequired = fullTimeEducationRequired;
	}

	@Column(name="OFER_REQ_DESC")
	public String getOfferRequiredDesc() {
		return offerRequiredDesc;
	}

	public void setOfferRequiredDesc(String offerRequiredDesc) {
		this.offerRequiredDesc = offerRequiredDesc;
	}

	@Column(name="REMK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SYS_REF_PRJ")
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}

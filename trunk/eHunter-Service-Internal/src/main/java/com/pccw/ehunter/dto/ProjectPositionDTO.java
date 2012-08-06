package com.pccw.ehunter.dto;

import java.util.Date;

public class ProjectPositionDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String systemPositionRefNum;
	private String positionName;
	private String positionType;
	private String department;
	private String reportTo;
	private int branchNum;
	private Date expireDate;
	private int yearlySalary;
	private String dutyDescription;
	private int ageFrom;
	private int ageTo;
	private String genderRequired;
	private String subjectRequired;
	private int jobExperienceRequired;
	private String degreeRequired;
	private String fullTimeEduRequired;
	private String remark;

	public String getSystemPositionRefNum() {
		return systemPositionRefNum;
	}

	public void setSystemPositionRefNum(String systemPositionRefNum) {
		this.systemPositionRefNum = systemPositionRefNum;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getPositionType() {
		return positionType;
	}

	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getReportTo() {
		return reportTo;
	}

	public void setReportTo(String reportTo) {
		this.reportTo = reportTo;
	}

	public int getBranchNum() {
		return branchNum;
	}

	public void setBranchNum(int branchNum) {
		this.branchNum = branchNum;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public int getYearlySalary() {
		return yearlySalary;
	}

	public void setYearlySalary(int yearlySalary) {
		this.yearlySalary = yearlySalary;
	}

	public String getDutyDescription() {
		return dutyDescription;
	}

	public void setDutyDescription(String dutyDescription) {
		this.dutyDescription = dutyDescription;
	}

	public int getAgeFrom() {
		return ageFrom;
	}

	public void setAgeFrom(int ageFrom) {
		this.ageFrom = ageFrom;
	}

	public int getAgeTo() {
		return ageTo;
	}

	public void setAgeTo(int ageTo) {
		this.ageTo = ageTo;
	}

	public String getGenderRequired() {
		return genderRequired;
	}

	public void setGenderRequired(String genderRequired) {
		this.genderRequired = genderRequired;
	}

	public String getSubjectRequired() {
		return subjectRequired;
	}

	public void setSubjectRequired(String subjectRequired) {
		this.subjectRequired = subjectRequired;
	}

	public int getJobExperienceRequired() {
		return jobExperienceRequired;
	}

	public void setJobExperienceRequired(int jobExperienceRequired) {
		this.jobExperienceRequired = jobExperienceRequired;
	}

	public String getDegreeRequired() {
		return degreeRequired;
	}

	public void setDegreeRequired(String degreeRequired) {
		this.degreeRequired = degreeRequired;
	}

	public String getFullTimeEduRequired() {
		return fullTimeEduRequired;
	}

	public void setFullTimeEduRequired(String fullTimeEduRequired) {
		this.fullTimeEduRequired = fullTimeEduRequired;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

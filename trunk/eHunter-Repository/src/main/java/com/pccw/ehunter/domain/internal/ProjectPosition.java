package com.pccw.ehunter.domain.internal;

import java.util.Date;
import java.util.List;

import com.pccw.ehunter.domain.BaseEntity;

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
	
	private List<PositionAnnualLeaveWelfare> annualLeaveWelfares;
	private List<PositionSocityWelfare> socityWelfares;
	private List<PositionSalaryCompose> salaryComposes;
	private List<PositionKeyWord> keyWords;
	private List<PositionLanguageRequired> languageRequired;

	public List<PositionAnnualLeaveWelfare> getAnnualLeaveWelfares() {
		return annualLeaveWelfares;
	}

	public void setAnnualLeaveWelfares(
			List<PositionAnnualLeaveWelfare> annualLeaveWelfares) {
		this.annualLeaveWelfares = annualLeaveWelfares;
	}

	public List<PositionSocityWelfare> getSocityWelfares() {
		return socityWelfares;
	}

	public void setSocityWelfares(List<PositionSocityWelfare> socityWelfares) {
		this.socityWelfares = socityWelfares;
	}

	public List<PositionSalaryCompose> getSalaryComposes() {
		return salaryComposes;
	}

	public void setSalaryComposes(List<PositionSalaryCompose> salaryComposes) {
		this.salaryComposes = salaryComposes;
	}

	public List<PositionKeyWord> getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(List<PositionKeyWord> keyWords) {
		this.keyWords = keyWords;
	}

	public List<PositionLanguageRequired> getLanguageRequired() {
		return languageRequired;
	}

	public void setLanguageRequired(List<PositionLanguageRequired> languageRequired) {
		this.languageRequired = languageRequired;
	}

	public String getSystemPositionRefNum() {
		return systemPositionRefNum;
	}

	public void setSystemPositionRefNum(String systemPositionRefNum) {
		this.systemPositionRefNum = systemPositionRefNum;
	}

	public String getPostionType() {
		return postionType;
	}

	public void setPostionType(String postionType) {
		this.postionType = postionType;
	}

	public String getPostionName() {
		return postionName;
	}

	public void setPostionName(String postionName) {
		this.postionName = postionName;
	}

	public String getPostionDeparment() {
		return postionDeparment;
	}

	public void setPostionDeparment(String postionDeparment) {
		this.postionDeparment = postionDeparment;
	}

	public String getReportTo() {
		return reportTo;
	}

	public void setReportTo(String reportTo) {
		this.reportTo = reportTo;
	}

	public int getUnderlingNumber() {
		return underlingNumber;
	}

	public void setUnderlingNumber(int underlingNumber) {
		this.underlingNumber = underlingNumber;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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

	public String getJobExperienceRequired() {
		return jobExperienceRequired;
	}

	public void setJobExperienceRequired(String jobExperienceRequired) {
		this.jobExperienceRequired = jobExperienceRequired;
	}

	public String getEducationRequired() {
		return educationRequired;
	}

	public void setEducationRequired(String educationRequired) {
		this.educationRequired = educationRequired;
	}

	public String getFullTimeEducationRequired() {
		return fullTimeEducationRequired;
	}

	public void setFullTimeEducationRequired(String fullTimeEducationRequired) {
		this.fullTimeEducationRequired = fullTimeEducationRequired;
	}

	public String getOfferRequiredDesc() {
		return offerRequiredDesc;
	}

	public void setOfferRequiredDesc(String offerRequiredDesc) {
		this.offerRequiredDesc = offerRequiredDesc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

package com.pccw.ehunter.dto;

import java.util.List;

public class PositionRequirementDTO extends BaseDTO {
	private static final long serialVersionUID = -6064997412158930410L;

	private String ageFrom;
	private String ageTo;
	private String gender;
	private String major;
	private SubjectDTO majorDto;
	private String workExperience;
	private String degree;
	private String degreeDto;
	private String ftEduIndicator;
	private String[] language;
	private String duty;
	private List<String> keywords;
	private String remark;

	public String getAgeFrom() {
		return ageFrom;
	}

	public void setAgeFrom(String ageFrom) {
		this.ageFrom = ageFrom;
	}

	public String getAgeTo() {
		return ageTo;
	}

	public void setAgeTo(String ageTo) {
		this.ageTo = ageTo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public SubjectDTO getMajorDto() {
		return majorDto;
	}

	public void setMajorDto(SubjectDTO majorDto) {
		this.majorDto = majorDto;
	}

	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getDegreeDto() {
		return degreeDto;
	}

	public void setDegreeDto(String degreeDto) {
		this.degreeDto = degreeDto;
	}

	public String getFtEduIndicator() {
		return ftEduIndicator;
	}

	public void setFtEduIndicator(String ftEduIndicator) {
		this.ftEduIndicator = ftEduIndicator;
	}

	public String[] getLanguage() {
		return language;
	}

	public void setLanguage(String[] language) {
		this.language = language;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

package com.pccw.ehunter.dto;

import java.util.List;

public class PositionRequirementDTO extends BaseDTO {
	private static final long serialVersionUID = -6064997412158930410L;

	private String ageFrom;
	private String ageTo;
	private String gender;
	private String majorCategory;
	private SubjectCategoryDTO majorCategoryDto;
	private String workExperience;
	private String degree;
	private DegreeDTO degreeDto;
	private String ftEduIndicator;
	private String[] language;
	private String duty;
	private String[] keyWords = new String[5];
	private String expectIndustries;
	private List<IndustryDTO> expectIndustryDtos;
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

	public String getMajorCategory() {
		return majorCategory;
	}

	public void setMajorCategory(String majorCategory) {
		this.majorCategory = majorCategory;
	}

	public SubjectCategoryDTO getMajorCategoryDto() {
		return majorCategoryDto;
	}

	public void setMajorCategoryDto(SubjectCategoryDTO majorCategoryDto) {
		this.majorCategoryDto = majorCategoryDto;
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

	public DegreeDTO getDegreeDto() {
		return degreeDto;
	}

	public void setDegreeDto(DegreeDTO degreeDto) {
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

	public String[] getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String[] keyWords) {
		this.keyWords = keyWords;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getExpectIndustries() {
		return expectIndustries;
	}

	public void setExpectIndustries(String expectIndustries) {
		this.expectIndustries = expectIndustries;
	}

	public List<IndustryDTO> getExpectIndustryDtos() {
		return expectIndustryDtos;
	}

	public void setExpectIndustryDtos(List<IndustryDTO> expectIndustryDtos) {
		this.expectIndustryDtos = expectIndustryDtos;
	}

}

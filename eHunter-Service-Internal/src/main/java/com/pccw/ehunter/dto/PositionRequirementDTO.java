package com.pccw.ehunter.dto;

import java.util.List;

public class PositionRequirementDTO extends BaseDTO {
	private static final long serialVersionUID = -6064997412158930410L;

	private String systemPositionRefNum;
	private String ageFromStr;
	private String ageToStr;
	private String gender;
	private String majorCategory;
	private SubjectCategoryDTO majorCategoryDto;
	private String workExperienceStr;
	private String degree;
	private DegreeDTO degreeDto;
	private String ftEduIndicator;
	private String[] language;
	private String languageStr;
	private String duty;
	private String expectIndustries;
	private List<IndustryDTO> expectIndustryDtos;
	private String remark;

	public String getSystemPositionRefNum() {
		return systemPositionRefNum;
	}

	public void setSystemPositionRefNum(String systemPositionRefNum) {
		this.systemPositionRefNum = systemPositionRefNum;
	}

	public String getAgeFromStr() {
		return ageFromStr;
	}

	public void setAgeFromStr(String ageFromStr) {
		this.ageFromStr = ageFromStr;
	}

	public String getAgeToStr() {
		return ageToStr;
	}

	public void setAgeToStr(String ageToStr) {
		this.ageToStr = ageToStr;
	}

	public String getWorkExperienceStr() {
		return workExperienceStr;
	}

	public void setWorkExperienceStr(String workExperienceStr) {
		this.workExperienceStr = workExperienceStr;
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

	public String getLanguageStr() {
		return languageStr;
	}

	public void setLanguageStr(String languageStr) {
		this.languageStr = languageStr;
	}

}

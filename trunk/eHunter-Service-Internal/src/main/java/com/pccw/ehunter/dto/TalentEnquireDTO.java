package com.pccw.ehunter.dto;

import java.io.Serializable;

public class TalentEnquireDTO implements Serializable {
	private static final long serialVersionUID = 2002413011835066426L;

	private String talentID;
	private String name;
	private String maritalStatus;
	private String talentSrc;
	
	private String systemProjectRefNum;
	private String searchIndicator;
	private String ageFrom;
	private String ageTo;
	private String gender;
	private String majorCategory;
	private String workExperience;
	private String degree;
	private String ftEduIndicator;
	private String expectIndustries;
	
	private String keywords;
	private String queryMode;//P-represents precise inquiry,F - represents fuzzy query
	private String matchMode;//P - represents partial matching , F - represents full matching

	private JmesaCheckBoxDTO jmesaDto = new JmesaCheckBoxDTO();

	public String getTalentID() {
		return talentID;
	}

	public void setTalentID(String talentID) {
		this.talentID = talentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getTalentSrc() {
		return talentSrc;
	}

	public void setTalentSrc(String talentSrc) {
		this.talentSrc = talentSrc;
	}

	public JmesaCheckBoxDTO getJmesaDto() {
		return jmesaDto;
	}

	public void setJmesaDto(JmesaCheckBoxDTO jmesaDto) {
		this.jmesaDto = jmesaDto;
	}

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

	public String getMajorCategory() {
		return majorCategory;
	}

	public void setMajorCategory(String majorCategory) {
		this.majorCategory = majorCategory;
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

	public String getFtEduIndicator() {
		return ftEduIndicator;
	}

	public void setFtEduIndicator(String ftEduIndicator) {
		this.ftEduIndicator = ftEduIndicator;
	}

	public String getExpectIndustries() {
		return expectIndustries;
	}

	public void setExpectIndustries(String expectIndustries) {
		this.expectIndustries = expectIndustries;
	}

	public String getSystemProjectRefNum() {
		return systemProjectRefNum;
	}

	public void setSystemProjectRefNum(String systemProjectRefNum) {
		this.systemProjectRefNum = systemProjectRefNum;
	}

	public String getSearchIndicator() {
		return searchIndicator;
	}

	public void setSearchIndicator(String searchIndicator) {
		this.searchIndicator = searchIndicator;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getQueryMode() {
		return queryMode;
	}

	public void setQueryMode(String queryMode) {
		this.queryMode = queryMode;
	}

	public String getMatchMode() {
		return matchMode;
	}

	public void setMatchMode(String matchMode) {
		this.matchMode = matchMode;
	}
}

package com.pccw.ehunter.dto;

public class TalentPagedCriteria extends PagedCriteria {
	private static final long serialVersionUID = 1316229932527904788L;

	private String talentID;
	private String name;
	private String gender;
	private String maritalStatus;
	private String talentSrc;

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

}

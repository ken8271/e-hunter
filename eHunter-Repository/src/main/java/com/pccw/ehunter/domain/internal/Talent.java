package com.pccw.ehunter.domain.internal;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name = "T_TLNT_BS_INF")
public class Talent extends BaseEntity {
	private static final long serialVersionUID = 3598927769117045074L;
	
	private String talentID;
	private String talentSrc;
	private String cnName;
	private String enName;
	private String gender;
	private String maritalStatus;
	private Date birthDate;
	private String nativePlace;
	private String nowLivePlace;
	private String onceLivePlace;
	private String highestDegree;

	private String homeNumber;
	private String companyNumber;
	private String mobilePhone1;
	private String mobilePhone2;
	private String email;
	private String homeAddress;
	
	private List<Resume> resumes;

	@Id
	@Column(name="SYS_REF_TLNT")
	public String getTalentID() {
		return talentID;
	}

	public void setTalentID(String talentID) {
		this.talentID = talentID;
	}

	@Column(name="TLNT_SRC")
	public String getTalentSrc() {
		return talentSrc;
	}

	public void setTalentSrc(String talentSrc) {
		this.talentSrc = talentSrc;
	}

	@Column(name="CNM")
	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	@Column(name="ENM")
	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	@Column(name="SEX")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name="MAR_STAT")
	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	@Column(name="BRTH_DTTM")
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name="NTV_PLCE")
	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	@Column(name="NW_LV_PLCE")
	public String getNowLivePlace() {
		return nowLivePlace;
	}

	public void setNowLivePlace(String nowLivePlace) {
		this.nowLivePlace = nowLivePlace;
	}

	@Column(name="ONC_LV_PLCE")
	public String getOnceLivePlace() {
		return onceLivePlace;
	}

	public void setOnceLivePlace(String onceLivePlace) {
		this.onceLivePlace = onceLivePlace;
	}

	@Column(name="HGST_DGRE")
	public String getHighestDegree() {
		return highestDegree;
	}

	public void setHighestDegree(String highestDegree) {
		this.highestDegree = highestDegree;
	}

	@Column(name="HM_TEL_NBR")
	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	@Column(name="CO_TEL_NBR")
	public String getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}

	@Column(name="PRI_TEL_NBR1")
	public String getMobilePhone1() {
		return mobilePhone1;
	}

	public void setMobilePhone1(String mobilePhone1) {
		this.mobilePhone1 = mobilePhone1;
	}

	@Column(name="PRI_TEL_NBR2")
	public String getMobilePhone2() {
		return mobilePhone2;
	}

	public void setMobilePhone2(String mobilePhone2) {
		this.mobilePhone2 = mobilePhone2;
	}

	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="HM_ADDR")
	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy="talent" , cascade = CascadeType.ALL)
	public List<Resume> getResumes() {
		return resumes;
	}

	public void setResumes(List<Resume> resumes) {
		this.resumes = resumes;
	}

}

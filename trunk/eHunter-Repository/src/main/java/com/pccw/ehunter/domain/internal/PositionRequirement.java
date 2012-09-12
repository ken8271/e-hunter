package com.pccw.ehunter.domain.internal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name = "T_PRJ_POST_REQ")
public class PositionRequirement extends BaseEntity {
	private static final long serialVersionUID = 8165879419024337877L;

	private String systemPositionRefNum;
	private Project project;
	private Integer ageFrom;
	private Integer ageTo;
	private String gender;
	private String majorCategory;
	private Integer workExperience;
	private String degree;
	private String ftEduIndicator;
	private String languageStr;
	private String duty;
	private String expectIndustries;
	private String remark;

	@Id
	@Column(name="SYS_REF_POST")
	public String getSystemPositionRefNum() {
		return systemPositionRefNum;
	}

	public void setSystemPositionRefNum(String systemPositionRefNum) {
		this.systemPositionRefNum = systemPositionRefNum;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn(name = "SYS_REF_POST", referencedColumnName = "SYS_REF_PRJ")
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name="AG_FR")
	public Integer getAgeFrom() {
		return ageFrom;
	}

	public void setAgeFrom(Integer ageFrom) {
		this.ageFrom = ageFrom;
	}

	@Column(name="AG_TO")
	public Integer getAgeTo() {
		return ageTo;
	}

	public void setAgeTo(Integer ageTo) {
		this.ageTo = ageTo;
	}

	@Column(name="GNDR_REQ")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name="SUBJ_REQ")
	public String getMajorCategory() {
		return majorCategory;
	}

	public void setMajorCategory(String majorCategory) {
		this.majorCategory = majorCategory;
	}
	
	@Column(name="JOB_EXP_REQ")
	public Integer getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(Integer workExperience) {
		this.workExperience = workExperience;
	}
	
	@Column(name="EDU_REQ")
	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	@Column(name="FL_TM_EDU_FLG")
	public String getFtEduIndicator() {
		return ftEduIndicator;
	}

	public void setFtEduIndicator(String ftEduIndicator) {
		this.ftEduIndicator = ftEduIndicator;
	}

	@Column(name="LAN_REQ")
	public String getLanguageStr() {
		return languageStr;
	}

	public void setLanguageStr(String languageStr) {
		this.languageStr = languageStr;
	}

	@Column(name="OFER_REQ_DESC")
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	@Column(name="EXPT_INDS")
	public String getExpectIndustries() {
		return expectIndustries;
	}

	public void setExpectIndustries(String expectIndustries) {
		this.expectIndustries = expectIndustries;
	}

	@Column(name="REMK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

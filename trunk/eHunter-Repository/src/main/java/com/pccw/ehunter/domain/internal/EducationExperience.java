package com.pccw.ehunter.domain.internal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name = "T_TLNT_EDU_EXP")
public class EducationExperience extends BaseEntity {

	private EducationExperiencePK pk;
	private Date fromDate;
	private Date toDate;
	private String school;
	private String major;
	private String degree;

	@EmbeddedId
	public EducationExperiencePK getPk() {
		return pk;
	}

	public void setPk(EducationExperiencePK pk) {
		this.pk = pk;
	}

	@Column(name="BEGN_DTTM")
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@Column(name="GRDUAT_DTTM")
	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Column(name="SCHOOL")
	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Column(name="MAJOR")
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name="DEGREE")
	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

}

package com.pccw.ehunter.domain.internal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name = "T_TLNT_TRN_EXP")
public class TrainingExperience extends BaseEntity {
	private TrainingExperiencePK pk;
	private Date fromDate;
	private Date toDate;
	private String organization;
	private String address;
	private String course;
	private String cert;
	private String description;

	@EmbeddedId
	public TrainingExperiencePK getPk() {
		return pk;
	}

	public void setPk(TrainingExperiencePK pk) {
		this.pk = pk;
	}

	@Column(name="BEGN_DTTM")
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@Column(name="END_DTTM")
	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Column(name="TRN_ORG")
	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Column(name="TRN_ADDR")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="TRN_COUS")
	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	@Column(name="GAIN_CERT")
	public String getCert() {
		return cert;
	}

	public void setCert(String cert) {
		this.cert = cert;
	}

	@Column(name="TRN_DESC")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

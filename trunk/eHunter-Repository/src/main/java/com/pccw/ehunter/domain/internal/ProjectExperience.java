package com.pccw.ehunter.domain.internal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="T_TLNT_PRJ_EXP")
public class ProjectExperience {
	private ProjectExperiencePK pk;
	private Date fromDate;
	private Date toDate;
	private String name;
	private String duty;
	private String description;

	@EmbeddedId
	public ProjectExperiencePK getPk() {
		return pk;
	}

	public void setPk(ProjectExperiencePK pk) {
		this.pk = pk;
	}

	@Column(name="BEG_DTTM")
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

	@Column(name="PRJ_NM")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="PRJ_DUTY")
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	@Column(name="PRJ_DESC")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

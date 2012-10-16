package com.pccw.ehunter.domain.internal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name = "T_TLNT_EMP_HST")
public class EmploymentHistory extends BaseEntity {
	private static final long serialVersionUID = -3226320124810090487L;

	private EmploymentHistoryPK pk;
	private Date beginTime;
	private Date endTime;
	private String companyName;
	private String industryCategory;
	private String positions;

	@EmbeddedId
	public EmploymentHistoryPK getPk() {
		return pk;
	}

	public void setPk(EmploymentHistoryPK pk) {
		this.pk = pk;
	}

	@Column(name="BEGN_DTTM")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name="LEV_DTTM")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name="CO_NM")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name="IND_TY")
	public String getIndustryCategory() {
		return industryCategory;
	}

	public void setIndustryCategory(String industryCategory) {
		this.industryCategory = industryCategory;
	}

	@Column(name="POST_TY")
	public String getPositions() {
		return positions;
	}

	public void setPositions(String positions) {
		this.positions = positions;
	}

}

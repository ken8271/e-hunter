package com.pccw.ehunter.domain.internal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name = "T_TLNT_CONT_HST")
public class CandidateContactHistory extends BaseEntity {
	private static final long serialVersionUID = 4876069309536406529L;

	private String systemContactRefNum;

	private Talent talent;
	private Project project;

	private String contactCategory;
	private String record;
	private String remark;

	private InternalUser adviser;

	@Id
	@Column(name="SYS_REF_CONT_HST")
	public String getSystemContactRefNum() {
		return systemContactRefNum;
	}

	public void setSystemContactRefNum(String systemContactRefNum) {
		this.systemContactRefNum = systemContactRefNum;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SYS_REF_TLNT", referencedColumnName = "SYS_REF_TLNT", nullable = false)
	public Talent getTalent() {
		return talent;
	}

	public void setTalent(Talent talent) {
		this.talent = talent;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SYS_REF_PRJ", referencedColumnName = "SYS_REF_PRJ", nullable = false)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name="CONT_TY")
	public String getContactCategory() {
		return contactCategory;
	}

	public void setContactCategory(String contactCategory) {
		this.contactCategory = contactCategory;
	}

	@Column(name="CONT_REC")
	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	@Column(name="CONT_RMRK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONT_ADVSR", referencedColumnName = "USR_REC_ID", nullable = false)
	public InternalUser getAdviser() {
		return adviser;
	}

	public void setAdviser(InternalUser adviser) {
		this.adviser = adviser;
	}

}

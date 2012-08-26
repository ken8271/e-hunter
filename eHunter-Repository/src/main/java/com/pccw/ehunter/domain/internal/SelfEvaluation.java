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
@Table(name = "T_TLNT_SLF_EVLUTN")
public class SelfEvaluation extends BaseEntity {
	private static final long serialVersionUID = 3776484805403208957L;
	
	private String resumeID;
	private Resume resume;
	private String content;

	@Id
	@Column(name="SYS_REF_RSUM")
	public String getResumeID() {
		return resumeID;
	}

	public void setResumeID(String resumeID) {
		this.resumeID = resumeID;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name = "SYS_REF_RSUM", referencedColumnName = "SYS_REF_RSUM")
	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	@Column(name = "CNT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

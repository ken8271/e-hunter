package com.pccw.ehunter.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name = "T_SKL_TY")
public class SkillCategory extends BaseEntity {
	private static final long serialVersionUID = -2964188132591535502L;
	
	private String code;
	private String displayName;
	private String description;
	private int displaySeqNumber;
	private String activeIndicator;

	@Id
	@Column(name="TY_CD")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="DISP_NM")
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Column(name="CD_DESC")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="DISP_SEQ_NBR")
	public int getDisplaySeqNumber() {
		return displaySeqNumber;
	}

	public void setDisplaySeqNumber(int displaySeqNumber) {
		this.displaySeqNumber = displaySeqNumber;
	}

	@Column(name="ACTV_FLG")
	public String getActiveIndicator() {
		return activeIndicator;
	}

	public void setActiveIndicator(String activeIndicator) {
		this.activeIndicator = activeIndicator;
	}

}

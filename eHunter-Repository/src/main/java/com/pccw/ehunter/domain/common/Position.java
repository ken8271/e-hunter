package com.pccw.ehunter.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name="T_POST_TY_SUB")
public class Position extends BaseEntity{
	private static final long serialVersionUID = -7754380894111880232L;
	
	private String typeCode;
	private String topType;
	private String displayName;
	private String description;
	private int displaySeqNumber;
	private String activeFlag;

	@Id
	@Column(name="TY_CD")
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	@Column(name="TOP_TY")
	public String getTopType() {
		return topType;
	}

	public void setTopType(String topType) {
		this.topType = topType;
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
	public String getActiveFlag() {
		return activeFlag;
	}


	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

}

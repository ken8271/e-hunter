package com.pccw.ehunter.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name="T_POST_TY")
public class PositionCategory extends BaseEntity {
	private String typeCode;
	private String displayName;
	private String discription;
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

	@Column(name="DISP_NM")
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Column(name="CD_DESC")
	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
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

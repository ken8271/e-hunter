package com.pccw.ehunter.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name = "T_DGRE")
public class Degree extends BaseEntity {
	private static final long serialVersionUID = 4907756524081420076L;
	
	private String degreeCode;
	private String displayName;
	private String description;
	private int displayIndexNumber;
	private String activeIndicator;

	@Id
	@Column(name="DGRE_CD")
	public String getDegreeCode() {
		return degreeCode;
	}

	public void setDegreeCode(String degreeCode) {
		this.degreeCode = degreeCode;
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
	public int getDisplayIndexNumber() {
		return displayIndexNumber;
	}

	public void setDisplayIndexNumber(int displayIndexNumber) {
		this.displayIndexNumber = displayIndexNumber;
	}

	@Column(name="ACTV_FLG")
	public String getActiveIndicator() {
		return activeIndicator;
	}

	public void setActiveIndicator(String activeIndicator) {
		this.activeIndicator = activeIndicator;
	}

}

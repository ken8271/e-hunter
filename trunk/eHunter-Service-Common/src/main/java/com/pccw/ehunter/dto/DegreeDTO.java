package com.pccw.ehunter.dto;

public class DegreeDTO extends BaseDTO{
	private static final long serialVersionUID = 1L;
	
	private String degreeCode;
	private String displayName;
	private String description;
	private int displayIndexNumber;
	private String activeIndicator;

	public String getDegreeCode() {
		return degreeCode;
	}

	public void setDegreeCode(String degreeCode) {
		this.degreeCode = degreeCode;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDisplayIndexNumber() {
		return displayIndexNumber;
	}

	public void setDisplayIndexNumber(int displayIndexNumber) {
		this.displayIndexNumber = displayIndexNumber;
	}

	public String getActiveIndicator() {
		return activeIndicator;
	}

	public void setActiveIndicator(String activeIndicator) {
		this.activeIndicator = activeIndicator;
	}
}

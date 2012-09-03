package com.pccw.ehunter.dto;

public class ResidentialWelfareDTO extends BaseDTO {
	private static final long serialVersionUID = 924843295120285308L;
	
	private String welfareCode;
	private String displayName;
	private String description;
	private String activeIndicator;

	public String getWelfareCode() {
		return welfareCode;
	}

	public void setWelfareCode(String welfareCode) {
		this.welfareCode = welfareCode;
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

	public String getActiveIndicator() {
		return activeIndicator;
	}

	public void setActiveIndicator(String activeIndicator) {
		this.activeIndicator = activeIndicator;
	}
}

package com.pccw.ehunter.dto;

public class PositionDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String typeCode;
	private String topType;
	private String displayName;
	private String description;
	private String activeFlag;

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTopType() {
		return topType;
	}

	public void setTopType(String topType) {
		this.topType = topType;
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

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

}

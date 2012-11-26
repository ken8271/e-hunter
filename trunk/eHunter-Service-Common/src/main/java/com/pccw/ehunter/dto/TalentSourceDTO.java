package com.pccw.ehunter.dto;

public class TalentSourceDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String sourceId;
	private String displayName;
	private String officialSite;
	private int displaySeqNumber;
	private String activeIndicator;

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getOfficialSite() {
		return officialSite;
	}

	public void setOfficialSite(String officialSite) {
		this.officialSite = officialSite;
	}

	public int getDisplaySeqNumber() {
		return displaySeqNumber;
	}

	public void setDisplaySeqNumber(int displaySeqNumber) {
		this.displaySeqNumber = displaySeqNumber;
	}

	public String getActiveIndicator() {
		return activeIndicator;
	}

	public void setActiveIndicator(String activeIndicator) {
		this.activeIndicator = activeIndicator;
	}

}

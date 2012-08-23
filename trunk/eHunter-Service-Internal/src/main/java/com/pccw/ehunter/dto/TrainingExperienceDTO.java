package com.pccw.ehunter.dto;

public class TrainingExperienceDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private int itemNumber;
	private SimpleDateDTO fromDateDto;
	private SimpleDateDTO toDateDto;
	private String organization;
	private String address;
	private String course;
	private String cert;
	private String description;

	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	public SimpleDateDTO getFromDateDto() {
		return fromDateDto;
	}

	public void setFromDateDto(SimpleDateDTO fromDateDto) {
		this.fromDateDto = fromDateDto;
	}

	public SimpleDateDTO getToDateDto() {
		return toDateDto;
	}

	public void setToDateDto(SimpleDateDTO toDateDto) {
		this.toDateDto = toDateDto;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getCert() {
		return cert;
	}

	public void setCert(String cert) {
		this.cert = cert;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

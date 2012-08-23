package com.pccw.ehunter.dto;

public class ProjectExperienceDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private int itemNumber;
	private SimpleDateDTO fromDateDto;
	private SimpleDateDTO toDateDto;
	private String name;
	private String duty;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

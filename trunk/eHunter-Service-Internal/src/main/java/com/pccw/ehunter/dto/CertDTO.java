package com.pccw.ehunter.dto;

public class CertDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;
	private int itemNumber;
	private String certName;
	private SimpleDateDTO gainedDateDto;
	private String description;

	public String getCertName() {
		return certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public SimpleDateDTO getGainedDateDto() {
		return gainedDateDto;
	}

	public void setGainedDateDto(SimpleDateDTO gainedDateDto) {
		this.gainedDateDto = gainedDateDto;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

}

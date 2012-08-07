package com.pccw.ehunter.dto;

public class CustomerResponsePersonDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String systemRespRefNum;
	private String name;
	private String positionType;
	private String positionName;
	private MobilePhoneDTO telephoneDto;
	private String email;
	private String status;

	public String getSystemRespRefNum() {
		return systemRespRefNum;
	}

	public void setSystemRespRefNum(String systemRespRefNum) {
		this.systemRespRefNum = systemRespRefNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPositionType() {
		return positionType;
	}

	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public MobilePhoneDTO getTelephoneDto() {
		return telephoneDto;
	}

	public void setTelephoneDto(MobilePhoneDTO telephoneDto) {
		this.telephoneDto = telephoneDto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

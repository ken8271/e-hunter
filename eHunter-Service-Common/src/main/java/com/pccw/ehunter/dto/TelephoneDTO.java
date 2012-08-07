package com.pccw.ehunter.dto;

import java.io.Serializable;

public class TelephoneDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String stateCode;
	private String regionCode;
	private String phoneNumber;

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return regionCode + "-" + phoneNumber;
	}

}

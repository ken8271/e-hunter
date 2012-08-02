package com.pccw.ehunter.dto;

import java.io.Serializable;

public class MobilePhone implements Serializable {
	private static final long serialVersionUID = 1L;

	private String stateCode;
	private String phoneNumber;

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return phoneNumber;
	}

}

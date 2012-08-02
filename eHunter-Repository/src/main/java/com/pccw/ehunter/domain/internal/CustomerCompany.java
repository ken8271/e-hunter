package com.pccw.ehunter.domain.internal;

import com.pccw.ehunter.domain.BaseEntity;

public class CustomerCompany extends BaseEntity {

	private String systemCustRefNum;
	private String shortName;
	private String fullName;
	private String grade;
	private String status;
	private String offcialSite;
	private String telExchange;
	private String type;
	private String size;
	private String groupIndicator;
	
	public String getSystemCustRefNum() {
		return systemCustRefNum;
	}

	public void setSystemCustRefNum(String systemCustRefNum) {
		this.systemCustRefNum = systemCustRefNum;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOffcialSite() {
		return offcialSite;
	}

	public void setOffcialSite(String offcialSite) {
		this.offcialSite = offcialSite;
	}

	public String getTelExchange() {
		return telExchange;
	}

	public void setTelExchange(String telExchange) {
		this.telExchange = telExchange;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getGroupIndicator() {
		return groupIndicator;
	}

	public void setGroupIndicator(String groupIndicator) {
		this.groupIndicator = groupIndicator;
	}

}

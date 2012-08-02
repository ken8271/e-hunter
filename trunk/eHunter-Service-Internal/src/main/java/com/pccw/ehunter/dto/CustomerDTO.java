package com.pccw.ehunter.dto;

public class CustomerDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String systemCustRefNum;
	private String shortName;
	private String fullName;
	private String grade;
	private String status;
	private String offcialSite;
	private Telephone telExchange;
	private String type;
	private String size;
	private String groupIndicator;
	
	private CustomerResponsePersonDTO custRespPerson;
	private CustomerGroupDTO custGroup;

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

	public Telephone getTelExchange() {
		return telExchange;
	}

	public void setTelExchange(Telephone telExchange) {
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

	public CustomerResponsePersonDTO getCustRespPerson() {
		return custRespPerson;
	}

	public void setCustRespPerson(CustomerResponsePersonDTO custRespPerson) {
		this.custRespPerson = custRespPerson;
	}

	public CustomerGroupDTO getCustGroup() {
		return custGroup;
	}

	public void setCustGroup(CustomerGroupDTO custGroup) {
		this.custGroup = custGroup;
	}
}

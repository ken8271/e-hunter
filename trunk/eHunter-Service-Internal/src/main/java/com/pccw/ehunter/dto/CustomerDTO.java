package com.pccw.ehunter.dto;

import java.util.ArrayList;
import java.util.List;

public class CustomerDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String systemCustRefNum;
	private String shortName;
	private String fullName;
	private String grade;
	private CustomerGradeDTO gradeDto;
	private String status;
	private CustomerStatusDTO statusDto;
	private String offcialSite;
	private TelephoneDTO telExchangeDto;
	private String type;
	private CompanyCategoryDTO typeDto;
	private String size;
	private CompanySizeDTO sizeDto;
	private String groupIndicator;
	private String customerDescription;
	
	private CustomerGroupDTO custGroup;
	private List<CustomerResponsePersonDTO> multiResponsePerson = new ArrayList<CustomerResponsePersonDTO>();
	
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

	public TelephoneDTO getTelExchangeDto() {
		return telExchangeDto;
	}

	public void setTelExchangeDto(TelephoneDTO telExchangeDto) {
		this.telExchangeDto = telExchangeDto;
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

	public List<CustomerResponsePersonDTO> getMultiResponsePerson() {
		return multiResponsePerson;
	}

	public void setMultiResponsePerson(List<CustomerResponsePersonDTO> multiResponsePerson) {
		this.multiResponsePerson = multiResponsePerson;
	}

	public CustomerGroupDTO getCustGroup() {
		return custGroup;
	}

	public void setCustGroup(CustomerGroupDTO custGroup) {
		this.custGroup = custGroup;
	}

	public CustomerGradeDTO getGradeDto() {
		return gradeDto;
	}

	public void setGradeDto(CustomerGradeDTO gradeDto) {
		this.gradeDto = gradeDto;
	}

	public CustomerStatusDTO getStatusDto() {
		return statusDto;
	}

	public void setStatusDto(CustomerStatusDTO statusDto) {
		this.statusDto = statusDto;
	}

	public CompanyCategoryDTO getTypeDto() {
		return typeDto;
	}

	public void setTypeDto(CompanyCategoryDTO typeDto) {
		this.typeDto = typeDto;
	}

	public CompanySizeDTO getSizeDto() {
		return sizeDto;
	}

	public void setSizeDto(CompanySizeDTO sizeDto) {
		this.sizeDto = sizeDto;
	}

	public String getCustomerDescription() {
		return customerDescription;
	}

	public void setCustomerDescription(String customerDescription) {
		this.customerDescription = customerDescription;
	}
}

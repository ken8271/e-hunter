package com.pccw.ehunter.dto;

public class CustomerContactHistoryDTO extends BaseDTO {
	private static final long serialVersionUID = -7956913030826978881L;

	private String systemContactRefNum;
	private CustomerDTO customerDto;
	private CustomerResponsePersonDTO responsePersonDto;
	private InternalUserDTO adviserDto;
	private String content;
	private String remark;

	public String getSystemContactRefNum() {
		return systemContactRefNum;
	}

	public void setSystemContactRefNum(String systemContactRefNum) {
		this.systemContactRefNum = systemContactRefNum;
	}

	public CustomerDTO getCustomerDto() {
		return customerDto;
	}

	public void setCustomerDto(CustomerDTO customerDto) {
		this.customerDto = customerDto;
	}

	public CustomerResponsePersonDTO getResponsePersonDto() {
		return responsePersonDto;
	}

	public void setResponsePersonDto(CustomerResponsePersonDTO responsePersonDto) {
		this.responsePersonDto = responsePersonDto;
	}

	public InternalUserDTO getAdviserDto() {
		return adviserDto;
	}

	public void setAdviserDto(InternalUserDTO adviserDto) {
		this.adviserDto = adviserDto;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

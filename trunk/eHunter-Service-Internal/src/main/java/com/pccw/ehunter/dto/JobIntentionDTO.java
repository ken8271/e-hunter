package com.pccw.ehunter.dto;

public class JobIntentionDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String resumeID;
	private String employmentCategory ;
	private String expectAddress;
	private String expectPosition;
	private String expectIndustry;
	private String expectSalary;

	private PositionCategoryDTO expectPositionDto;
	private IndustryCategoryDTO expectIndustryDto;

	public String getResumeID() {
		return resumeID;
	}

	public void setResumeID(String resumeID) {
		this.resumeID = resumeID;
	}

	public String getEmploymentCategory() {
		return employmentCategory;
	}

	public void setEmploymentCategory(String employmentCategory) {
		this.employmentCategory = employmentCategory;
	}

	public String getExpectAddress() {
		return expectAddress;
	}

	public void setExpectAddress(String expectAddress) {
		this.expectAddress = expectAddress;
	}

	public String getExpectPosition() {
		return expectPosition;
	}

	public void setExpectPosition(String expectPosition) {
		this.expectPosition = expectPosition;
	}

	public String getExpectIndustry() {
		return expectIndustry;
	}

	public void setExpectIndustry(String expectIndustry) {
		this.expectIndustry = expectIndustry;
	}

	public String getExpectSalary() {
		return expectSalary;
	}

	public void setExpectSalary(String expectSalary) {
		this.expectSalary = expectSalary;
	}

	public PositionCategoryDTO getExpectPositionDto() {
		return expectPositionDto;
	}

	public void setExpectPositionDto(PositionCategoryDTO expectPositionDto) {
		this.expectPositionDto = expectPositionDto;
	}

	public IndustryCategoryDTO getExpectIndustryDto() {
		return expectIndustryDto;
	}

	public void setExpectIndustryDto(IndustryCategoryDTO expectIndustryDto) {
		this.expectIndustryDto = expectIndustryDto;
	}

}

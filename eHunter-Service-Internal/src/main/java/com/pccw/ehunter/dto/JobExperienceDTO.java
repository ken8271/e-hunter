package com.pccw.ehunter.dto;

public class JobExperienceDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private SimpleDateDTO fromDateDto;
	private SimpleDateDTO toDateDto;
	private String companyName;
	private CompanyTypeDTO companyTypeDto;
	private CompanySizeDTO companySizeDto;
	private IndustryDTO industryDto;
	private String department;
	private PositionCategoryDTO positionTypeDto;
	private String positionName;
	private String jobDescription;

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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public CompanyTypeDTO getCompanyTypeDto() {
		return companyTypeDto;
	}

	public void setCompanyTypeDto(CompanyTypeDTO companyTypeDto) {
		this.companyTypeDto = companyTypeDto;
	}

	public CompanySizeDTO getCompanySizeDto() {
		return companySizeDto;
	}

	public void setCompanySizeDto(CompanySizeDTO companySizeDto) {
		this.companySizeDto = companySizeDto;
	}

	public IndustryDTO getIndustryDto() {
		return industryDto;
	}

	public void setIndustryDto(IndustryDTO industryDto) {
		this.industryDto = industryDto;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public PositionCategoryDTO getPositionTypeDto() {
		return positionTypeDto;
	}

	public void setPositionTypeDto(PositionCategoryDTO positionTypeDto) {
		this.positionTypeDto = positionTypeDto;
	}

}

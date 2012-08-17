package com.pccw.ehunter.dto;

public class JobExperienceDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private SimpleDateDTO fromDateDto;
	private SimpleDateDTO toDateDto;
	private String companyName;
	private String department;
	private String positionName;
	private String jobDescription;

	private CompanyCategoryDTO companyCategoryDto;
	private CompanySizeDTO companySizeDto;
	private IndustryCategoryDTO industryCategoryDto;
	private IndustryDTO industryDto;
	private PositionCategoryDTO positionCategoryDto;
	private PositionDTO positionDto;

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

	public PositionCategoryDTO getPositionCategoryDto() {
		return positionCategoryDto;
	}

	public void setPositionCategoryDto(PositionCategoryDTO positionCategoryDto) {
		this.positionCategoryDto = positionCategoryDto;
	}

	public CompanyCategoryDTO getCompanyCategoryDto() {
		return companyCategoryDto;
	}

	public void setCompanyCategoryDto(CompanyCategoryDTO companyCategoryDto) {
		this.companyCategoryDto = companyCategoryDto;
	}

	public IndustryCategoryDTO getIndustryCategoryDto() {
		return industryCategoryDto;
	}

	public void setIndustryCategoryDto(IndustryCategoryDTO industryCategoryDto) {
		this.industryCategoryDto = industryCategoryDto;
	}

	public PositionDTO getPositionDto() {
		return positionDto;
	}

	public void setPositionDto(PositionDTO positionDto) {
		this.positionDto = positionDto;
	}

}

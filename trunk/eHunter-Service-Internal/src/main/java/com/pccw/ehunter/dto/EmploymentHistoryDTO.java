package com.pccw.ehunter.dto;

import java.util.List;

public class EmploymentHistoryDTO extends BaseDTO {
	private static final long serialVersionUID = -8942268416317626753L;

	private int itemNumber;
	private SimpleDateDTO beginTimeDto;
	private SimpleDateDTO endTimeDto;
	private String companyName;
	private String industryCategory;
	private String industry;
	private String positions;

	private IndustryCategoryDTO industryCategoryDto;
	private IndustryDTO industryDto;
	private List<PositionDTO> positionDtos;

	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	public SimpleDateDTO getBeginTimeDto() {
		return beginTimeDto;
	}

	public void setBeginTimeDto(SimpleDateDTO beginTimeDto) {
		this.beginTimeDto = beginTimeDto;
	}

	public SimpleDateDTO getEndTimeDto() {
		return endTimeDto;
	}

	public void setEndTimeDto(SimpleDateDTO endTimeDto) {
		this.endTimeDto = endTimeDto;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIndustryCategory() {
		return industryCategory;
	}

	public void setIndustryCategory(String industryCategory) {
		this.industryCategory = industryCategory;
	}

	public String getPositions() {
		return positions;
	}

	public void setPositions(String positions) {
		this.positions = positions;
	}

	public IndustryCategoryDTO getIndustryCategoryDto() {
		return industryCategoryDto;
	}

	public void setIndustryCategoryDto(IndustryCategoryDTO industryCategoryDto) {
		this.industryCategoryDto = industryCategoryDto;
	}

	public List<PositionDTO> getPositionDtos() {
		return positionDtos;
	}

	public void setPositionDtos(List<PositionDTO> positionDtos) {
		this.positionDtos = positionDtos;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public IndustryDTO getIndustryDto() {
		return industryDto;
	}

	public void setIndustryDto(IndustryDTO industryDto) {
		this.industryDto = industryDto;
	}

}

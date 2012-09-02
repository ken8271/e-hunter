package com.pccw.ehunter.dto;

import java.util.List;

public class PositionDescriptionDTO extends BaseDTO {
	private static final long serialVersionUID = -6282581877255631471L;

	private String positionCategory;
	private PositionCategoryDTO positionCategoryDto;
	private String position;
	private PositionDTO positionDto;
	private String positionName;
	private String department;
	private String reportTarget;
	private String[] cities;
	private List<CityDTO> cityDtos;
	private SimpleDateDTO expiryDateDto;
	private String salaryFrom;
	private String salaryTo;
	private String[] salaryStructure;
	private String[] socityWelfare;
	private String[] residentialWelfare;
	private String[] annualLeaveWelfare;
	private String dutyDescription;

	public String getPositionCategory() {
		return positionCategory;
	}

	public void setPositionCategory(String positionCategory) {
		this.positionCategory = positionCategory;
	}

	public PositionCategoryDTO getPositionCategoryDto() {
		return positionCategoryDto;
	}

	public void setPositionCategoryDto(PositionCategoryDTO positionCategoryDto) {
		this.positionCategoryDto = positionCategoryDto;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public PositionDTO getPositionDto() {
		return positionDto;
	}

	public void setPositionDto(PositionDTO positionDto) {
		this.positionDto = positionDto;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getReportTarget() {
		return reportTarget;
	}

	public void setReportTarget(String reportTarget) {
		this.reportTarget = reportTarget;
	}

	public String[] getCities() {
		return cities;
	}

	public void setCities(String[] cities) {
		this.cities = cities;
	}

	public List<CityDTO> getCityDtos() {
		return cityDtos;
	}

	public void setCityDtos(List<CityDTO> cityDtos) {
		this.cityDtos = cityDtos;
	}

	public SimpleDateDTO getExpiryDateDto() {
		return expiryDateDto;
	}

	public void setExpiryDateDto(SimpleDateDTO expiryDateDto) {
		this.expiryDateDto = expiryDateDto;
	}

	public String getSalaryFrom() {
		return salaryFrom;
	}

	public void setSalaryFrom(String salaryFrom) {
		this.salaryFrom = salaryFrom;
	}

	public String getSalaryTo() {
		return salaryTo;
	}

	public void setSalaryTo(String salaryTo) {
		this.salaryTo = salaryTo;
	}

	public String[] getSalaryStructure() {
		return salaryStructure;
	}

	public void setSalaryStructure(String[] salaryStructure) {
		this.salaryStructure = salaryStructure;
	}

	public String[] getSocityWelfare() {
		return socityWelfare;
	}

	public void setSocityWelfare(String[] socityWelfare) {
		this.socityWelfare = socityWelfare;
	}

	public String[] getResidentialWelfare() {
		return residentialWelfare;
	}

	public void setResidentialWelfare(String[] residentialWelfare) {
		this.residentialWelfare = residentialWelfare;
	}

	public String[] getAnnualLeaveWelfare() {
		return annualLeaveWelfare;
	}

	public void setAnnualLeaveWelfare(String[] annualLeaveWelfare) {
		this.annualLeaveWelfare = annualLeaveWelfare;
	}

	public String getDutyDescription() {
		return dutyDescription;
	}

	public void setDutyDescription(String dutyDescription) {
		this.dutyDescription = dutyDescription;
	}

}

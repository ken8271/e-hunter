package com.pccw.ehunter.dto;

import java.util.ArrayList;
import java.util.List;

public class PositionDescriptionDTO extends BaseDTO {
	private static final long serialVersionUID = -6282581877255631471L;

	private String systemPositionRefNum;
	private String positionCategory;
	private PositionCategoryDTO positionCategoryDto;
	private String position;
	private PositionDTO positionDto;
	private String positionName;
	private String expectNumberStr;
	private String department;
	private String reportTarget;
	private String cities;
	private List<CityDTO> cityDtos;
	private SimpleDateDTO expiryDateDto;
	private String salaryFromStr;
	private String salaryToStr;
	private String[] salaryCategory;
	private String salaryCategoryStr;
	private List<SalaryCategoryDTO> salaryCategoryDtos = new ArrayList<SalaryCategoryDTO>();
	private String[] societyWelfare;
	private String societyWelfareStr;
	private List<SocietyWelfareDTO> scoitetyWelfareDtos = new ArrayList<SocietyWelfareDTO>();
	private String[] residentialWelfare;
	private String residentialWelfareStr;
	private List<ResidentialWelfareDTO> residentialWelfareDtos = new ArrayList<ResidentialWelfareDTO>();
	private String[] annualLeaveWelfare;
	private String annualLeaveWelfareStr;
	private List<AnnualLeaveWelfareDTO> annualLeaveWelfareDtos = new ArrayList<AnnualLeaveWelfareDTO>();
	private String dutyDescription;

	public String getSystemPositionRefNum() {
		return systemPositionRefNum;
	}

	public void setSystemPositionRefNum(String systemPositionRefNum) {
		this.systemPositionRefNum = systemPositionRefNum;
	}

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

	public String getExpectNumberStr() {
		return expectNumberStr;
	}

	public void setExpectNumberStr(String expectNumberStr) {
		this.expectNumberStr = expectNumberStr;
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

	public String getCities() {
		return cities;
	}

	public void setCities(String cities) {
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

	public String getSalaryFromStr() {
		return salaryFromStr;
	}

	public void setSalaryFromStr(String salaryFromStr) {
		this.salaryFromStr = salaryFromStr;
	}

	public String getSalaryToStr() {
		return salaryToStr;
	}

	public void setSalaryToStr(String salaryToStr) {
		this.salaryToStr = salaryToStr;
	}

	public String[] getSalaryCategory() {
		return salaryCategory;
	}

	public void setSalaryCategory(String[] salaryCategory) {
		this.salaryCategory = salaryCategory;
	}

	public String[] getSocietyWelfare() {
		return societyWelfare;
	}

	public void setSocietyWelfare(String[] societyWelfare) {
		this.societyWelfare = societyWelfare;
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

	public List<SalaryCategoryDTO> getSalaryCategoryDtos() {
		return salaryCategoryDtos;
	}

	public void setSalaryCategoryDtos(List<SalaryCategoryDTO> salaryCategoryDtos) {
		this.salaryCategoryDtos = salaryCategoryDtos;
	}

	public List<SocietyWelfareDTO> getScoitetyWelfareDtos() {
		return scoitetyWelfareDtos;
	}

	public void setScoitetyWelfareDtos(
			List<SocietyWelfareDTO> scoitetyWelfareDtos) {
		this.scoitetyWelfareDtos = scoitetyWelfareDtos;
	}

	public List<ResidentialWelfareDTO> getResidentialWelfareDtos() {
		return residentialWelfareDtos;
	}

	public void setResidentialWelfareDtos(
			List<ResidentialWelfareDTO> residentialWelfareDtos) {
		this.residentialWelfareDtos = residentialWelfareDtos;
	}

	public List<AnnualLeaveWelfareDTO> getAnnualLeaveWelfareDtos() {
		return annualLeaveWelfareDtos;
	}

	public void setAnnualLeaveWelfareDtos(
			List<AnnualLeaveWelfareDTO> annualLeaveWelfareDtos) {
		this.annualLeaveWelfareDtos = annualLeaveWelfareDtos;
	}

	public String getSalaryCategoryStr() {
		return salaryCategoryStr;
	}

	public void setSalaryCategoryStr(String salaryCategoryStr) {
		this.salaryCategoryStr = salaryCategoryStr;
	}

	public String getSocietyWelfareStr() {
		return societyWelfareStr;
	}

	public void setSocietyWelfareStr(String societyWelfareStr) {
		this.societyWelfareStr = societyWelfareStr;
	}

	public String getResidentialWelfareStr() {
		return residentialWelfareStr;
	}

	public void setResidentialWelfareStr(String residentialWelfareStr) {
		this.residentialWelfareStr = residentialWelfareStr;
	}

	public String getAnnualLeaveWelfareStr() {
		return annualLeaveWelfareStr;
	}

	public void setAnnualLeaveWelfareStr(String annualLeaveWelfareStr) {
		this.annualLeaveWelfareStr = annualLeaveWelfareStr;
	}
}

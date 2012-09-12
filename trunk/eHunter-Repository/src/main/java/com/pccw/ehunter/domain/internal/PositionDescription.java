package com.pccw.ehunter.domain.internal;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name="T_PRJ_POST_DESC")
public class PositionDescription extends BaseEntity {
	private static final long serialVersionUID = 8698828216911059838L;

	private String systemPostionRefNum;
	private Project project;
	private String position;
	private String positionName;
	private String department;
	private String reportTarget;
	private String cities;
	private Date expiryDate;
	private Integer salaryFrom;
	private Integer salaryTo;
	private String salaryCategoryStr;
	private String societyWelfareStr;
	private String residentialWelfareStr;
	private String annualLeaveWelfareStr;
	private List<PositionKeyWord> keyWords;
	private String dutyDescription;

	@Id
	@Column(name="SYS_REF_POST")
	public String getSystemPostionRefNum() {
		return systemPostionRefNum;
	}

	public void setSystemPostionRefNum(String systemPostionRefNum) {
		this.systemPostionRefNum = systemPostionRefNum;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn(name = "SYS_REF_POST", referencedColumnName = "SYS_REF_PRJ")
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name="POST_TY")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name="POST_NM")
	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	@Column(name="POST_DEPT")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name="PEPT_TO")
	public String getReportTarget() {
		return reportTarget;
	}

	public void setReportTarget(String reportTarget) {
		this.reportTarget = reportTarget;
	}

	@Column(name="WK_CTS")
	public String getCities() {
		return cities;
	}

	public void setCities(String cities) {
		this.cities = cities;
	}

	@Column(name="EXPR_DT")
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Column(name="YRLY_SALRY_FR")
	public Integer getSalaryFrom() {
		return salaryFrom;
	}

	public void setSalaryFrom(Integer salaryFrom) {
		this.salaryFrom = salaryFrom;
	}

	@Column(name="YRLY_SALRY_TO")
	public Integer getSalaryTo() {
		return salaryTo;
	}

	public void setSalaryTo(Integer salaryTo) {
		this.salaryTo = salaryTo;
	}

	@Column(name="SALRY_CMPS")
	public String getSalaryCategoryStr() {
		return salaryCategoryStr;
	}

	public void setSalaryCategoryStr(String salaryCategoryStr) {
		this.salaryCategoryStr = salaryCategoryStr;
	}

	@Column(name="SCTY_WELF")
	public String getSocietyWelfareStr() {
		return societyWelfareStr;
	}

	public void setSocietyWelfareStr(String societyWelfareStr) {
		this.societyWelfareStr = societyWelfareStr;
	}

	@Column(name="RSDTL_WELF")
	public String getResidentialWelfareStr() {
		return residentialWelfareStr;
	}

	public void setResidentialWelfareStr(String residentialWelfareStr) {
		this.residentialWelfareStr = residentialWelfareStr;
	}

	@Column(name="ANUL_LVE_WELF")
	public String getAnnualLeaveWelfareStr() {
		return annualLeaveWelfareStr;
	}

	public void setAnnualLeaveWelfareStr(String annualLeaveWelfareStr) {
		this.annualLeaveWelfareStr = annualLeaveWelfareStr;
	}

	@Column(name="DUTY_DESC")
	public String getDutyDescription() {
		return dutyDescription;
	}

	public void setDutyDescription(String dutyDescription) {
		this.dutyDescription = dutyDescription;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.postDesc", cascade = { CascadeType.ALL })
	public List<PositionKeyWord> getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(List<PositionKeyWord> keyWords) {
		this.keyWords = keyWords;
	}

}

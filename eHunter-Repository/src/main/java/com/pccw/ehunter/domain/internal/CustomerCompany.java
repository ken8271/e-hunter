package com.pccw.ehunter.domain.internal;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name = "T_CUST_CO")
public class CustomerCompany extends BaseEntity {

	private String systemCustRefNum;
	private String shortName;
	private String fullName;
	private String grade;
	private String status;
	private String offcialSite;
	private String telExchange;
	private String type;
	private String size;
	private String groupIndicator;

	private CustomerGroup group;
	private List<CustomerResponsablePerson> custRespPersons;
	private List<Project> projects;

	@Id
	@Column(name = "SYS_REF_CUST")
	public String getSystemCustRefNum() {
		return systemCustRefNum;
	}

	public void setSystemCustRefNum(String systemCustRefNum) {
		this.systemCustRefNum = systemCustRefNum;
	}

	@Column(name = "CO_SHRT_NM")
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "CO_NM")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "CUST_GRDE")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "CUST_STAT")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "OFCL_SITE")
	public String getOffcialSite() {
		return offcialSite;
	}

	public void setOffcialSite(String offcialSite) {
		this.offcialSite = offcialSite;
	}

	@Column(name = "CO_TEL_EXCHG")
	public String getTelExchange() {
		return telExchange;
	}
	
	public void setTelExchange(String telExchange) {
		this.telExchange = telExchange;
	}


	@Column(name = "CO_TY")
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "CO_SZ")
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Column(name = "GP_FLAG")
	public String getGroupIndicator() {
		return groupIndicator;
	}

	public void setGroupIndicator(String groupIndicator) {
		this.groupIndicator = groupIndicator;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "SYS_REF_GP")
	public CustomerGroup getGroup() {
		return group;
	}

	public void setGroup(CustomerGroup group) {
		this.group = group;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="SYS_REF_CUST")
	public List<CustomerResponsablePerson> getCustRespPersons() {
		return custRespPersons;
	}

	public void setCustRespPersons(
			List<CustomerResponsablePerson> custRespPersons) {
		this.custRespPersons = custRespPersons;
	}

	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL ,mappedBy="customer")
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

}

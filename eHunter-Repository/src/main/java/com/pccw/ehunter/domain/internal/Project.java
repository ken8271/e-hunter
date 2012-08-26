package com.pccw.ehunter.domain.internal;

import java.util.ArrayList;
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
@Table(name="T_PRJ")
public class Project extends BaseEntity {
	private static final long serialVersionUID = -6586349396029661685L;
	
	private String systemProjectRefNum;
	private String projectName;
	private CustomerCompany customer;

	private List<ProjectPosition> positions = new ArrayList<ProjectPosition>();

	@Id
	@Column(name="SYS_REF_PRJ")
	public String getSystemProjectRefNum() {
		return systemProjectRefNum;
	}

	public void setSystemProjectRefNum(String systemProjectRefNum) {
		this.systemProjectRefNum = systemProjectRefNum;
	}

	@Column(name="PRJ_NM")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@OneToMany(fetch=FetchType.LAZY , mappedBy="project")
	public List<ProjectPosition> getPositions() {
		return positions;
	}

	public void setPositions(List<ProjectPosition> positions) {
		this.positions = positions;
	}

	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="SYS_REF_CUST")
	public CustomerCompany getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerCompany customer) {
		this.customer = customer;
	}

}

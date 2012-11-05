package com.pccw.ehunter.domain.internal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name="T_INT_USR")
public class InternalUser extends BaseEntity {
	private static final long serialVersionUID = 4317238041740048471L;

	private String userRecId;

	private String loginId;

	private String password;

	private String staffId;

	private String accountStatus;

	private String enName;

	private String cnName;

	private String passwordExpired;

	private String phone;

	private String mobile;

	private String email;
	
	private List<InternalRole> activeRoles = new ArrayList<InternalRole>(); 

	@Id
	@Column(name="USR_REC_ID")
	public String getUserRecId() {
		return userRecId;
	}

	public void setUserRecId(String userRecId) {
		this.userRecId = userRecId;
	}

	@Column(name="LOGIN_ID")
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Column(name="PWD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="STAF_NBR")
	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Column(name="ACCT_ST")
	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Column(name="ENM")
	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	@Column(name="CNM")
	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	@Column(name="CO_TEL_NBR")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name="PR_TEL_NBR")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="PWD_EXPR_FLG")
	public String getPasswordExpired() {
		return passwordExpired;
	}

	public void setPasswordExpired(String passwordExpired) {
		this.passwordExpired = passwordExpired;
	}

	@ManyToMany(fetch=FetchType.EAGER,cascade={CascadeType.REFRESH})
	@JoinTable(name="T_INT_USR_ROLE_ASGN",
			   joinColumns={@JoinColumn(name="USR_REC_ID")},
			   inverseJoinColumns={@JoinColumn(name="SYS_REF_ROLE")})
	public List<InternalRole> getActiveRoles() {
		return activeRoles;
	}

	public void setActiveRoles(List<InternalRole> activeRoles) {
		this.activeRoles = activeRoles;
	}
}

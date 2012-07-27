package com.pccw.ehunter.domain.internal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pccw.ehunter.domain.BaseEntity;

@Entity
@Table(name = "T_INT_ROLE")
public class InternalRole extends BaseEntity {
	private String sysRefRole;
	private String roleId;
	private String roleName;
	private String roleDesc;
	private String roleStatus;

	@Id
	@Column(name="SYS_REF_ROLE")
	public String getSysRefRole() {
		return sysRefRole;
	}

	public void setSysRefRole(String sysRefRole) {
		this.sysRefRole = sysRefRole;
	}

	@Column(name="ROLE_ID")
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	@Column(name="ROLE_NM")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name="ROLE_DESC")
	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@Column(name="ROLE_STAT")
	public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}

}

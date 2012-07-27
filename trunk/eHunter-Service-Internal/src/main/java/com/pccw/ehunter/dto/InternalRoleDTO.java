package com.pccw.ehunter.dto;

public class InternalRoleDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private String sysRefRole;
	private String roleId;
	private String roleName;
	private String roleDesc;
	private String roleStatus;

	public String getSysRefRole() {
		return sysRefRole;
	}

	public void setSysRefRole(String sysRefRole) {
		this.sysRefRole = sysRefRole;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}

}

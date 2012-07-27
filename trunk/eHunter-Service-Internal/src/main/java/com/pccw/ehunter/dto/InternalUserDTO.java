package com.pccw.ehunter.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.constant.CommonConstant;

public class InternalUserDTO extends BaseDTO implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String userRecId;
	private String loginId;
	private String password;
	private String confirmPassword;
	private String staffId;
	private String accountStatus;
	private String enName;
	private String cnName;
	private String passwordExpired;
	private String phone;
	private String mobile;
	private String email;
	private List<InternalRoleDTO> activeRoles;

	public String getUserRecId() {
		return userRecId;
	}

	public void setUserRecId(String userRecId) {
		this.userRecId = userRecId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getPasswordExpired() {
		return passwordExpired;
	}

	public void setPasswordExpired(String passwordExpired) {
		this.passwordExpired = passwordExpired;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<InternalRoleDTO> getActiveRoles() {
		return activeRoles;
	}

	public void setActiveRoles(List<InternalRoleDTO> activeRoles) {
		this.activeRoles = activeRoles;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		if(!CollectionUtils.isEmpty(this.activeRoles)){
			for(InternalRoleDTO dto : activeRoles){
				authorities.add(new SimpleGrantedAuthority(dto.getRoleId()));
			}
		}
		return authorities;
	}

	@Override
	public String getUsername() {
		return this.loginId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		if (CommonConstant.NO.equals(this.passwordExpired)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isEnabled() {
		if (CommonConstant.USER_ACCOUNT_ACTIVE.equals(this.accountStatus)) {
			return true;
		}
		return false;
	}

}

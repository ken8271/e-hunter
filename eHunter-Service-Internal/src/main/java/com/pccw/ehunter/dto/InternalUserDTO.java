package com.pccw.ehunter.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.security.AbstractUserDetails;

public class InternalUserDTO extends AbstractUserDetails {
	private static final long serialVersionUID = 1L;
	
	private String confirmPassword;

	private List<InternalRoleDTO> activeRoles;

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

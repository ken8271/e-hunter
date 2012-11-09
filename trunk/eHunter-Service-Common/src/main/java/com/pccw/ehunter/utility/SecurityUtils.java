package com.pccw.ehunter.utility;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.security.AbstractUserDetails;

public class SecurityUtils {
	
	public static UserDetails getUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null){
			if(authentication.getPrincipal() != null && authentication.getPrincipal() instanceof UserDetails){
				return (UserDetails)authentication.getPrincipal();
			}else{
				return null;
			}
		}
		
		return null;
	}
	
	public static String getUserRecId(){
		UserDetails usr = getUser();
		if(usr != null && usr instanceof AbstractUserDetails){
			return ((AbstractUserDetails)usr).getUserRecId();
		}else {
			return CommonConstant.ANOYMOUS_INTERNAL_USER;
		}
	}
	
	public static String getUserName(){
		UserDetails usr = getUser();
		if(usr != null && usr instanceof AbstractUserDetails){
			return ((AbstractUserDetails)usr).getUsername();
		}else {
			return CommonConstant.ANOYMOUS_INTERNAL_USER;
		}
	}
}

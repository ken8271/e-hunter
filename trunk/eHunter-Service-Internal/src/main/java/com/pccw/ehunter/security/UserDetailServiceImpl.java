package com.pccw.ehunter.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pccw.ehunter.convertor.InternalUserConvertor;
import com.pccw.ehunter.dao.InternalUserDAO;
import com.pccw.ehunter.domain.internal.InternalUser;

@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private InternalUserDAO internalUserDao;

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException {
		InternalUser usr = internalUserDao.getInternalUserByLoginId(name);
		
		if(null == usr ){
			throw new UsernameNotFoundException("The user with login id " + name + "not found !");
		}
		
		return InternalUserConvertor.toDto(usr);
	}

}

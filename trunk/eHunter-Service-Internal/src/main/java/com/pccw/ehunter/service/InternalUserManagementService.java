package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.InternalRoleDTO;
import com.pccw.ehunter.dto.InternalUserDTO;
import com.pccw.ehunter.dto.InternalUserPagedCriteria;

public interface InternalUserManagementService {
	public List<InternalRoleDTO> getActiveDirectoryRoles();
	public List<InternalUserDTO> getInternalUsersByCriteria(InternalUserPagedCriteria pagedCriteria);
	public int getInternalUsersCountByCriteria(InternalUserPagedCriteria pagedCriteria);
	public InternalUserDTO getInternalUserByID(String id);
	public InternalUserDTO getInternalUserByLoginID(String loginID);
	public void saveInternalUser(InternalUserDTO internalUserDto);
	public void updateInternalUser(InternalUserDTO internalUserDto);
	public void resetPassword(InternalUserDTO internalUserDto);
	public void deleteInternalUser(InternalUserDTO internalUserDto);
}

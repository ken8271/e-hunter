package com.pccw.ehunter.dao;

import java.util.List;

import com.pccw.ehunter.domain.internal.InternalUser;
import com.pccw.ehunter.dto.InternalUserPagedCriteria;

public interface InternalUserDAO {
	public InternalUser getInternalUserByLoginId(String loginId);
	public List<Object> getInternalUsersByCriteria(InternalUserPagedCriteria pagedCriteria);
	public int getInternalUsersCountByCriteria(InternalUserPagedCriteria pagedCriteria);
	public void saveInternalUser(InternalUser internalUser);
	public void updateInternalUser(InternalUser internalUser);
	public void resetPassword(InternalUser internalUser);
	public void changePassword(String userID , String newPassword);
	public void deleteInternalUser(InternalUser internalUser);
}

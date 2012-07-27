package com.pccw.ehunter.dao;

import com.pccw.ehunter.domain.internal.InternalUser;

public interface InternalUserDAO {
	InternalUser getInternalUserByLoginId(String loginId);
}

package com.pccw.ehunter.dao;

import java.util.List;

import com.pccw.ehunter.domain.internal.CustomerGroup;

public interface CustomerRegistrationDAO {
	public List<CustomerGroup> loadCustGroups();
	public CustomerGroup loadCustGroupByID(String systemGroupRefNum);
	public int countGroupsByFullName(String fullName);
}

package com.pccw.ehunter.dao;

import java.util.List;

import com.pccw.ehunter.domain.internal.CustomerCompany;
import com.pccw.ehunter.domain.internal.CustomerGroup;
import com.pccw.ehunter.domain.internal.CustomerResponsablePerson;

public interface CustomerRegistrationDAO {
	public List<CustomerGroup> loadCustGroups();
	public CustomerGroup loadCustGroupByID(String systemGroupRefNum);
	public int countGroupsByFullName(String fullName);
	
	public void saveCustomerCompany(CustomerCompany customerCompany);
	public void saveCustomerGroup(CustomerGroup customerGroup);
	public void saveCustomerResponsablePerson(CustomerResponsablePerson customerResponsablePerson);
	
	public void updateCustomerByProperty(String property , String value , String id);
}

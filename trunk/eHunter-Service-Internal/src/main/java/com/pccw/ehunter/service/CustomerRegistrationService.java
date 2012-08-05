package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.dto.CustomerGroupDTO;

public interface CustomerRegistrationService {
	public CustomerGroupDTO loadCustGroupByID(String systemGroupRefNum);
	public List<CustomerGroupDTO> loadCustGroups();
	public int getCountOfGroupsByFullName(String fullName);
	public void completeCustRegistration(CustomerDTO customerDto);
}

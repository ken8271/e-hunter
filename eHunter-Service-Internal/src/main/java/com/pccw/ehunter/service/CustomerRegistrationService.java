package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.CustomerGroupDTO;

public interface CustomerRegistrationService {
	public CustomerGroupDTO loadCustGroupByID(String systemGroupRefNum);
	public List<CustomerGroupDTO> loadCustGroups();
}

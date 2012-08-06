package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.dto.CustomerPagedCriteria;

public interface CustomerCommonService {
	public int getCustomersCountByCriteria(CustomerPagedCriteria pagedCriteria);
	public List<CustomerDTO> getCustomersByCriteria(CustomerPagedCriteria pagedCriteria);
	public CustomerDTO getCustomerByID(String id);
}

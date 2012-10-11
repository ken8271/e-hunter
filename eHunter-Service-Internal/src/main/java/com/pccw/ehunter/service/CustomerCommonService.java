package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.dto.CustomerGroupDTO;
import com.pccw.ehunter.dto.CustomerPagedCriteria;
import com.pccw.ehunter.dto.CustomerResponsePersonDTO;

public interface CustomerCommonService {
	public int getCustomersCountByCriteria(CustomerPagedCriteria pagedCriteria);
	public List<CustomerDTO> getCustomersByCriteria(CustomerPagedCriteria pagedCriteria);
	public CustomerDTO getCustomerByID(String customerId);
	public List<CustomerResponsePersonDTO> getResponsePersonsByCustomerID(String customerID);
	public List<CustomerDTO> getCustomersByCompanyName(String name , String indicator);
	public List<CustomerGroupDTO> getCustomerGroupByName(String name , String indicator);
}

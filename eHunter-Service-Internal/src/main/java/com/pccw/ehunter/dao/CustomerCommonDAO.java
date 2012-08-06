package com.pccw.ehunter.dao;

import java.util.List;

import com.pccw.ehunter.domain.internal.CustomerCompany;
import com.pccw.ehunter.dto.CustomerPagedCriteria;

public interface CustomerCommonDAO {
	public int getCustomersCountByCriteria(CustomerPagedCriteria pagedCriteria);
	public List<Object> getCustomersByCriteria(CustomerPagedCriteria pagedCriteria);  
	public CustomerCompany getCustomerByID(String systemCustRefNum);
}

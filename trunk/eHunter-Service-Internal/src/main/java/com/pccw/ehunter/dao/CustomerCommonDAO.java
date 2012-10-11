package com.pccw.ehunter.dao;

import java.util.List;

import com.pccw.ehunter.dto.CustomerPagedCriteria;

public interface CustomerCommonDAO {
	public int getCustomersCountByCriteria(CustomerPagedCriteria pagedCriteria);
	public List<Object> getCustomersByCriteria(CustomerPagedCriteria pagedCriteria);  
	public Object getCustomerByID(String systemCustRefNum);
	public Object getCustomerGroupByID(String groupId);
	public List<Object> getCustomerRespPersonsByCustID(String custId);
	public List<String> getCustomersByCompanyName(String name , String indicator);
	public List<String> getCustomerGroupByName(String name , String indicator);
}

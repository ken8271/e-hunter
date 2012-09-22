package com.pccw.ehunter.dao;

import java.util.List;

import com.pccw.ehunter.domain.internal.CustomerContactHistory;

public interface CustomerContactHistoryDAO {
	public void saveContactHistory(CustomerContactHistory po);
	public List<Object> getContactHistories(String customerID);
	public Object getContactHistoryByID(String id);
}

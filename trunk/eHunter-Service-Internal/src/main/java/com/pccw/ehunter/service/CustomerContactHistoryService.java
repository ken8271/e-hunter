package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.CustomerContactHistoryDTO;

public interface CustomerContactHistoryService {
	public void saveContactHistory(CustomerContactHistoryDTO dto);
	public List<CustomerContactHistoryDTO> getContactHistories(String customerID);
	public CustomerContactHistoryDTO getContactHistoryByID(String id);
}

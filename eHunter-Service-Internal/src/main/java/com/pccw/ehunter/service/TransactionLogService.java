package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.TransactionLogDTO;
import com.pccw.ehunter.dto.TransactionLogPagedCriteria;

public interface TransactionLogService {
	public void logTransaction(String function , String msg);
	public void logTransaction(String function , String msg , String user);
	public int getTransactionlogCountByCriteria(TransactionLogPagedCriteria pagedCriteria);
	public List<TransactionLogDTO> getTransactionlogByCriteria(TransactionLogPagedCriteria pagedCriteria);
}

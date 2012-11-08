package com.pccw.ehunter.dao;

import java.util.List;

import com.pccw.ehunter.dto.TransactionLogPagedCriteria;

public interface TransactionLogDAO {
	public int getTransactionlogCountByCriteria(TransactionLogPagedCriteria pagedCriteria);
	public List<Object> getTransactionlogByCriteria(TransactionLogPagedCriteria pagedCriteria);
}

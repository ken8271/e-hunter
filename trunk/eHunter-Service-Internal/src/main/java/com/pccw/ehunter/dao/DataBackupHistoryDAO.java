package com.pccw.ehunter.dao;

import java.util.List;

import com.pccw.ehunter.dto.PagedCriteria;

public interface DataBackupHistoryDAO {
	public int getBackupHistoryCountByCriteria(PagedCriteria pagedCriteria);
	public List<Object> getBackupHistoriesByCriteria(PagedCriteria pagedCriteria);
}

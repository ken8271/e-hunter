package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.DataBackupHistoryDTO;
import com.pccw.ehunter.dto.PagedCriteria;

public interface DataBackupHistoryService {
	public int getBackupHistoryCountByCriteria(PagedCriteria pagedCriteria);
	public List<DataBackupHistoryDTO> getBackupHistoriesByCriteria(PagedCriteria pagedCriteria);
	public void saveBackupHistory(DataBackupHistoryDTO dto);
	public DataBackupHistoryDTO getBackupHistoryByID(String id);
}

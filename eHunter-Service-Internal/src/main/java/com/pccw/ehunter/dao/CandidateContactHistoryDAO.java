package com.pccw.ehunter.dao;

import java.util.List;

import com.pccw.ehunter.domain.internal.CandidateContactHistory;

public interface CandidateContactHistoryDAO {
	public List<Object> getContactHistories(String talentID , String projectID);
	public Object getContactHistoryByID(String id);
	public void saveContactHistory(CandidateContactHistory po);
}

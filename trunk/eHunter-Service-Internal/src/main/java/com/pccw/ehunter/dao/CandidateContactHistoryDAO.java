package com.pccw.ehunter.dao;

import java.util.List;

public interface CandidateContactHistoryDAO {
	public List<Object> getContactHistories(String talentID , String projectID);
}

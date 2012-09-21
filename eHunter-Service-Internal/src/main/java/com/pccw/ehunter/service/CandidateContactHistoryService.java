package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.CandidateContactHistoryDTO;

public interface CandidateContactHistoryService {
	public CandidateContactHistoryDTO getContactHistoryByID(String id);
	public List<CandidateContactHistoryDTO> getContactHistories(String talentID , String projectID);
	public void saveContactHistory(CandidateContactHistoryDTO dto);
}

package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.TalentSourceDTO;

public interface TalentSourceService {
	public List<TalentSourceDTO> getAllTalentSources();
	public void saveTalentSource(TalentSourceDTO dto);
	public void deleteTalentSource(String id);
	public boolean isTalentSourceExists(String officialSite);
}

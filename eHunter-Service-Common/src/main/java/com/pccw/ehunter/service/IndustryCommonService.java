package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.IndustryCategoryDTO;
import com.pccw.ehunter.dto.IndustryDTO;

public interface IndustryCommonService {
	public List<IndustryCategoryDTO> getIndustryCategories();
	public List<IndustryDTO> getIndustriesByCategory(String categoryCode);
	public IndustryDTO getIndustryByCode(String code);
}

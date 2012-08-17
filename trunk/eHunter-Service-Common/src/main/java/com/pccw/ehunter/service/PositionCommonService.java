package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.PositionCategoryDTO;
import com.pccw.ehunter.dto.PositionDTO;

public interface PositionCommonService {
	public List<PositionCategoryDTO> getPositionCategories();
	public List<PositionDTO> getPositionsByCategory(String categoryCode);
	public PositionDTO getPositionByCode(String code);
}

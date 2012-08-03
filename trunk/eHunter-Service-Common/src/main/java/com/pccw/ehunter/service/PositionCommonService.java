package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.BaseLabelValueDTO;

public interface PositionCommonService {
	public List<BaseLabelValueDTO> loadPositionTypes();
	public List<BaseLabelValueDTO> loadPositionTypeByParentCode(String code);
}

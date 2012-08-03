package com.pccw.ehunter.dao;

import java.util.List;

import com.pccw.ehunter.domain.common.PositionSubType;
import com.pccw.ehunter.domain.common.PositionType;

public interface PositionCommonDAO {
	public List<PositionType> loadPositionTypes();
	public List<PositionSubType> loadSubTypesByParentCode(String code);
}

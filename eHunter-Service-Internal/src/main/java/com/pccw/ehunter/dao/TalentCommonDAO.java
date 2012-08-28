package com.pccw.ehunter.dao;

import java.util.List;

import com.pccw.ehunter.dto.TalentPagedCriteria;

public interface TalentCommonDAO {
	public int getTalentsCountByCriteria(TalentPagedCriteria pagedCriteria);
	public List<Object> getTalentsByCriteria(TalentPagedCriteria pagedCriteria);
}

package com.pccw.ehunter.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.dto.TalentPagedCriteria;

public interface TalentCommonService {
	public int getTalentsCountByCriteria(TalentPagedCriteria pagedCriteria);
	public List<TalentDTO> getTalentsByCriteria(HttpServletRequest request ,TalentPagedCriteria pagedCriteria);
}

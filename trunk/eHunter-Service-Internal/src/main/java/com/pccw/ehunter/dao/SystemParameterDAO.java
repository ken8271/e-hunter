package com.pccw.ehunter.dao;

import java.util.List;

import com.pccw.ehunter.dto.PagedCriteria;

public interface SystemParameterDAO {
	public int getParametersCountByCriteria(PagedCriteria pagedCriteria);
	public List<Object> getParametersByCriteria(PagedCriteria pagedCriteria);
}

package com.pccw.ehunter.dao;

import java.util.List;

import com.pccw.ehunter.dto.PagedCriteria;

public interface CodeTableCommonDAO {
	public List<Object> getCodes(String tableKey , String codeKey);
	public List<Object> getCodetablesByCriteria(PagedCriteria  pagedCriteria);
	public int getCodetablesCountByCriteria(PagedCriteria pagedCriteria);
}

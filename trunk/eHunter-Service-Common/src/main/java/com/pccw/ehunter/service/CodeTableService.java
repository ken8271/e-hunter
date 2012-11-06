package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.CodeTableDTO;
import com.pccw.ehunter.dto.PagedCriteria;

public interface CodeTableService {
	public List<CodeTableDTO> getCodetablesByCriteria(PagedCriteria pagedCriteria);
	public int getCodetablesCountByCriteria(PagedCriteria pagedCriteria);
	public CodeTableDTO getCodetableByID(String id);
	public void updateCodetable(CodeTableDTO dto);
}

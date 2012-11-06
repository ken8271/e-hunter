package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.PagedCriteria;
import com.pccw.ehunter.dto.SystemParameterDTO;

public interface SystemParameterService {
	public int getParametersCountByCriteria(PagedCriteria pagedCriteria);
	public List<SystemParameterDTO> getParametersByCriteria(PagedCriteria pagedCriteria);
	public SystemParameterDTO getSystemParameterByKey(String key);
	public void updateSystemParameter(SystemParameterDTO dto);
	public void deleteSystemParameter(SystemParameterDTO dto);
	public void saveSystemParameter(SystemParameterDTO dto);
}

package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.PagedCriteria;
import com.pccw.ehunter.dto.portal.PortalInformationCenterDTO;

public interface PortalInformationCenterService {
	public void saveInformation(PortalInformationCenterDTO dto);
	public void updateInformation(PortalInformationCenterDTO dto);
	public int  getPortalInformationCountByCriteria(PagedCriteria pagedCriteria);
	public List<PortalInformationCenterDTO> getPortalInformationByCriteria(PagedCriteria pagedCriteria);
	public PortalInformationCenterDTO getInformationByID(String id);
}

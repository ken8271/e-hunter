package com.pccw.ehunter.service;

import java.util.List;

import com.pccw.ehunter.dto.PagedCriteria;
import com.pccw.ehunter.dto.portal.PortalReleasedPositionDTO;

public interface PortalPositionReleaseService {
	public void saveReleasedPosition(PortalReleasedPositionDTO dto);
	public void updatePosition(PortalReleasedPositionDTO dto);
	public int getReleasedPositionsCountByPagedCriteria(PagedCriteria pagedCriteria);
	public List<PortalReleasedPositionDTO> getReleasedPositionsByCriteria(PagedCriteria pagedCriteria);
	public PortalReleasedPositionDTO getPositionInfoByID(String id);
}

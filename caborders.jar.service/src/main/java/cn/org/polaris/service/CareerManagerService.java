package cn.org.polaris.service;

import java.util.List;

import cn.org.polaris.dto.PagedCriteria;
import cn.org.polaris.dto.biz.ReleasedPositionDTO;

public interface CareerManagerService {
	public int getPositionsCountByCriteria(PagedCriteria pagedCriteria);
	public List<ReleasedPositionDTO> getPositionsByCriteria(PagedCriteria pagedCriteria);
	public ReleasedPositionDTO getPositionByID(String id);
}

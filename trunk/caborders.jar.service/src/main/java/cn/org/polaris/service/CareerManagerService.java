package cn.org.polaris.service;

import java.util.List;

import cn.org.polaris.dto.biz.PositionPagedCriteria;
import cn.org.polaris.dto.biz.ReleasedPositionDTO;

public interface CareerManagerService {
	public int getPositionsCountByCriteria(PositionPagedCriteria pagedCriteria);
	public List<ReleasedPositionDTO> getPositionsByCriteria(PositionPagedCriteria pagedCriteria);
	public ReleasedPositionDTO getPositionByID(String id);
	
	public void releasePosition(ReleasedPositionDTO dto);
	public void deletePositionByIDs(String[] ids);
}

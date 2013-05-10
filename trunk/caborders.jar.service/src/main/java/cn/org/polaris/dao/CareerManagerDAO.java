package cn.org.polaris.dao;

import java.util.List;

import cn.org.polaris.dto.biz.PositionPagedCriteria;
import cn.org.polaris.repo.ReleasedPosition;

public interface CareerManagerDAO {
	public int getPositionsCountByCriteria(PositionPagedCriteria pagedCriteria);
	public List<ReleasedPosition> getPositionsByCriteria(PositionPagedCriteria pagedCriteria);
	public ReleasedPosition getPositionByID(String id);
	
	public void releasePosition(ReleasedPosition rp);
	public void updatePosition(ReleasedPosition rp);
	public void deletePosition(ReleasedPosition rp);
}

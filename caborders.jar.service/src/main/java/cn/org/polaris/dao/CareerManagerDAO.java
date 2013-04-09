package cn.org.polaris.dao;

import java.util.List;

import cn.org.polaris.dto.PagedCriteria;
import cn.org.polaris.repo.ReleasedPosition;

public interface CareerManagerDAO {
	public int getPositionsCountByCriteria(PagedCriteria pagedCriteria);
	public List<ReleasedPosition> getPositionsByCriteria(PagedCriteria pagedCriteria);
	public ReleasedPosition getPositionByID(String id);
}

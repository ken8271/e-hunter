package cn.org.polaris.dao;

import java.util.List;

import cn.org.polaris.dto.biz.InformationPagedCriteria;
import cn.org.polaris.repo.Information;

public interface InfoCenterDAO {
	public int getInfosCountByCategory(InformationPagedCriteria pagedCriteria);
	public List<Information> getInfosByCategory(InformationPagedCriteria pagedCriteria);
	public Information getInformationByID(String id);
	
	public void releaseInformation(Information info);
}

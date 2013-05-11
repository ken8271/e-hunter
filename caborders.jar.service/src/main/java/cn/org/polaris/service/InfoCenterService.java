package cn.org.polaris.service;

import java.util.List;

import cn.org.polaris.dto.biz.InformationDTO;
import cn.org.polaris.dto.biz.InformationPagedCriteria;

public interface InfoCenterService {
	public int getInfosCountByCriteria(InformationPagedCriteria pagedCriteria);
	public List<InformationDTO> getInfosByCriteria(InformationPagedCriteria pagedCriteria);
	public InformationDTO getInformationByID(String id);
	
	public void releaseInformation(InformationDTO info);
	public void updateInformation(InformationDTO info);
	public void deleteInformationsByIds(String[] ids);
}

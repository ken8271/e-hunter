package cn.org.polaris.service;

import java.util.List;

import cn.org.polaris.dto.biz.InformationDTO;
import cn.org.polaris.dto.biz.InformationPagedCriteria;

public interface InfoCenterService {
	public int getInfosCountByCriteria(InformationPagedCriteria pagedCriteria);
	public List<InformationDTO> getInfosByCriteria(InformationPagedCriteria pagedCriteria);
}

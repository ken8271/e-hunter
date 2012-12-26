package cn.org.polaris.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.org.polaris.convertor.InformationConvertor;
import cn.org.polaris.dao.InfoCenterDAO;
import cn.org.polaris.dto.biz.InformationDTO;
import cn.org.polaris.dto.biz.InformationPagedCriteria;
import cn.org.polaris.service.InfoCenterService;

@Service("infoCenterService")
@Transactional
public class InfoCenterServiceImpl implements InfoCenterService{
	
	@Autowired
	private InfoCenterDAO infoCenterDao;

	@Override
	@Transactional(readOnly=true)
	public int getInfosCountByCriteria(InformationPagedCriteria pagedCriteria) {
		return infoCenterDao.getInfosCountByCategory(pagedCriteria);
	}

	@Override
	@Transactional(readOnly=true)
	public List<InformationDTO> getInfosByCriteria(InformationPagedCriteria pagedCriteria) {
		return InformationConvertor.toDtos(infoCenterDao.getInfosByCategory(pagedCriteria));
	}

}

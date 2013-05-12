package cn.org.polaris.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.org.polaris.constant.TransactionIndicator;
import cn.org.polaris.convertor.InformationConvertor;
import cn.org.polaris.dao.InfoCenterDAO;
import cn.org.polaris.dto.biz.InformationDTO;
import cn.org.polaris.dto.biz.InformationPagedCriteria;
import cn.org.polaris.service.InfoCenterService;
import cn.org.polaris.utility.BaseDtoUtility;
import cn.org.polaris.utility.IDGenerator;
import cn.org.polaris.utility.StringUtils;

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

	@Override
	@Transactional(readOnly=true)
	public InformationDTO getInformationByID(String id) {
		return InformationConvertor.toDto(infoCenterDao.getInformationByID(id));
	}

	@Override
	@Transactional
	public void releaseInformation(InformationDTO dto) {
		dto.setSystemRefInfo(IDGenerator.generateUUID());
		BaseDtoUtility.setCommonProperties(dto, TransactionIndicator.INSERT);
		infoCenterDao.releaseInformation(InformationConvertor.toPo(dto));
	}

	@Override
	@Transactional
	public void updateInformation(InformationDTO info) {
		
	}

	@Override
	@Transactional
	public void deleteInformationsByIds(String[] ids) {
		if(!StringUtils.isEmpty(ids)){			
			for(String id : ids){				
				infoCenterDao.deleteInformationByID(id);
			}
		}
	}

	@Override
	@Transactional
	public void deleteInformationByID(String id) {
		infoCenterDao.deleteInformationByID(id);
	}

}

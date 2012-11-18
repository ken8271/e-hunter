package com.pccw.ehunter.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pccw.ehunter.constant.IDNumberKeyConstant;
import com.pccw.ehunter.constant.TransactionIndicator;
import com.pccw.ehunter.convertor.PortalInformationCenterConvertor;
import com.pccw.ehunter.domain.portal.PortalInformationCenter;
import com.pccw.ehunter.dto.PagedCriteria;
import com.pccw.ehunter.dto.portal.PortalInformationCenterDTO;
import com.pccw.ehunter.helper.IDGenerator;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.PortalInformationCenterService;
import com.pccw.ehunter.utility.BaseDtoUtility;

@Service("infoCenterService")
@Transactional
public class PortalInformationCenterServiceImpl implements PortalInformationCenterService {
	
	private SimpleHibernateTemplate<PortalInformationCenter, String> simpleDao;
	
	@Autowired
	private IDGenerator idGenerator;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		simpleDao = new SimpleHibernateTemplate<PortalInformationCenter, String>(sessionFactory, PortalInformationCenter.class);
	}

	@Override
	@Transactional
	public void saveInformation(PortalInformationCenterDTO dto) {
		dto.setSystemRefInfo(idGenerator.generateID(IDNumberKeyConstant.PORTAL_INFORMATION_CENTER_SEQUENCE_KEY, null, 9));
		BaseDtoUtility.setCommonProperties(dto, TransactionIndicator.INSERT);
		
		simpleDao.save(PortalInformationCenterConvertor.toPo(dto));
	}

	@Override
	@Transactional(readOnly = true)
	public int getPortalInformationCountByCriteria(PagedCriteria pagedCriteria) {
		Long count = simpleDao.findCountByPagedCriteria(" SELECT COUNT(*) FROM PortalInformationCenter ");
		return count==null ? 0 : count.intValue();
	}

	@Override
	@Transactional(readOnly = true)
	public List<PortalInformationCenterDTO> getPortalInformationByCriteria(PagedCriteria pagedCriteria) {
		return PortalInformationCenterConvertor.toDtos(simpleDao.findByPagedCriteria(pagedCriteria));
	}

}

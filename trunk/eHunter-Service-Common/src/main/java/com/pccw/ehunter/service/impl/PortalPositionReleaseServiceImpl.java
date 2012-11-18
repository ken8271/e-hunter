package com.pccw.ehunter.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pccw.ehunter.constant.IDNumberKeyConstant;
import com.pccw.ehunter.constant.TransactionIndicator;
import com.pccw.ehunter.convertor.PortalReleasedPositionConvertor;
import com.pccw.ehunter.domain.portal.PortalReleasedPosition;
import com.pccw.ehunter.dto.PagedCriteria;
import com.pccw.ehunter.dto.portal.PortalReleasedPositionDTO;
import com.pccw.ehunter.helper.IDGenerator;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.PortalPositionReleaseService;
import com.pccw.ehunter.utility.BaseDtoUtility;

@Service("positionReleaseService")
@Transactional
public class PortalPositionReleaseServiceImpl implements PortalPositionReleaseService{
	
	@Autowired
	private IDGenerator idGenerator;
	
	private SimpleHibernateTemplate<PortalReleasedPosition, String> simpleDao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		simpleDao = new SimpleHibernateTemplate<PortalReleasedPosition, String>(sessionFactory, PortalReleasedPosition.class);
	}

	@Override
	@Transactional
	public void saveReleasedPosition(PortalReleasedPositionDTO dto) {
		dto.setSystemRefPosition(idGenerator.generateID(IDNumberKeyConstant.PORTAL_RELEASED_POSITION_SEQUENCE_KEY, null, 9));
		BaseDtoUtility.setCommonProperties(dto, TransactionIndicator.INSERT);
		
		simpleDao.save(PortalReleasedPositionConvertor.toPo(dto));
	}

	@Override
	@Transactional(readOnly = true)
	public int getReleasedPositionsCountByPagedCriteria(PagedCriteria pagedCriteria) {
		Long count = simpleDao.findCountByPagedCriteria(" SELECT COUNT(*) FROM PortalReleasedPosition ");
		return count == null ? 0 : count.intValue();
	}

	@Override
	@Transactional(readOnly = true)
	public List<PortalReleasedPositionDTO> getReleasedPositionsByCriteria(PagedCriteria pagedCriteria) {
		return PortalReleasedPositionConvertor.toDtos(simpleDao.findByPagedCriteria(pagedCriteria));
	}

}

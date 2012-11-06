package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.constant.TransactionIndicator;
import com.pccw.ehunter.convertor.SystemParameterConvertor;
import com.pccw.ehunter.dao.SystemParameterDAO;
import com.pccw.ehunter.domain.common.SystemParameter;
import com.pccw.ehunter.dto.PagedCriteria;
import com.pccw.ehunter.dto.SystemParameterDTO;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.SystemParameterService;
import com.pccw.ehunter.utility.BaseDtoUtility;
import com.pccw.ehunter.utility.StringUtils;

@Service("systemParameterService")
@Transactional
public class SystemParameterServiceImpl implements SystemParameterService{
	
	@Autowired
	private SystemParameterDAO systemParameterDao;
	
	private SimpleHibernateTemplate<SystemParameter, String> simpleDao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		simpleDao = new SimpleHibernateTemplate<SystemParameter, String>(sessionFactory, SystemParameter.class);
	}

	@Override
	@Transactional(readOnly=true)
	public int getParametersCountByCriteria(PagedCriteria pagedCriteria) {
		return systemParameterDao.getParametersCountByCriteria(pagedCriteria);
	}

	@Override
	@Transactional(readOnly=true)
	public List<SystemParameterDTO> getParametersByCriteria(PagedCriteria pagedCriteria) {
		List<Object> list = systemParameterDao.getParametersByCriteria(pagedCriteria);
		List<SystemParameterDTO> dtos = new ArrayList<SystemParameterDTO>();
		
		if(!CollectionUtils.isEmpty(list)){
			SystemParameterDTO dto = null;
			for(Object o : list){
				dto = new SystemParameterDTO();
				Object[] os = (Object[])o;
				
				dto.setParameterKey(StringUtils.isEmpty((String)os[0]) ? "" : (String)os[0]);
				dto.setValueType(StringUtils.isEmpty((String)os[1]) ? "" : (String)os[1]);
				dto.setValue(StringUtils.isEmpty((String)os[2]) ? "" : (String)os[2]);
				dto.setDescription(StringUtils.isEmpty((String)os[3]) ? "" : (String)os[3]);
				
				dtos.add(dto);
			}
		}
		
		return dtos;
	}

	@Override
	@Transactional(readOnly=true)
	public SystemParameterDTO getSystemParameterByKey(String key) {
		return SystemParameterConvertor.toDto(simpleDao.findUniqueByProperty("parameterKey", key));
	}

	@Override
	@Transactional
	public void updateSystemParameter(SystemParameterDTO dto) {
		BaseDtoUtility.setCommonProperties(dto, TransactionIndicator.UPDATE);
		simpleDao.update(SystemParameterConvertor.toPo(dto));
	}

	@Override
	@Transactional
	public void deleteSystemParameter(SystemParameterDTO dto) {
		simpleDao.delete(SystemParameterConvertor.toPo(dto));
	}

	@Override
	public void saveSystemParameter(SystemParameterDTO dto) {
		BaseDtoUtility.setCommonProperties(dto, TransactionIndicator.INSERT);
		simpleDao.save(SystemParameterConvertor.toPo(dto));
	}

}

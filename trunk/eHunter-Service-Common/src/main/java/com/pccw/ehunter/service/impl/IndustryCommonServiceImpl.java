package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.convertor.IndustryCategoryConvertor;
import com.pccw.ehunter.convertor.IndustryConvertor;
import com.pccw.ehunter.domain.common.Industry;
import com.pccw.ehunter.domain.common.IndustryCategory;
import com.pccw.ehunter.dto.IndustryCategoryDTO;
import com.pccw.ehunter.dto.IndustryDTO;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.IndustryCommonService;

@Service("industryCommonService")
@Transactional
public class IndustryCommonServiceImpl implements IndustryCommonService{
	
	private SimpleHibernateTemplate<IndustryCategory, String> categoryDao;
	
	private SimpleHibernateTemplate<Industry, String> dao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		categoryDao = new SimpleHibernateTemplate<IndustryCategory, String>(sessionFactory, IndustryCategory.class);
		dao = new SimpleHibernateTemplate<Industry, String>(sessionFactory, Industry.class);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<IndustryCategoryDTO> getIndustryCategories() {
		List<IndustryCategory> pos = categoryDao.findAllUndeleted();
		
		if(CollectionUtils.isEmpty(pos)){
			return null;
		}
		
		List<IndustryCategoryDTO> dtos = new ArrayList<IndustryCategoryDTO>();
		
		for(IndustryCategory po : pos){
			dtos.add(IndustryCategoryConvertor.toDto(po));
		}
		
		return dtos;
	}

	@Override
	@Transactional(readOnly=true)
	public List<IndustryDTO> getIndustriesByCategory(String categoryCode) {
		List<Industry> pos = dao.findUndeletedByProperty("categoryCode", categoryCode);
		
		if(CollectionUtils.isEmpty(pos)){
			return null;
		}
		
		List<IndustryDTO> dtos = new ArrayList<IndustryDTO>();
		
		for(Industry po : pos){
			dtos.add(IndustryConvertor.toDto(po));
		}
		
		return dtos;
	}

	@Override
	public IndustryDTO getIndustryByCode(String code) {
		return IndustryConvertor.toDto(dao.findUniqueByProperty("industryCode", code));
	}

}

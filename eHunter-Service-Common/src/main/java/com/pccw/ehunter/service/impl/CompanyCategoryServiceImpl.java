package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.convertor.CompanyCategoryConvertor;
import com.pccw.ehunter.convertor.CompanySizeConvertor;
import com.pccw.ehunter.domain.common.CompanyCategory;
import com.pccw.ehunter.domain.common.CompanySize;
import com.pccw.ehunter.dto.CompanyCategoryDTO;
import com.pccw.ehunter.dto.CompanySizeDTO;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.CompanyCategoryService;

@Service("companyCategoryService")
@Transactional
public class CompanyCategoryServiceImpl implements CompanyCategoryService{
	
	private SimpleHibernateTemplate<CompanyCategory, String> companyCategoryDao;
	
	private SimpleHibernateTemplate<CompanySize, String> companySizeDao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		companyCategoryDao = new SimpleHibernateTemplate<CompanyCategory, String>(sessionFactory, CompanyCategory.class);
		companySizeDao = new SimpleHibernateTemplate<CompanySize, String>(sessionFactory, CompanySize.class);
	}

	@Override
	@Transactional(readOnly=true)
	public List<CompanyCategoryDTO> getCompanyCategories() {
		List<CompanyCategory> pos = companyCategoryDao.findAllUndeleted("displaySeqNumber");
		
		if(CollectionUtils.isEmpty(pos)){
			return null;
		}
		
		List<CompanyCategoryDTO> dtos = new ArrayList<CompanyCategoryDTO>();
		
		for(CompanyCategory po : pos){
			dtos.add(CompanyCategoryConvertor.toDto(po));
		}
		
		return dtos;
	}

	@Override
	@Transactional(readOnly=true)
	public CompanyCategoryDTO getCompanyCategoryByCode(String code) {
		return CompanyCategoryConvertor.toDto(companyCategoryDao.findUniqueByProperty("code", code));
	}

	@Override
	public List<CompanySizeDTO> getCompanySizes() {
		List<CompanySize> sizes = companySizeDao.findAllUndeleted("displaySeqNumber");
		
		if(CollectionUtils.isEmpty(sizes)){
			return null;
		}
		
		List<CompanySizeDTO> dtos = new ArrayList<CompanySizeDTO>();
		
		for(CompanySize po : sizes ){
			dtos.add(CompanySizeConvertor.toDto(po));
		}
		return dtos;
	}

	@Override
	public CompanySizeDTO getCompanySizeByCode(String code) {
		return CompanySizeConvertor.toDto(companySizeDao.findUniqueByProperty("sizeCode", code));
	}

}

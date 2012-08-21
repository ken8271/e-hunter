package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.convertor.LanguageCategoryConvertor;
import com.pccw.ehunter.domain.common.LanguageCategory;
import com.pccw.ehunter.dto.LanguageCategoryDTO;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.LanguageCommonService;

@Service("languageService")
@Transactional
public class LanguageCommonServiceImpl implements LanguageCommonService{
	
	private SimpleHibernateTemplate<LanguageCategory, String> dao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		dao = new SimpleHibernateTemplate<LanguageCategory, String>(sessionFactory, LanguageCategory.class);
	}

	@Override
	@Transactional(readOnly=true)
	public List<LanguageCategoryDTO> getLanguageCategories() {
		List<LanguageCategory> pos = dao.findAllUndeleted("displaySeqNumber");
		List<LanguageCategoryDTO> dtos = new ArrayList<LanguageCategoryDTO>();
		
		if(CollectionUtils.isEmpty(pos)){
			return dtos;
		}
		
		for(LanguageCategory po : pos){
			dtos.add(LanguageCategoryConvertor.toDto(po));
		}
		return dtos;
	}

}

package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.convertor.SkillCategoryConvertor;
import com.pccw.ehunter.domain.common.SkillCategory;
import com.pccw.ehunter.dto.SkillCategoryDTO;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.SkillCategoryService;

@Service("skillCategoryService")
@Transactional
public class SkillCategoryServiceImpl implements SkillCategoryService{
	
	private SimpleHibernateTemplate<SkillCategory, String> dao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		dao = new SimpleHibernateTemplate<SkillCategory, String>(sessionFactory, SkillCategory.class);
	}

	@Override
	@Transactional(readOnly=true)
	public List<SkillCategoryDTO> getSkillCategories() {
		List<SkillCategory> pos = dao.findAllUndeleted("displaySeqNumber");
		List<SkillCategoryDTO> dtos = new ArrayList<SkillCategoryDTO>();
		
		if(CollectionUtils.isEmpty(pos)){
			return dtos;
		}
		
		for(SkillCategory po : pos){
			dtos.add(SkillCategoryConvertor.toDto(po));
		}
		
		return dtos;
	}

}

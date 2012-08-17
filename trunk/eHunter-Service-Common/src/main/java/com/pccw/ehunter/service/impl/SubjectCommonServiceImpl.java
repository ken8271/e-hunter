package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.convertor.SubjectCategoryConvertor;
import com.pccw.ehunter.convertor.SubjectConvertor;
import com.pccw.ehunter.domain.common.Subject;
import com.pccw.ehunter.domain.common.SubjectCategory;
import com.pccw.ehunter.dto.SubjectCategoryDTO;
import com.pccw.ehunter.dto.SubjectDTO;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.SubjectCommonService;

@Service("subjectCommonService")
@Transactional
public class SubjectCommonServiceImpl implements SubjectCommonService{
	
	private SimpleHibernateTemplate<SubjectCategory, String> categoryDao;
	
	private SimpleHibernateTemplate<Subject, String> dao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		categoryDao = new SimpleHibernateTemplate<SubjectCategory, String>(sessionFactory, SubjectCategory.class);
		dao = new SimpleHibernateTemplate<Subject, String>(sessionFactory,Subject.class);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<SubjectCategoryDTO> getAllSubjectTypes() {
		List<SubjectCategory> types = categoryDao.findAllUndeleted("displayIndexNumber");
		
		if(CollectionUtils.isEmpty(types)){
			return null;
		}
		
		List<SubjectCategoryDTO> typeDtos = new ArrayList<SubjectCategoryDTO>();
		for(SubjectCategory po : types){
			typeDtos.add(SubjectCategoryConvertor.toDto(po));
		}
		
		return typeDtos;
	}

	@Override
	public List<SubjectDTO> getSubjectsByType(String typeCode) {
		List<Subject> subjects = dao.findUndeletedByProperty("subjectType", typeCode);
		
		if(CollectionUtils.isEmpty(subjects)){
			return null;
		}
		
		List<SubjectDTO> dtos = new ArrayList<SubjectDTO>();
		for(Subject po : subjects){
			dtos.add(SubjectConvertor.toDto(po));
		}
		return dtos;
	}

	@Override
	public SubjectDTO getSubjectByCode(String code) {
		return SubjectConvertor.toDto(dao.findUniqueByProperty("subjectCode", code));
	}
}

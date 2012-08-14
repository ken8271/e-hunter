package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.convertor.SubjectTypeConvertor;
import com.pccw.ehunter.domain.common.SubjectType;
import com.pccw.ehunter.dto.SubjectTypeDTO;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.SubjectTypeService;

@Service("subjectTypeService")
@Transactional
public class SubjectTypeServiceImpl implements SubjectTypeService{
	
	private SimpleHibernateTemplate<SubjectType, String> dao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		dao = new SimpleHibernateTemplate<SubjectType, String>(sessionFactory,SubjectType.class);
	}

	@Override
	@Transactional(readOnly=true)
	public List<SubjectTypeDTO> getAllSubjectTypes() {
		List<SubjectType> types = dao.findAllUndeleted();
		
		if(CollectionUtils.isEmpty(types)){
			return null;
		}
		
		List<SubjectTypeDTO> typeDtos = new ArrayList<SubjectTypeDTO>();
		for(SubjectType po : types){
			typeDtos.add(SubjectTypeConvertor.toDto(po));
		}
		
		return typeDtos;
	}
	

}

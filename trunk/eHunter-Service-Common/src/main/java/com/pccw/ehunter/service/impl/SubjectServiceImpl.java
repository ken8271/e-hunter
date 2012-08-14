package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.convertor.SubjectConvertor;
import com.pccw.ehunter.domain.common.Subject;
import com.pccw.ehunter.dto.SubjectDTO;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.SubjectService;

@Service("subjectService")
@Transactional
public class SubjectServiceImpl implements SubjectService{
	private SimpleHibernateTemplate<Subject, String> dao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		dao = new SimpleHibernateTemplate<Subject, String>(sessionFactory,Subject.class);
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
}

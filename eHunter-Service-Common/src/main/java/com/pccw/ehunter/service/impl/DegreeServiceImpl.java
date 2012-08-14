package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.convertor.DegreeConvertor;
import com.pccw.ehunter.domain.common.Degree;
import com.pccw.ehunter.dto.DegreeDTO;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.DegreeService;

@Service("degreeService")
@Transactional
public class DegreeServiceImpl implements DegreeService{
	private SimpleHibernateTemplate<Degree, String> dao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		dao = new SimpleHibernateTemplate<Degree, String>(sessionFactory,Degree.class);
	}

	@Override
	@Transactional(readOnly=true)
	public List<DegreeDTO> getAllDegrees() {
		List<Degree> dgres = dao.findAllUndeleted();
		
		if(CollectionUtils.isEmpty(dgres)){
			return null;
		}
		
		List<DegreeDTO> dtos = new ArrayList<DegreeDTO>();
		
		for(Degree po : dgres){
			dtos.add(DegreeConvertor.toDto(po));
		}
		return dtos;
	}
}

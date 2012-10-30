package com.pccw.ehunter.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.pccw.ehunter.dao.CurriculumVitaeUploadDAO;
import com.pccw.ehunter.domain.internal.UploadedCurriculumVitae;

@Component("cvUploadDao")
public class HibernateCurriculumVitaeUploadDAO implements CurriculumVitaeUploadDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void saveUploadedCurriculumVitae(UploadedCurriculumVitae po) {
		hibernateTemplate.save(po);
	}

}

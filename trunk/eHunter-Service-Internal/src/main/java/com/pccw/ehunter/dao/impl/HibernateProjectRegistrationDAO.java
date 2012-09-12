package com.pccw.ehunter.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.pccw.ehunter.dao.ProjectRegistrationDAO;
import com.pccw.ehunter.domain.internal.Project;

@Component("projectRegtDao")
public class HibernateProjectRegistrationDAO implements ProjectRegistrationDAO{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void saveProject(Project project) {
		hibernateTemplate.save(project);
	}

}

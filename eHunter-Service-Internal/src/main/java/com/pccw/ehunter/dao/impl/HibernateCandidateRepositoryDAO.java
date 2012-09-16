package com.pccw.ehunter.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.dao.CandidateRepositoryDAO;
import com.pccw.ehunter.domain.internal.ProjectCandidateRepository;

@Component("cddtRepoDao")
public class HibernateCandidateRepositoryDAO implements CandidateRepositoryDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void saveCandidateRepository(List<ProjectCandidateRepository> repos) {
		if(!CollectionUtils.isEmpty(repos)){
			for(ProjectCandidateRepository repo : repos){
				hibernateTemplate.save(repo);
			}
		}
	}

}

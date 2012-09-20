package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.convertor.ProjectStatusConvertor;
import com.pccw.ehunter.domain.common.ProjectStatus;
import com.pccw.ehunter.dto.ProjectStatusDTO;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.ProjectStatusService;

@Service("projectStatusService")
@Transactional
public class ProjectStatusServiceImpl implements ProjectStatusService{
	
	private SimpleHibernateTemplate<ProjectStatus, String> dao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		dao = new SimpleHibernateTemplate<ProjectStatus, String>(sessionFactory, ProjectStatus.class);
	}

	@Override
	public List<ProjectStatusDTO> getProjectStatus() {
		List<ProjectStatusDTO> dtos = new ArrayList<ProjectStatusDTO>();
		List<ProjectStatus> pos = dao.findAllUndeleted("displaySeqNumber");
		
		if(!CollectionUtils.isEmpty(pos)){
			for(ProjectStatus po : pos){
				dtos.add(ProjectStatusConvertor.toDto(po));
			}
		}
		
		return dtos;
	}

}

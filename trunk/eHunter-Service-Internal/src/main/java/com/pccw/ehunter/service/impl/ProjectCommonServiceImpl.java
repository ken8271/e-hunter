package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.convertor.ProjectConvertor;
import com.pccw.ehunter.dao.ProjectCommonDAO;
import com.pccw.ehunter.domain.internal.Project;
import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.dto.InternalUserDTO;
import com.pccw.ehunter.dto.ProjectDTO;
import com.pccw.ehunter.dto.ProjectPagedCriteria;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.ProjectCommonService;
import com.pccw.ehunter.utility.StringUtils;

@Service("projectCommonService")
@Transactional
public class ProjectCommonServiceImpl implements ProjectCommonService{
	
	@Autowired
	private ProjectCommonDAO projectCommonDao;
	
	private SimpleHibernateTemplate<Project, String> simpleDao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		simpleDao = new SimpleHibernateTemplate<Project, String>(sessionFactory, Project.class);
	}

	@Override
	@Transactional(readOnly=true)
	public int getProjectsCountByCriteria(ProjectPagedCriteria pagedCriteria) {
		return projectCommonDao.getProjectsCountByCriteria(pagedCriteria);
	}

	@Override
	@Transactional(readOnly=true)
	public List<ProjectDTO> getProjectsByCriteria(ProjectPagedCriteria pagedCriteria) {
		List<ProjectDTO> projects = new ArrayList<ProjectDTO>();
		List<Object> list = projectCommonDao.getProjectsByCriteria(pagedCriteria);
		if(!CollectionUtils.isEmpty(list)){
			ProjectDTO dto = null;
			for(Object o : list){
				dto = new ProjectDTO();
				Object[] objs = (Object[])o;
				
				dto.setSystemProjectRefNum(StringUtils.isEmpty((String)objs[0]) ? "" : (String)objs[0]);
				dto.setProjectName(StringUtils.isEmpty((String)objs[1]) ? "" : (String)objs[1]);
				
				CustomerDTO cust = new CustomerDTO();
				cust.setFullName(StringUtils.isEmpty((String)objs[2]) ? "" : (String)objs[2]);
				dto.setCustomerDto(cust);
				
				dto.setCreateDateTime((Date)objs[3]);
				
				InternalUserDTO usr = new InternalUserDTO();
				usr.setCnName(StringUtils.isEmpty((String)objs[4]) ? "" : (String)objs[4]);
				dto.setAdviserDto(usr);
				
				projects.add(dto);
			}
		}
		
		return projects;
	}

	@Override
	@Transactional(readOnly=true)
	public ProjectDTO getProjectByID(String id) {
		return ProjectConvertor.toDto(simpleDao.findUniqueByProperty("systemProjectRefNum", id));
	}

}

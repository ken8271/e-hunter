package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.dao.ProjectCommonDAO;
import com.pccw.ehunter.dto.ProjectDTO;
import com.pccw.ehunter.dto.ProjectPagedCriteria;
import com.pccw.ehunter.service.ProjectCommonService;
import com.pccw.ehunter.utility.StringUtils;

@Service("projectCommonService")
public class ProjectCommonServiceImpl implements ProjectCommonService{
	
	@Autowired
	private ProjectCommonDAO projectCommonDao;

	@Override
	public int getProjectsCountByCriteria(ProjectPagedCriteria pagedCriteria) {
		return projectCommonDao.getProjectsCountByCriteria(pagedCriteria);
	}

	@Override
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
				
				projects.add(dto);
			}
		}
		
		return projects;
	}

}

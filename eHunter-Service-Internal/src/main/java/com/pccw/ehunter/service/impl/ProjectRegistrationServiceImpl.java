package com.pccw.ehunter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pccw.ehunter.constant.IDNumberKeyConstant;
import com.pccw.ehunter.constant.TransactionIndicator;
import com.pccw.ehunter.convertor.ProjectConvertor;
import com.pccw.ehunter.dao.ProjectRegistrationDAO;
import com.pccw.ehunter.domain.internal.Project;
import com.pccw.ehunter.dto.PositionDescriptionDTO;
import com.pccw.ehunter.dto.PositionRequirementDTO;
import com.pccw.ehunter.dto.ProjectDTO;
import com.pccw.ehunter.helper.IDGenerator;
import com.pccw.ehunter.service.ProjectRegistrationService;
import com.pccw.ehunter.utility.BaseDtoUtility;

@Service("projectRegtService")
@Transactional
public class ProjectRegistrationServiceImpl implements ProjectRegistrationService{
	
	@Autowired
	private ProjectRegistrationDAO projectRegtDao;
	
	@Autowired
	private IDGenerator idGenerator;

	@Override
	@Transactional
	public void submitProject(ProjectDTO projectDto) {
		System.out.println("---------------1-----------------");
		if(projectDto != null){		
			System.out.println("---------------2-----------------");
			String projectID = idGenerator.generateID(IDNumberKeyConstant.PROJECT_SEQUENCE_KEY, "P", 9);
			projectDto.setSystemProjectRefNum(projectID);
			BaseDtoUtility.setCommonProperties(projectDto, TransactionIndicator.INSERT);
			
			PositionDescriptionDTO pdDto = projectDto.getPostDescDto();
			PositionRequirementDTO prDto = projectDto.getPostRequireDto();
			if(pdDto != null){
				System.out.println("---------------3-----------------");
				pdDto.setSystemPositionRefNum(projectID);
				BaseDtoUtility.setCommonProperties(pdDto, TransactionIndicator.INSERT);
			}
			
			if(prDto != null){
				System.out.println("---------------4-----------------");
				prDto.setSystemPositionRefNum(projectID);
				BaseDtoUtility.setCommonProperties(prDto, TransactionIndicator.INSERT);
			}
		}
		
		System.out.println("---------------6-----------------" + projectDto.getPostDescDto().getSystemPositionRefNum());
		Project project = ProjectConvertor.toPo(projectDto , TransactionIndicator.INSERT);
		
		System.out.println(project.getPostDescription().getSystemPositionRefNum());
		System.out.println("---------------5-----------------");
		projectRegtDao.saveProject(project);
	}

}

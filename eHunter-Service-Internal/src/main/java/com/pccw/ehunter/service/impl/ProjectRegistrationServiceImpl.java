package com.pccw.ehunter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pccw.ehunter.constant.IDNumberKeyConstant;
import com.pccw.ehunter.constant.StatusCode;
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
		if(projectDto != null){		
			String projectID = idGenerator.generateID(IDNumberKeyConstant.PROJECT_SEQUENCE_KEY, "P", 9);
			projectDto.setSystemProjectRefNum(projectID);
			projectDto.setStatus(StatusCode.INITIALIZED);
			BaseDtoUtility.setCommonProperties(projectDto, TransactionIndicator.INSERT);
			
			PositionDescriptionDTO pdDto = projectDto.getPostDescDto();
			PositionRequirementDTO prDto = projectDto.getPostRequireDto();
			if(pdDto != null){
				pdDto.setSystemPositionRefNum(projectID);
				BaseDtoUtility.setCommonProperties(pdDto, TransactionIndicator.INSERT);
			}
			
			if(prDto != null){
				prDto.setSystemPositionRefNum(projectID);
				BaseDtoUtility.setCommonProperties(prDto, TransactionIndicator.INSERT);
			}
		}
		
		Project project = ProjectConvertor.toPo(projectDto , TransactionIndicator.INSERT);
		
		projectRegtDao.saveProject(project);
	}

	@Override
	@Transactional
	public void updateProject(ProjectDTO projectDto) {
		
	}

	@Override
	@Transactional
	public void updateProjectStatus(ProjectDTO projectDTO) {
		if(projectDTO == null) return ;
		
		BaseDtoUtility.setCommonProperties(projectDTO, TransactionIndicator.UPDATE);
		
		projectRegtDao.updateProjectStatus(ProjectConvertor.toPo(projectDTO, TransactionIndicator.UPDATE));
	}

	@Override
	@Transactional
	public void updatePositionDescription(ProjectDTO dto) {
		if(dto == null) return ;
		
		BaseDtoUtility.setCommonProperties(dto, TransactionIndicator.UPDATE);
		
		PositionDescriptionDTO pdDto = dto.getPostDescDto();
		if(pdDto != null){
			BaseDtoUtility.setCommonProperties(pdDto, TransactionIndicator.UPDATE);
		}
		
		projectRegtDao.updateProject(ProjectConvertor.toPo(dto, TransactionIndicator.UPDATE));

	}

	@Override
	@Transactional
	public void updatePositionRequirement(ProjectDTO dto) {
		if(dto == null) return ;
		
		BaseDtoUtility.setCommonProperties(dto, TransactionIndicator.UPDATE);
		
		PositionRequirementDTO prDto = dto.getPostRequireDto();
		
		if(prDto != null){
			BaseDtoUtility.setCommonProperties(prDto, TransactionIndicator.UPDATE);
		}
		
		projectRegtDao.updateProject(ProjectConvertor.toPo(dto, TransactionIndicator.INSERT));
	}
}

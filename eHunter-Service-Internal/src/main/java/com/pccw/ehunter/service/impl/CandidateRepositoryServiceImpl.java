package com.pccw.ehunter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.constant.TransactionIndicator;
import com.pccw.ehunter.convertor.ProjectCandidateRepositoryConvertor;
import com.pccw.ehunter.dao.CandidateRepositoryDAO;
import com.pccw.ehunter.dto.ProjectCandidateRepositoryDTO;
import com.pccw.ehunter.service.CandidateRepositoryService;
import com.pccw.ehunter.utility.BaseDtoUtility;

@Service("cddtRepoService")
@Transactional
public class CandidateRepositoryServiceImpl implements CandidateRepositoryService{
	
	@Autowired
	private CandidateRepositoryDAO cddtRepoDao;

	@Override
	@Transactional
	public void saveCandidateRepository(List<ProjectCandidateRepositoryDTO> repoDtos) {
		if(CollectionUtils.isEmpty(repoDtos)) return ;
		
		for(ProjectCandidateRepositoryDTO dto : repoDtos){
			BaseDtoUtility.setCommonProperties(dto, TransactionIndicator.INSERT);
		}
		
		cddtRepoDao.saveCandidateRepository(ProjectCandidateRepositoryConvertor.toPos(repoDtos));
	}
}

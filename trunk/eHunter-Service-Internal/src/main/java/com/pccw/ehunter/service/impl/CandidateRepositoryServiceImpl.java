package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.constant.TransactionIndicator;
import com.pccw.ehunter.convertor.CandidateConvertor;
import com.pccw.ehunter.dao.CandidateRepositoryDAO;
import com.pccw.ehunter.dto.CandidateDTO;
import com.pccw.ehunter.dto.DegreeDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.dto.TalentPagedCriteria;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.service.CandidateRepositoryService;
import com.pccw.ehunter.utility.BaseDtoUtility;
import com.pccw.ehunter.utility.StringUtils;

@Service("cddtRepoService")
@Transactional
public class CandidateRepositoryServiceImpl implements CandidateRepositoryService{
	
	@Autowired
	private CandidateRepositoryDAO cddtRepoDao;
	
	@Autowired
	private CodeTableHelper codeTableHelper;

	@Override
	@Transactional
	public void saveCandidateRepository(List<CandidateDTO> cddtDtos) {
		if(CollectionUtils.isEmpty(cddtDtos)) return ;
		
		for(CandidateDTO dto : cddtDtos){
			BaseDtoUtility.setCommonProperties(dto, TransactionIndicator.INSERT);
		}
		
		cddtRepoDao.saveCandidateRepository(CandidateConvertor.toPos(cddtDtos));
	}

	@Override
	@Transactional(readOnly = true)
	public int getCandidateRepositoryCountByProjectID(TalentPagedCriteria pagedCriteria) {
		return cddtRepoDao.getCandidateRepositoryCountByProjectID(pagedCriteria);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CandidateDTO> getCandidateRepositoryByProjectID(HttpServletRequest request , TalentPagedCriteria pagedCriteria) {
		List<CandidateDTO> dtos = new ArrayList<CandidateDTO>();
		List<Object> list = cddtRepoDao.getCandidateRepositoryByProjectID(pagedCriteria);
		
		if(!CollectionUtils.isEmpty(list)){
			CandidateDTO cddt = null;
			TalentDTO tlnt = null;
			DegreeDTO dg = null;
			for(Object o : list){
				cddt = new CandidateDTO();
				Object[] os = (Object[])o;
				
				tlnt = new TalentDTO();
				tlnt.setTalentID(StringUtils.isEmpty((String)os[0]) ? "" : (String)os[0]);
				tlnt.setCnName(StringUtils.isEmpty((String)os[1]) ? "" : (String)os[1]);
				tlnt.setEnName(StringUtils.isEmpty((String)os[2]) ? "" : (String)os[2]);
				
				dg = new DegreeDTO();
				dg.setDegreeCode(StringUtils.isEmpty((String)os[3]) ? "" : (String)os[3]);
				dg.setDisplayName(StringUtils.isEmpty((String)os[4]) ? "" : (String)os[4]);
				tlnt.setDegreeDto(dg);
				
				tlnt.setNowLivePlace(StringUtils.isEmpty((String)os[5]) ? "" : (String)os[5]);
				cddt.setTalentDto(tlnt);
				
				cddt.setCandidateStatus(StringUtils.isEmpty((String)os[6]) ? "" : (String)os[6]);
				cddt.setCandidateStatusDto(codeTableHelper.getCandidateStatusByCode(request, cddt.getCandidateStatus()));
				
				dtos.add(cddt);
			}
		}
		
		return dtos;
	}
}

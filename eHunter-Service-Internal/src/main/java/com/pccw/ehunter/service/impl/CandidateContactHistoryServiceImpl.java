package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.constant.DateFormatConstant;
import com.pccw.ehunter.constant.IDNumberKeyConstant;
import com.pccw.ehunter.constant.TransactionIndicator;
import com.pccw.ehunter.convertor.CandidateContactHistoryConvertor;
import com.pccw.ehunter.dao.CandidateContactHistoryDAO;
import com.pccw.ehunter.dao.CandidateRepositoryDAO;
import com.pccw.ehunter.domain.internal.Candidate;
import com.pccw.ehunter.domain.internal.CandidatePK;
import com.pccw.ehunter.domain.internal.Project;
import com.pccw.ehunter.domain.internal.Talent;
import com.pccw.ehunter.dto.CandidateContactHistoryDTO;
import com.pccw.ehunter.dto.InternalUserDTO;
import com.pccw.ehunter.dto.ProjectDTO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.helper.IDGenerator;
import com.pccw.ehunter.service.CandidateContactHistoryService;
import com.pccw.ehunter.utility.BaseDtoUtility;
import com.pccw.ehunter.utility.BaseEntityUtility;
import com.pccw.ehunter.utility.DateUtils;
import com.pccw.ehunter.utility.StringUtils;

@Service("candidateContactHistoryService")
@Transactional
public class CandidateContactHistoryServiceImpl implements CandidateContactHistoryService{
	
	@Autowired
	private CandidateContactHistoryDAO candidateContactHistoryDao;
	
	@Autowired
	private CandidateRepositoryDAO cddtRepoDao;
	
	@Autowired
	private IDGenerator idGenerator;

	@Override
	@Transactional(readOnly=true)
	public List<CandidateContactHistoryDTO> getContactHistories(String talentID, String projectID) {
		List<CandidateContactHistoryDTO> dtos = new ArrayList<CandidateContactHistoryDTO>();
		List<Object> list = candidateContactHistoryDao.getContactHistories(talentID, projectID);
		
		if(!CollectionUtils.isEmpty(list)){
			CandidateContactHistoryDTO dto = null;
			for(Object o : list){
				dto = new CandidateContactHistoryDTO();
				Object[] os = (Object[])o;
				
				dto.setSystemContactRefNum(StringUtils.isEmpty((String)os[0]) ? "" : (String)os[0]);
				dto.setContactCategory(StringUtils.isEmpty((String)os[1]) ? "" : (String)os[1]);
				dto.setRecord(StringUtils.isEmpty((String)os[2]) ? "" : (String)os[2]);
				
				InternalUserDTO usr = new InternalUserDTO();
				usr.setCnName(StringUtils.isEmpty((String)os[3]) ? "" : (String)os[3]);
				dto.setAdviserDto(usr);
				
				dto.setCreateDateTime(os[4] == null ? (new Date()) : ((Date)os[4]));
				
				dto.setRemark(StringUtils.isEmpty((String)os[5]) ? "" : (String)os[5]);
				
				dtos.add(dto);
			}
		}
		
		return dtos;
	}

	@Override
	@Transactional
	public void saveContactHistory(CandidateContactHistoryDTO dto) {
		if(dto == null) return ;
		
		dto.setSystemContactRefNum(idGenerator.generateID(IDNumberKeyConstant.CANDIDATE_CONTACT_SEQUENCE_KEY, DateUtils.formatDateTime(DateFormatConstant.DATE_YYMMDD, new Date()), 9 , true));
		
		BaseDtoUtility.setCommonProperties(dto, TransactionIndicator.INSERT);
		
		candidateContactHistoryDao.saveContactHistory(CandidateContactHistoryConvertor.toPo(dto));
		
		CandidatePK pk = new CandidatePK();
		Talent t = new Talent();
		t.setTalentID(dto.getTalentDto().getTalentID());
		pk.setTalent(t);
		Project p = new Project();
		p.setSystemProjectRefNum(dto.getProjectDto().getSystemProjectRefNum());
		pk.setProject(p);
		
		Candidate po = new Candidate();
		BaseEntityUtility.updateCommonProperties( po, TransactionIndicator.UPDATE );
		po.setCandidateStatus(dto.getContactCategory());
		po.setPk(pk);
		
		cddtRepoDao.updateCandidateStatus(po);
	}

	@Override
	@Transactional(readOnly=true)
	public CandidateContactHistoryDTO getContactHistoryByID(String id) {
		CandidateContactHistoryDTO dto = new CandidateContactHistoryDTO();
		dto.setTalentDto(new TalentDTO());
		dto.setProjectDto(new ProjectDTO());
		dto.setAdviserDto(new InternalUserDTO());
		
		Object o = candidateContactHistoryDao.getContactHistoryByID(id);
		
		if( null != o){
			Object[] os = (Object[])o;
			
			dto.setSystemContactRefNum(StringUtils.isEmpty((String)os[0]) ? "" : (String)os[0]);
			dto.getTalentDto().setTalentID(StringUtils.isEmpty((String)os[1]) ? "" : (String)os[1]);
			dto.getTalentDto().setEnName(StringUtils.isEmpty((String)os[2]) ? "" : (String)os[2]);
			dto.getTalentDto().setCnName(StringUtils.isEmpty((String)os[3]) ? "" : (String)os[3]);
			dto.getProjectDto().setSystemProjectRefNum(StringUtils.isEmpty((String)os[4]) ? "" : (String)os[4]);
			dto.getProjectDto().setProjectName(StringUtils.isEmpty((String)os[5]) ? "" : (String)os[5]);
			dto.setContactCategory(StringUtils.isEmpty((String)os[6]) ? "" : (String)os[6]);
			dto.setRecord(StringUtils.isEmpty((String)os[7]) ? "" : (String)os[7]);
			dto.setRemark(StringUtils.isEmpty((String)os[8]) ? "" : (String)os[8]);
			dto.getAdviserDto().setUserRecId(StringUtils.isEmpty((String)os[9]) ? "" : (String)os[9]);
			dto.getAdviserDto().setCnName(StringUtils.isEmpty((String)os[10]) ? "" : (String)os[10]);
			dto.setCreateDateTime(os[11]==null ? (new Date()) : ((Date)os[11]));
		}
		
		return dto;
	}
}

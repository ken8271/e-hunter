package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.dao.CandidateContactHistoryDAO;
import com.pccw.ehunter.dto.CandidateContactHistoryDTO;
import com.pccw.ehunter.dto.InternalUserDTO;
import com.pccw.ehunter.service.CandidateContactHistoryService;
import com.pccw.ehunter.utility.StringUtils;

@Service("candidateContactHistoryService")
@Transactional
public class CandidateContactHistoryServiceImpl implements CandidateContactHistoryService{
	
	@Autowired
	private CandidateContactHistoryDAO candidateContactHistoryDao;

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
				
				InternalUserDTO usr = new InternalUserDTO();
				usr.setCnName(StringUtils.isEmpty((String)os[2]) ? "" : (String)os[2]);
				dto.setAdviserDto(usr);
				
				dto.setCreateDateTime(os[3] == null ? (new Date()) : ((Date)os[3]));
				
				dtos.add(dto);
			}
		}
		
		return dtos;
	}
}

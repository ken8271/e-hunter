package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.convertor.CandidateStatusConvertor;
import com.pccw.ehunter.domain.common.CandidateStatus;
import com.pccw.ehunter.dto.CandidateStatusDTO;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.CandidateStatusService;

@Service("candidateStatusService")
@Transactional
public class CandidateStatusServiceImpl implements CandidateStatusService{
	
	private SimpleHibernateTemplate<CandidateStatus, String> dao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		dao = new SimpleHibernateTemplate<CandidateStatus, String>(sessionFactory, CandidateStatus.class);
	}

	@Override
	public List<CandidateStatusDTO> getCandidateStatus() {
		List<CandidateStatusDTO> dtos = new ArrayList<CandidateStatusDTO>();
		List<CandidateStatus> pos = dao.findAllUndeleted("displaySeqNumber");
		
		if(!CollectionUtils.isEmpty(pos)){
			for(CandidateStatus po : pos){
				dtos.add(CandidateStatusConvertor.toDto(po));
			}
		}
		
		return dtos;
	}

}

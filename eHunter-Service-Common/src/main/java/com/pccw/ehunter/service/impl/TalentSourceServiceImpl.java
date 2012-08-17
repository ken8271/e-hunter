package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.convertor.TalentSourceConvertor;
import com.pccw.ehunter.domain.common.TalentSource;
import com.pccw.ehunter.dto.TalentSourceDTO;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.TalentSourceService;

@Service("talentSourceService")
@Transactional
public class TalentSourceServiceImpl implements TalentSourceService {
	
	private SimpleHibernateTemplate<TalentSource, String> dao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		dao = new SimpleHibernateTemplate<TalentSource, String>(sessionFactory,TalentSource.class);
	}

	@Override
	public List<TalentSourceDTO> getAllTalentSources() {
		List<TalentSource> srcs = dao.findAllUndeleted("displaySeqNumber");
		
		if(CollectionUtils.isEmpty(srcs)){
			return null;
		}
		
		List<TalentSourceDTO> srcDtos = new ArrayList<TalentSourceDTO>();
		for(TalentSource po : srcs){
			srcDtos.add(TalentSourceConvertor.toDto(po));
		}
		
		return srcDtos;
	}
}

package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.constant.IDNumberKeyConstant;
import com.pccw.ehunter.constant.TransactionIndicator;
import com.pccw.ehunter.convertor.TalentSourceConvertor;
import com.pccw.ehunter.domain.common.TalentSource;
import com.pccw.ehunter.dto.TalentSourceDTO;
import com.pccw.ehunter.helper.IDGenerator;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.TalentSourceService;
import com.pccw.ehunter.utility.BaseDtoUtility;

@Service("talentSourceService")
@Transactional
public class TalentSourceServiceImpl implements TalentSourceService {
	
	private SimpleHibernateTemplate<TalentSource, String> dao;
	
	@Autowired
	private IDGenerator idGenerator;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		dao = new SimpleHibernateTemplate<TalentSource, String>(sessionFactory,TalentSource.class);
	}

	@Override
	@Transactional(readOnly=true)
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

	@Override
	@Transactional
	public void saveTalentSource(TalentSourceDTO dto) {
		dto.setSourceId(idGenerator.generateID(IDNumberKeyConstant.TALENT_SOURCE_SEQUENCE_KEY, null, 3 ,false));
		BaseDtoUtility.setCommonProperties(dto, TransactionIndicator.INSERT);
		dao.save(TalentSourceConvertor.toPo(dto));
	}

	@Override
	@Transactional(readOnly=true)
	public boolean isTalentSourceExists(String officialSite) {
		List<TalentSource> srcs =  dao.findByProperty("officialSite", officialSite);
		
		if(!CollectionUtils.isEmpty(srcs)){
			return true;
		}
		
		return false;
	}

	@Override
	@Transactional
	public void deleteTalentSource(String id) {
		TalentSource po = dao.findUniqueByProperty("sourceId", id);
		dao.delete(po);
	}
}

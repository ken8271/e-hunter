package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.convertor.SkillLevelConvertor;
import com.pccw.ehunter.domain.common.SkillLevel;
import com.pccw.ehunter.dto.SkillLevelDTO;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.SkillLevelService;

@Service("skillLevelService")
@Transactional
public class SkillLevelServiceImpl implements SkillLevelService{
	
	private SimpleHibernateTemplate<SkillLevel, String> dao;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		dao = new SimpleHibernateTemplate<SkillLevel, String>(sessionFactory, SkillLevel.class);
	}

	@Override
	@Transactional(readOnly=true)
	public List<SkillLevelDTO> getSkillLevels() {
		List<SkillLevel> pos = dao.findAllUndeleted("displaySeqNumber");
		List<SkillLevelDTO> dtos = new ArrayList<SkillLevelDTO>();
		
		if(CollectionUtils.isEmpty(pos)){
			return dtos;
		}
		
		for(SkillLevel po : pos){
			dtos.add(SkillLevelConvertor.toDto(po));
		}
		
		return dtos;
	}

}

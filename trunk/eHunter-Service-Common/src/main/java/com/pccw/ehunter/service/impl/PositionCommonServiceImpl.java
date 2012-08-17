package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.convertor.PositionCategoryConvertor;
import com.pccw.ehunter.convertor.PositionConvertor;
import com.pccw.ehunter.domain.common.Position;
import com.pccw.ehunter.domain.common.PositionCategory;
import com.pccw.ehunter.dto.PositionCategoryDTO;
import com.pccw.ehunter.dto.PositionDTO;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.PositionCommonService;

@Service("positionCommonService")
@Transactional
public class PositionCommonServiceImpl implements PositionCommonService{
	
	private SimpleHibernateTemplate<PositionCategory, String> categoryDao;
	
	private SimpleHibernateTemplate<Position, String> dao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		categoryDao = new SimpleHibernateTemplate<PositionCategory, String>(sessionFactory, PositionCategory.class);
		dao = new SimpleHibernateTemplate<Position, String>(sessionFactory, Position.class);
	}

	@Override
	@Transactional(readOnly=true)
	public List<PositionCategoryDTO> getPositionCategories() {
		List<PositionCategory> pos = categoryDao.findAllUndeleted();
		
		if(CollectionUtils.isEmpty(pos)){
			return null;
		}
		
		List<PositionCategoryDTO> dtos = new ArrayList<PositionCategoryDTO>();
		
		for(PositionCategory po : pos){
			dtos.add(PositionCategoryConvertor.toDto(po));
		}
		
		return dtos;
	}

	@Override
	@Transactional(readOnly=true)
	public List<PositionDTO> getPositionsByCategory(String categoryCode) {
		List<Position> pos = dao.findUndeletedByProperty("topType", categoryCode);
		
		if(CollectionUtils.isEmpty(pos)){
			return null;
		}
		
		List<PositionDTO> dtos = new ArrayList<PositionDTO>();
		
		for(Position po : pos){
			dtos.add(PositionConvertor.toDto(po));
		}
		
		return dtos;
	}

	@Override
	@Transactional(readOnly=true)
	public PositionDTO getPositionByCode(String code) {
		return PositionConvertor.toDto(dao.findUniqueByProperty("typeCode", code));
	}
}

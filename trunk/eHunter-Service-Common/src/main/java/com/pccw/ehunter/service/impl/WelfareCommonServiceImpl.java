package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.convertor.AnnualLeaveWelfareConvertor;
import com.pccw.ehunter.convertor.ResidentialWelfareConvertor;
import com.pccw.ehunter.convertor.SalaryCategoryConvertor;
import com.pccw.ehunter.convertor.SocietyWelfareConvertor;
import com.pccw.ehunter.domain.common.AnnualLeaveWelfare;
import com.pccw.ehunter.domain.common.ResidentialWelfare;
import com.pccw.ehunter.domain.common.SalaryCategory;
import com.pccw.ehunter.domain.common.SocietyWelfare;
import com.pccw.ehunter.dto.AnnualLeaveWelfareDTO;
import com.pccw.ehunter.dto.ResidentialWelfareDTO;
import com.pccw.ehunter.dto.SalaryCategoryDTO;
import com.pccw.ehunter.dto.SocietyWelfareDTO;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.WelfareCommonService;

@Service("welfareCommonService")
@Transactional
public class WelfareCommonServiceImpl implements WelfareCommonService{
	
	private SimpleHibernateTemplate<AnnualLeaveWelfare, String> annualLeaveWelfareDao;
	private SimpleHibernateTemplate<SalaryCategory, String> salaryCategoryDao;
	private SimpleHibernateTemplate<ResidentialWelfare, String> residentialWelfareDao;
	private SimpleHibernateTemplate<SocietyWelfare, String> societyWelfareDao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		annualLeaveWelfareDao = new SimpleHibernateTemplate<AnnualLeaveWelfare, String>(sessionFactory, AnnualLeaveWelfare.class);
		salaryCategoryDao = new SimpleHibernateTemplate<SalaryCategory, String>(sessionFactory, SalaryCategory.class);
		residentialWelfareDao = new SimpleHibernateTemplate<ResidentialWelfare, String>(sessionFactory, ResidentialWelfare.class);
		societyWelfareDao = new SimpleHibernateTemplate<SocietyWelfare, String>(sessionFactory, SocietyWelfare.class);
	}

	@Override
	@Transactional(readOnly=true)
	public List<AnnualLeaveWelfareDTO> getAnnualLeaveWelfares() {
		List<AnnualLeaveWelfare> pos = annualLeaveWelfareDao.findAllUndeleted("displaySeqNumber");
		List<AnnualLeaveWelfareDTO> dtos = new ArrayList<AnnualLeaveWelfareDTO>();
		
		if(CollectionUtils.isEmpty(pos)){
			return dtos;
		}
		
		for(AnnualLeaveWelfare po : pos){
			dtos.add(AnnualLeaveWelfareConvertor.toDto(po));
		}
		
		return dtos;
	}

	@Override
	@Transactional(readOnly=true)
	public List<SalaryCategoryDTO> getSalaryCategories() {
		List<SalaryCategory> pos = salaryCategoryDao.findAllUndeleted("displaySeqNumber");
		List<SalaryCategoryDTO> dtos = new ArrayList<SalaryCategoryDTO>();
		
		if(CollectionUtils.isEmpty(pos)){
			return dtos;
		}
		
		for(SalaryCategory po : pos){
			dtos.add(SalaryCategoryConvertor.toDto(po));
		}
		
		return dtos;
	}

	@Override
	@Transactional(readOnly=true)
	public List<ResidentialWelfareDTO> getResidentialWelfares() {
		List<ResidentialWelfare> pos = residentialWelfareDao.findAllUndeleted("displaySeqNumber");
		List<ResidentialWelfareDTO> dtos = new ArrayList<ResidentialWelfareDTO>();
		
		if(CollectionUtils.isEmpty(pos)){
			return dtos;
		}
		
		for(ResidentialWelfare po : pos){
			dtos.add(ResidentialWelfareConvertor.toDto(po));
		}
		
		return dtos;
	}

	@Override
	@Transactional(readOnly=true)
	public List<SocietyWelfareDTO> getSocietyWelfares() {
		List<SocietyWelfare> pos = societyWelfareDao.findAllUndeleted("displaySeqNumber");
		List<SocietyWelfareDTO> dtos = new ArrayList<SocietyWelfareDTO>();
		
		if(CollectionUtils.isEmpty(pos)){
			return dtos;
		}
		
		for(SocietyWelfare po : pos){
			dtos.add(SocietyWelfareConvertor.toDto(po));
		}
		
		return dtos;
	}

}

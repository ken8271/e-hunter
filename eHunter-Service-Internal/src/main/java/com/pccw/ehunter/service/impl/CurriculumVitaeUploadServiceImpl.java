package com.pccw.ehunter.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pccw.ehunter.constant.IDNumberKeyConstant;
import com.pccw.ehunter.constant.TransactionIndicator;
import com.pccw.ehunter.convertor.UploadedCurriculumVitaeConvertor;
import com.pccw.ehunter.dao.CurriculumVitaeUploadDAO;
import com.pccw.ehunter.domain.internal.UploadedCurriculumVitae;
import com.pccw.ehunter.dto.UploadedCurriculumVitaeDTO;
import com.pccw.ehunter.helper.IDGenerator;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.CurriculumVitaeUploadService;
import com.pccw.ehunter.utility.BaseDtoUtility;

@Service("cvUploadService")
@Transactional
public class CurriculumVitaeUploadServiceImpl implements CurriculumVitaeUploadService{
	
	@Autowired
	private CurriculumVitaeUploadDAO cvUploadDao;
	
	private SimpleHibernateTemplate<UploadedCurriculumVitae, String> dao;
	
	@Autowired
	private IDGenerator idGenerator;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		dao = new SimpleHibernateTemplate<UploadedCurriculumVitae, String>(sessionFactory, UploadedCurriculumVitae.class);
	}

	@Override
	@Transactional
	public void saveUploadedCurriculumVitae(UploadedCurriculumVitaeDTO dto) {
		dto.setCvID(idGenerator.generateID(IDNumberKeyConstant.UPLOADED_CV_SEQUENCE_KEY, null, 9));
		BaseDtoUtility.setCommonProperties(dto, TransactionIndicator.INSERT);
		
		cvUploadDao.saveUploadedCurriculumVitae(UploadedCurriculumVitaeConvertor.toPo(dto));
	}

	@Override
	@Transactional(readOnly=true)
	public UploadedCurriculumVitaeDTO getUploadedCurriculumVitaeByID(String id) {
		return UploadedCurriculumVitaeConvertor.toDto(dao.findUniqueByProperty("cvID", id));
	}

	@Override
	@Transactional
	public void deleteCurriculumVitae(UploadedCurriculumVitaeDTO dto) {
		dao.delete(UploadedCurriculumVitaeConvertor.toPo(dto));
	}

}

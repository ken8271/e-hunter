package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.convertor.CustomerGradeConvertor;
import com.pccw.ehunter.domain.common.CustomerGrade;
import com.pccw.ehunter.dto.CustomerGradeDTO;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.CustomerGradeService;

@Service("customerGradeService")
@Transactional
public class CustomerGradeServiceImpl implements CustomerGradeService{
	
	private SimpleHibernateTemplate<CustomerGrade, String>  dao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		dao = new SimpleHibernateTemplate<CustomerGrade, String>(sessionFactory, CustomerGrade.class);
	}

	@Override
	public List<CustomerGradeDTO> getCustomerGrades() {
		List<CustomerGradeDTO> dtos = new ArrayList<CustomerGradeDTO>();
		List<CustomerGrade> pos = dao.findAllUndeleted("displaySeqNumber");
		
		if(!CollectionUtils.isEmpty(pos)){
			for(CustomerGrade po : pos){
				dtos.add(CustomerGradeConvertor.toDto(po));
			}
		}
		
		return dtos;
	}

}

package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.convertor.CustomerStatusConvertor;
import com.pccw.ehunter.domain.common.CustomerStatus;
import com.pccw.ehunter.dto.CustomerStatusDTO;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.CustomerStatusService;

@Service("customerStatusService")
@Transactional
public class CustomerStatusServiceImpl implements CustomerStatusService{
	
	private SimpleHibernateTemplate<CustomerStatus, String>  dao ;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		dao = new SimpleHibernateTemplate<CustomerStatus, String>(sessionFactory, CustomerStatus.class);
	}

	@Override
	public List<CustomerStatusDTO> getCustomerStatuses() {
		List<CustomerStatusDTO> dtos = new ArrayList<CustomerStatusDTO>();
		List<CustomerStatus> pos = dao.findAllUndeleted("displaySeqNumber");
		
		if(!CollectionUtils.isEmpty(pos)){
			for(CustomerStatus po : pos){
				dtos.add(CustomerStatusConvertor.toDto(po));
			}
		}
		
		return dtos;
	}

}

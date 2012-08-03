package com.pccw.ehunter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.ehunter.convertor.CustomerGroupConvertor;
import com.pccw.ehunter.dao.CustomerRegistrationDAO;
import com.pccw.ehunter.dto.CustomerGroupDTO;
import com.pccw.ehunter.service.CustomerRegistrationService;

@Service("custRegtService")
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService{
	
	@Autowired
	private CustomerRegistrationDAO custRegtDao;
	
	@Override
	public CustomerGroupDTO loadCustGroupByID(String systemGroupRefNum) {
		return CustomerGroupConvertor.toDto(custRegtDao.loadCustGroupByID(systemGroupRefNum));
	}

	@Override
	public List<CustomerGroupDTO> loadCustGroups() {
		return CustomerGroupConvertor.toDtos(custRegtDao.loadCustGroups());
	}
}

package com.pccw.ehunter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pccw.ehunter.convertor.CustomerGroupConvertor;
import com.pccw.ehunter.dao.CustomerRegistrationDAO;
import com.pccw.ehunter.dto.CustomerGroupDTO;
import com.pccw.ehunter.service.CustomerRegistrationService;

@Service("custRegtService")
@Transactional
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService{
	
	@Autowired
	private CustomerRegistrationDAO custRegtDao;
	
	@Override
	@Transactional(readOnly=true)
	public CustomerGroupDTO loadCustGroupByID(String systemGroupRefNum) {
		return CustomerGroupConvertor.toDto(custRegtDao.loadCustGroupByID(systemGroupRefNum));
	}

	@Override
	@Transactional(readOnly=true)
	public List<CustomerGroupDTO> loadCustGroups() {
		return CustomerGroupConvertor.toDtos(custRegtDao.loadCustGroups());
	}

	@Override
	public int getCountOfGroupsByFullName(String fullName) {
		return custRegtDao.countGroupsByFullName(fullName);
	}
}

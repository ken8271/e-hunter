package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.dao.CustomerCommonDAO;
import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.dto.CustomerPagedCriteria;
import com.pccw.ehunter.service.CustomerCommonService;
import com.pccw.ehunter.utility.StringUtils;

@Service("custCommonService")
@Transactional
public class CustomerCommonServiceImpl implements CustomerCommonService{
	
	@Autowired
	private CustomerCommonDAO customerDao;

	@Override
	@Transactional(readOnly=true)
	public int getCustomersCountByCriteria(CustomerPagedCriteria pagedCriteria) {
		return customerDao.getCustomersCountByCriteria(pagedCriteria);
	}

	@Override
	@Transactional(readOnly=true)
	public List<CustomerDTO> getCustomersByCriteria(CustomerPagedCriteria pagedCriteria) {
		List<Object> list = customerDao.getCustomersByCriteria(pagedCriteria);
		List<CustomerDTO> customers = new ArrayList<CustomerDTO>();
		
		if(!CollectionUtils.isEmpty(list)){
			CustomerDTO dto = null;
			for(Object o : list){
				dto = new CustomerDTO();
				Object[] objs = (Object[])o;
				dto.setSystemCustRefNum(StringUtils.isEmpty((String)objs[0]) ? "" : (String)objs[0]);
				dto.setFullName(StringUtils.isEmpty((String)objs[1]) ? "" : (String)objs[1]);
				dto.setShortName(StringUtils.isEmpty((String)objs[2]) ? "" : (String)objs[2]);
				dto.setGrade(StringUtils.isEmpty((String)objs[3]) ? "" : (String)objs[3]);
				dto.setStatus(StringUtils.isEmpty((String)objs[4]) ? "" : (String)objs[4]);
				
				customers.add(dto);
			}
		}
		
		return customers;
	}

}

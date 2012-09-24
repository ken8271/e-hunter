package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.convertor.CustomerConvertor;
import com.pccw.ehunter.convertor.MobilePhoneConvertor;
import com.pccw.ehunter.dao.CustomerCommonDAO;
import com.pccw.ehunter.domain.internal.CustomerCompany;
import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.dto.CustomerPagedCriteria;
import com.pccw.ehunter.dto.CustomerResponsePersonDTO;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.CustomerCommonService;
import com.pccw.ehunter.utility.StringUtils;

@Service("custCommonService")
@Transactional
public class CustomerCommonServiceImpl implements CustomerCommonService{
	
	@Autowired
	private CustomerCommonDAO customerDao;
	
	private SimpleHibernateTemplate<CustomerCompany, String> dao;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		dao = new SimpleHibernateTemplate<CustomerCompany, String>(sessionFactory, CustomerCompany.class);
	}

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

	@Override
	@Transactional(readOnly=true)
	public CustomerDTO getCustomerByID(String customerId) {
		return CustomerConvertor.toDto(dao.findUniqueByProperty("systemCustRefNum", customerId));
	}

	@Override
	public List<CustomerResponsePersonDTO> getResponsePersonsByCustomerID(String customerID) {
		List<CustomerResponsePersonDTO> dtos = new ArrayList<CustomerResponsePersonDTO>();
		List<Object> list = customerDao.getCustomerRespPersonsByCustID(customerID);
		
		if(!CollectionUtils.isEmpty(list)){
			CustomerResponsePersonDTO dto = null;
			for(Object o : list){
				dto = new CustomerResponsePersonDTO();
				Object[] os = (Object[])o;
				//cr.RP_NM , cr.RP_TEL , cr.RP_EMAIL , cr.RP_STAT , pt.DISP_NM AS TOP_POST , ps.DISP_NM AS SUB_POST , cr.RP_POST
				dto.setSystemRespRefNum(StringUtils.isEmpty((String)os[0]) ? "" : ((String)os[0]));
				dto.setName(StringUtils.isEmpty((String)os[1]) ? "" : ((String)os[1]));
				dto.setTelephoneDto(MobilePhoneConvertor.toDto(StringUtils.isEmpty((String)os[2]) ? "" : ((String)os[2])));
				dto.setEmail(StringUtils.isEmpty((String)os[3]) ? "" : ((String)os[3]));
				dto.setStatus(StringUtils.isEmpty((String)os[4]) ? "" : ((String)os[4]));
				
				dtos.add(dto);
			}
		}
		
		return dtos;
	}

}

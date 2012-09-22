package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.constant.CustomerIndicator;
import com.pccw.ehunter.convertor.MobilePhoneConvertor;
import com.pccw.ehunter.convertor.TelePhoneConvertor;
import com.pccw.ehunter.dao.CustomerCommonDAO;
import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.dto.CustomerGroupDTO;
import com.pccw.ehunter.dto.CustomerPagedCriteria;
import com.pccw.ehunter.dto.CustomerResponsePersonDTO;
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

	@Override
	@Transactional(readOnly=true)
	public CustomerDTO getCustomerByID(String customerId) {
		Object cust = customerDao.getCustomerByID(customerId);
		
		if(null == cust){
			return new CustomerDTO();
		}
		
		CustomerDTO dto = toCustomerDto(cust);
		
		if(!CustomerIndicator.CUSTOMER_INDEPENDENT_COMPANY.equals(dto.getGroupIndicator())){
			dto.setCustGroup(toGroupDto(customerDao.getCustomerGroupByID(dto.getCustGroup().getSystemGroupRefNum())));
		}
		
		dto.setCustRespPerson(toRespPersonDto(customerDao.getCustomerRespPersonsByCustID(customerId)));
		
		return dto;
	}

	private CustomerResponsePersonDTO toRespPersonDto(List<Object> list) {
		CustomerResponsePersonDTO dto = new CustomerResponsePersonDTO();
		
		if(CollectionUtils.isEmpty(list)){
			return dto;
		}
		
		Object o = list.get(0);
		
		if(null == o){
			return dto;
		}
		
		Object[] objs = (Object[])o;

		dto.setName(StringUtils.isEmpty((String)objs[0]) ? "" : ((String)objs[0]));
		dto.setTelephoneDto(MobilePhoneConvertor.toDto(StringUtils.isEmpty((String)objs[1]) ? "" : ((String)objs[1])));
		dto.setEmail(StringUtils.isEmpty((String)objs[2]) ? "" : ((String)objs[2]));
		if(CommonConstant.STATUS_IN_SERVICE.equals(StringUtils.isEmpty((String)objs[3]) ? "" : ((String)objs[3]))){
			dto.setStatus(CommonConstant.STATUS_IN_SERVICE_DESC);
		}else if(CommonConstant.STATUS_OUT_SERVICE.equals(StringUtils.isEmpty((String)objs[3]) ? "" : ((String)objs[3]))){
			dto.setStatus(CommonConstant.STATUS_OUT_SERVICE_DESC);
		}
		dto.setPositionType((StringUtils.isEmpty((String)objs[4]) ? "" : ((String)objs[4])) + " / " + (StringUtils.isEmpty((String)objs[5]) ? "" : ((String)objs[5])));
		dto.setPositionName(StringUtils.isEmpty((String)objs[6]) ? "" : ((String)objs[6]));
		
		return dto;
	}

	private CustomerGroupDTO toGroupDto(Object obj) {
		CustomerGroupDTO dto = new CustomerGroupDTO();
		
		if(null == obj){
			return dto;
		}
		
		Object[] objs = (Object[])obj;
		dto.setSystemGroupRefNum(StringUtils.isEmpty((String)objs[0]) ? "" : ((String)objs[0]));
		dto.setFullName(StringUtils.isEmpty((String)objs[1]) ? "" : ((String)objs[1]));
		dto.setShortName(StringUtils.isEmpty((String)objs[2]) ? "" : ((String)objs[2]));
		
		return dto;
	}

	private CustomerDTO toCustomerDto(Object obj) {
		CustomerDTO dto = new CustomerDTO();
		Object[] objs = (Object[])obj;
		
		dto.setSystemCustRefNum(StringUtils.isEmpty((String)objs[0]) ? "" : ((String)objs[0]));
		dto.setFullName(StringUtils.isEmpty((String)objs[1]) ? "" : ((String)objs[1]));
		dto.setShortName(StringUtils.isEmpty((String)objs[2]) ? "" : ((String)objs[2]));
		dto.setOffcialSite(StringUtils.isEmpty((String)objs[3]) ? "" : ((String)objs[3]));
		dto.setTelExchangeDto(TelePhoneConvertor.toDto(StringUtils.isEmpty((String)objs[4]) ? "" : ((String)objs[4])));
		dto.setGroupIndicator(StringUtils.isEmpty((String)objs[5]) ? "" : ((String)objs[5]));
		CustomerGroupDTO group = new CustomerGroupDTO();
		group.setSystemGroupRefNum(StringUtils.isEmpty((String)objs[6]) ? "" : ((String)objs[6]));
		dto.setCustGroup(group);
		dto.setType(StringUtils.isEmpty((String)objs[7]) ? "" : ((String)objs[7]));
		dto.setSize(StringUtils.isEmpty((String)objs[8]) ? "" : ((String)objs[8]));
		dto.setGrade(StringUtils.isEmpty((String)objs[9]) ? "" : ((String)objs[9]));
		dto.setStatus(StringUtils.isEmpty((String)objs[10]) ? "" : ((String)objs[10]));
		
		return dto;
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

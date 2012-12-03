package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.constant.DateFormatConstant;
import com.pccw.ehunter.constant.IDNumberKeyConstant;
import com.pccw.ehunter.constant.TransactionIndicator;
import com.pccw.ehunter.convertor.CustomerContactHistoryConvertor;
import com.pccw.ehunter.dao.CustomerContactHistoryDAO;
import com.pccw.ehunter.dto.CustomerContactHistoryDTO;
import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.dto.CustomerResponsePersonDTO;
import com.pccw.ehunter.dto.InternalUserDTO;
import com.pccw.ehunter.helper.IDGenerator;
import com.pccw.ehunter.service.CustomerContactHistoryService;
import com.pccw.ehunter.utility.BaseDtoUtility;
import com.pccw.ehunter.utility.DateUtils;
import com.pccw.ehunter.utility.StringUtils;

@Service("customerContactHistoryService")
@Transactional
public class CustomerContactHistoryServiceImpl implements CustomerContactHistoryService{
	
	@Autowired
	private CustomerContactHistoryDAO customerContactHistoryDao;
	
	@Autowired
	private IDGenerator idGenerator;

	@Override
	@Transactional
	public void saveContactHistory(CustomerContactHistoryDTO dto) {
		if(dto == null) return ;
		
		dto.setSystemContactRefNum(idGenerator.generateID(IDNumberKeyConstant.CUSTOMER_CONTACT_SEQUENCE_KEY, DateUtils.formatDateTime(DateFormatConstant.DATE_YYMMDD, new Date()), 9 , true));
		BaseDtoUtility.setCommonProperties(dto, TransactionIndicator.INSERT);
		
		customerContactHistoryDao.saveContactHistory(CustomerContactHistoryConvertor.toPo(dto));
	}

	@Override
	@Transactional(readOnly=true)
	public List<CustomerContactHistoryDTO> getContactHistories(String customerID) {
		List<CustomerContactHistoryDTO> dtos = new ArrayList<CustomerContactHistoryDTO>();
		List<Object> list = customerContactHistoryDao.getContactHistories(customerID);
		
		if(!CollectionUtils.isEmpty(list)){
			CustomerContactHistoryDTO dto = null;
			for(Object o : list){
				dto = new CustomerContactHistoryDTO();
				Object[] os = (Object[])o;
				
				dto.setSystemContactRefNum(StringUtils.isEmpty((String)os[0]) ? "" : (String)os[0]);
				
				CustomerResponsePersonDTO rp = new CustomerResponsePersonDTO();
				rp.setSystemRespRefNum(StringUtils.isEmpty((String)os[1]) ? "" : (String)os[1]);
				rp.setName(StringUtils.isEmpty((String)os[2]) ? "" : (String)os[2]);
				dto.setResponsePersonDto(rp);
				
				InternalUserDTO user = new InternalUserDTO();
				user.setUserRecId(StringUtils.isEmpty((String)os[3]) ? "" : (String)os[3]);
				user.setCnName(StringUtils.isEmpty((String)os[4]) ? "" : (String)os[4]);
				dto.setAdviserDto(user);
				
				dto.setCreateDateTime(os[5]==null ? (new Date()) : ((Date)os[5]));
				
				dtos.add(dto);
			}
		}
		
		return dtos;
	}

	@Override
	@Transactional(readOnly=true)
	public CustomerContactHistoryDTO getContactHistoryByID(String id) {
		CustomerContactHistoryDTO dto = new CustomerContactHistoryDTO();
		dto.setCustomerDto(new CustomerDTO());
		dto.setResponsePersonDto(new CustomerResponsePersonDTO());
		dto.setAdviserDto(new InternalUserDTO());
		
		Object o = customerContactHistoryDao.getContactHistoryByID(id);
		
		if(o != null){
			Object[] os = (Object[])o;
			
			dto.setSystemContactRefNum(StringUtils.isEmpty((String)os[0]) ? "" : (String)os[0]);
			dto.getCustomerDto().setSystemCustRefNum(StringUtils.isEmpty((String)os[1]) ? "" : (String)os[1]);
			dto.getCustomerDto().setFullName(StringUtils.isEmpty((String)os[2]) ? "" : (String)os[2]);
			dto.getCustomerDto().setShortName(StringUtils.isEmpty((String)os[3]) ? "" : (String)os[3]);
			dto.getResponsePersonDto().setSystemRespRefNum(StringUtils.isEmpty((String)os[4]) ? "" : (String)os[4]);
			dto.getResponsePersonDto().setName(StringUtils.isEmpty((String)os[5]) ? "" : (String)os[5]);
			dto.getAdviserDto().setUserRecId(StringUtils.isEmpty((String)os[6]) ? "" : (String)os[6]);
			dto.getAdviserDto().setCnName(StringUtils.isEmpty((String)os[7]) ? "" : (String)os[7]);
			dto.setCreateDateTime(os[8]==null ? (new Date()) : ((Date)os[8]));
			dto.setContent(StringUtils.isEmpty((String)os[9]) ? "" : (String)os[9]);
			dto.setRemark(StringUtils.isEmpty((String)os[10]) ? "" : (String)os[10]);
		}
		
		return dto;
	}

}

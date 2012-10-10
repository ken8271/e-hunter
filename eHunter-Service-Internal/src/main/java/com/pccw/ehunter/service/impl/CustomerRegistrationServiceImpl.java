package com.pccw.ehunter.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.constant.CustomerIndicator;
import com.pccw.ehunter.constant.DateFormatConstant;
import com.pccw.ehunter.constant.IDNumberKeyConstant;
import com.pccw.ehunter.constant.TransactionIndicator;
import com.pccw.ehunter.convertor.CustomerConvertor;
import com.pccw.ehunter.convertor.CustomerGroupConvertor;
import com.pccw.ehunter.dao.CustomerRegistrationDAO;
import com.pccw.ehunter.domain.internal.CustomerCompany;
import com.pccw.ehunter.domain.internal.CustomerResponsablePerson;
import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.dto.CustomerGroupDTO;
import com.pccw.ehunter.dto.CustomerResponsePersonDTO;
import com.pccw.ehunter.helper.IDGeneratorImpl;
import com.pccw.ehunter.service.CustomerRegistrationService;
import com.pccw.ehunter.utility.BaseEntityUtility;
import com.pccw.ehunter.utility.DateUtils;
import com.pccw.ehunter.utility.StringUtils;

@Service("custRegtService")
@Transactional
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService{
	
	@Autowired
	private CustomerRegistrationDAO custRegtDao;
	
	@Autowired
	private IDGeneratorImpl idGenerator;

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

	@Override
	@Transactional
	public void completeCustRegistration(CustomerDTO customerDto) {
		
		if(customerDto != null){
			customerDto.setSystemCustRefNum(idGenerator.generateID(IDNumberKeyConstant.CUSTOMER_SEQUENCE_KEY, CommonConstant.PREFIX_CUSTOMER_ID , 6));

			if(customerDto.getCustGroup() != null ){
				if(CustomerIndicator.CUSTOMER_GROUP.equals(customerDto.getGroupIndicator())){
					customerDto.getCustGroup().setSystemGroupRefNum(idGenerator.generateID(IDNumberKeyConstant.CUSTOMER_GROUP_SEQUENCE_KEY , DateUtils.formatDateTime(DateFormatConstant.DATE_YYMMDD, new Date()) , 9));					
				}else if(CustomerIndicator.CUSTOMER_SUBSIDIARY.equals(customerDto.getGroupIndicator())){
					customerDto.getCustGroup().setSystemGroupRefNum(null);		
				}
			}
			
			if(!CollectionUtils.isEmpty(customerDto.getMultiResponsePerson())){
				for(CustomerResponsePersonDTO rpDto : customerDto.getMultiResponsePerson()){
					rpDto.setSystemRespRefNum(idGenerator.generateID(IDNumberKeyConstant.CUSTOMER_RESP_PERSON_SEQUENCE_KEY , DateUtils.formatDateTime(DateFormatConstant.DATE_YYMMDD, new Date()) , 9));
				}
			}
		}
		
		CustomerCompany po = CustomerConvertor.toPo(customerDto);
		
		if(po != null){
			BaseEntityUtility.setCommonProperties(po, TransactionIndicator.INSERT);
			
			if(po.getGroup() != null && CustomerIndicator.CUSTOMER_GROUP.equals(po.getGroupIndicator())){
				BaseEntityUtility.setCommonProperties(po.getGroup(), TransactionIndicator.INSERT);
			}
			
			if(!CollectionUtils.isEmpty(po.getCustRespPersons())){
				for(CustomerResponsablePerson rp : po.getCustRespPersons()){
					BaseEntityUtility.setCommonProperties(rp, TransactionIndicator.INSERT);
				}
			}
		}
		
		custRegtDao.saveCustomerCompany(po);
	}
	
	@Transactional
	public void updateSubsidiaryInfo(String systemGroupRefNum , String systemCustRefNum){
		if(!StringUtils.isEmpty(systemGroupRefNum)){
			custRegtDao.updateCustomerByProperty("SYS_REF_GP", systemGroupRefNum, systemCustRefNum);
		}
	}

	@Override
	@Transactional
	public void updateCustomerInfo(CustomerDTO customerDto) {
		
		CustomerCompany po = CustomerConvertor.toPo(customerDto);
		
		if(po != null){
			BaseEntityUtility.setCommonProperties(po, TransactionIndicator.UPDATE);
			
			if(po.getGroup() != null && CustomerIndicator.CUSTOMER_GROUP.equals(po.getGroupIndicator())){
				BaseEntityUtility.setCommonProperties(po.getGroup(), TransactionIndicator.UPDATE);
			}
			
			if(!CollectionUtils.isEmpty(po.getCustRespPersons())){
				for(CustomerResponsablePerson rp : po.getCustRespPersons()){
					BaseEntityUtility.setCommonProperties(rp, TransactionIndicator.UPDATE);
				}
			}
		}
		
		custRegtDao.updateCustomer(po);
	}
}

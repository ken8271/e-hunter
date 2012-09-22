package com.pccw.ehunter.convertor;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.internal.CustomerCompany;
import com.pccw.ehunter.domain.internal.CustomerContactHistory;
import com.pccw.ehunter.domain.internal.CustomerResponsablePerson;
import com.pccw.ehunter.domain.internal.InternalUser;
import com.pccw.ehunter.dto.CustomerContactHistoryDTO;

public class CustomerContactHistoryConvertor {

	public static CustomerContactHistory toPo(CustomerContactHistoryDTO dto){
		if(dto == null) return null;
		
		CustomerContactHistory po = new CustomerContactHistory();
		BeanUtils.copyProperties(dto, po);
		
		CustomerCompany cc = new CustomerCompany();
		cc.setSystemCustRefNum(dto.getCustomerDto().getSystemCustRefNum());
		po.setCustomer(cc);
		
		CustomerResponsablePerson rp = new CustomerResponsablePerson();
		rp.setSystemRespRefNum(dto.getResponsePersonDto().getSystemRespRefNum());
		po.setResponsePerson(rp);
		
		InternalUser iu = new InternalUser();
		iu.setUserRecId(dto.getAdviserDto().getUserRecId());
		po.setAdviser(iu);
		
		return po;
	}
}

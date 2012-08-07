package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.domain.internal.CustomerCompany;
import com.pccw.ehunter.domain.internal.CustomerResponsablePerson;
import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.dto.CustomerEnquireDTO;
import com.pccw.ehunter.dto.CustomerPagedCriteria;
import com.pccw.ehunter.dto.TelephoneDTO;
import com.pccw.ehunter.utility.StringUtils;

public class CustomerConvertor {
	
	public static final String HYPHEN = "-";
	
	public static CustomerDTO toDto(CustomerCompany po){
		if(null == po){
			return null;
		}
		
		CustomerDTO dto = new CustomerDTO();
		
		BeanUtils.copyProperties(po, dto);
		
		TelephoneDTO tel = new TelephoneDTO();
		if(StringUtils.isEmpty(po.getTelExchange()) || po.getTelExchange().indexOf(HYPHEN) == -1){
			tel.setRegionCode("");
			tel.setPhoneNumber("");
		}else {
			String[] numbers = po.getTelExchange().split(HYPHEN);
			tel.setRegionCode(numbers[0]);
			tel.setPhoneNumber(numbers[1]);
		}
		
		dto.setTelExchangeDto(tel);
		dto.setCustGroup(CustomerGroupConvertor.toDto(po.getGroup()));
		
		if(!CollectionUtils.isEmpty(po.getCustRespPersons())){
			dto.setCustRespPerson(CustomerResponsablePersonConvertor.toDto(po.getCustRespPersons().get(0)));
		}
		
		return dto;
	}
	
	public static CustomerCompany toPo(CustomerDTO dto){
		if(null == dto){
			return null;
		}
		
		CustomerCompany po = new CustomerCompany();
		
		BeanUtils.copyProperties(dto, po);
		
		po.setTelExchange(dto.getTelExchangeDto().toString());
		po.setGroup(CustomerGroupConvertor.toPo(dto.getCustGroup()));
		
		List<CustomerResponsablePerson> rps = new ArrayList<CustomerResponsablePerson>();
		if(null != dto.getCustRespPerson()){
			rps.add(CustomerResponsablePersonConvertor.toPo(dto.getCustRespPerson()));
		}
		po.setCustRespPersons(rps);
		
		return po;
	}
	
	public static List<CustomerDTO> toDtos(List<CustomerCompany> pos){
		List<CustomerDTO> dtos = new ArrayList<CustomerDTO>();
		
		if(CollectionUtils.isEmpty(pos)){
			return dtos;
		}
		
		for(CustomerCompany po : pos){
			CustomerDTO dto = toDto(po);
			if(null != dto){
				dtos.add(dto);
			}
		}
		
		return dtos;
	}
	
	public static List<CustomerCompany> toPos(List<CustomerDTO> dtos){
		List<CustomerCompany> pos = new ArrayList<CustomerCompany>();
		
		if(CollectionUtils.isEmpty(dtos)){
			return pos;
		}
		
		for(CustomerDTO dto : dtos){
			CustomerCompany po = toPo(dto);
			if(null != po){
				pos.add(po);
			}
		}
		return pos;
	}
	
	public static CustomerPagedCriteria toPagedCriteria(CustomerEnquireDTO enquireDto){
		CustomerPagedCriteria pagedCriteria = new CustomerPagedCriteria();
		
		if(null == enquireDto){
			return pagedCriteria;
		}
		
		BeanUtils.copyProperties(enquireDto, pagedCriteria);
		
		if(enquireDto.getJmesaDto() != null && enquireDto.getJmesaDto().getRowSelect() != null){
			pagedCriteria.getPageFilter().setRowStart(enquireDto.getJmesaDto().getRowSelect().getRowStart());
			pagedCriteria.getPageFilter().setRowEnd(enquireDto.getJmesaDto().getRowSelect().getRowEnd());
		}
		
		return pagedCriteria;
	}
}

package com.pccw.ehunter.convertor;

import com.pccw.ehunter.dto.MobilePhoneDTO;
import com.pccw.ehunter.utility.StringUtils;

public class MobilePhoneConvertor {
	
	public static MobilePhoneDTO toDto(String number){
		MobilePhoneDTO dto = new MobilePhoneDTO();
		
		if(StringUtils.isEmpty(number)){
			return dto;
		}
		
		dto.setPhoneNumber(number);
		
		return dto;
	}
}

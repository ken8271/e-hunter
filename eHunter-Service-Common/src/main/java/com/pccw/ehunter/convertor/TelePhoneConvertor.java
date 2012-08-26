package com.pccw.ehunter.convertor;

import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.dto.TelephoneDTO;
import com.pccw.ehunter.utility.StringUtils;

public class TelePhoneConvertor {
	
	public static TelephoneDTO toDto(String phone){
		TelephoneDTO dto = new TelephoneDTO();
		
		if(StringUtils.isEmpty(phone) || phone.indexOf(CommonConstant.HYPHEN) == -1){
			return dto;
		}
		
		String[] nbrs = phone.split(CommonConstant.HYPHEN);
		dto.setRegionCode(nbrs[0]);
		dto.setPhoneNumber(nbrs[1]);
		
		return dto;
	}
	
	public static String toString(TelephoneDTO dto){
		if(dto == null) return null;
		
		if(StringUtils.isEmpty(dto.getRegionCode()) || StringUtils.isEmpty(dto.getPhoneNumber())){
			return null;
		}
		
		return dto.toString();
	}
}

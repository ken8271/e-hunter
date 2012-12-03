package com.pccw.ehunter.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pccw.ehunter.service.IDGeneratorService;
import com.pccw.ehunter.utility.StringUtils;

@Component("idGenerator")
public class IDGeneratorImpl implements IDGenerator{
	
	@Autowired
	private IDGeneratorService idGeneratorService;

	@Override
	public String generateID(String key , boolean cycle) {
		return generateID(key, "", StringUtils.isEmpty(key) ? 0 : key.length() , false);
	}

	@Override
	public String generateID(String key, String prefix, int length , boolean cycle) {
		int padding = 0;
		String value = String.valueOf(idGeneratorService.getNextValue(key, false , cycle));

		padding = length - (StringUtils.isEmpty(prefix) ? 0 : prefix.length()) - (StringUtils.isEmpty(value) ? 0 : value.length());
		
		if(padding > 0){
			value = StringUtils.addLeadingZero(value, padding);
		}
		
		if(StringUtils.isEmpty(prefix)){
			return value;
		}
		
		return prefix + value;
	}

}

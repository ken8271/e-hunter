package com.pccw.ehunter.convertor;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.domain.internal.PositionKeyWord;

public class PositionKeyWordConvertor {
	
	public static String toString(PositionKeyWord po){
		if(po == null) return null;
		
		return po.getContent();
	}
	
	public static String[] toStrings(List<PositionKeyWord> pos){
		if(CollectionUtils.isEmpty(pos)){
			return new String[5];
		}
		
		String[] keyWords = new String[5];
		for(int i=0 ; i<pos.size() ; i++){
			keyWords[i] = toString(pos.get(i));
		}
		
		return keyWords;
	}
}

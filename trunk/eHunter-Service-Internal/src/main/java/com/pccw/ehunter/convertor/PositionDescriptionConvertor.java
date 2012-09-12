package com.pccw.ehunter.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.pccw.ehunter.domain.internal.PositionDescription;
import com.pccw.ehunter.domain.internal.PositionKeyWord;
import com.pccw.ehunter.domain.internal.PositionKeyWordPK;
import com.pccw.ehunter.dto.PositionDescriptionDTO;
import com.pccw.ehunter.utility.BaseEntityUtility;
import com.pccw.ehunter.utility.StringUtils;

public class PositionDescriptionConvertor {
	
	public static PositionDescription toPo(PositionDescriptionDTO dto , String transactionIndicator){
		if(dto == null) return null;
		
		PositionDescription po = new PositionDescription();
		BeanUtils.copyProperties(dto, po);
		
		po.setExpiryDate(SimpleDateConvertor.toDate(dto.getExpiryDateDto()));
		po.setSalaryFrom(Integer.valueOf(dto.getSalaryFromStr()));
		po.setSalaryTo(Integer.valueOf(dto.getSalaryToStr()));
		
		List<PositionKeyWord> kws = new ArrayList<PositionKeyWord>();
		if(!StringUtils.isEmpty(dto.getKeyWords())){
			String[] keyWords = dto.getKeyWords();
			for(int i=0 ; i<keyWords.length ; i++){
				PositionKeyWordPK pk = new PositionKeyWordPK();
				pk.setPostDesc(po);
				pk.setItemNumber(i);
				
				PositionKeyWord kw = new PositionKeyWord();
				kw.setPk(pk);
				kw.setContent(keyWords[i]);
				
				BaseEntityUtility.setCommonProperties(kw, transactionIndicator);
				
				kws.add(kw);
			}
		}
		po.setKeyWords(kws);
		
		return po;
	}
}

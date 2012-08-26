package com.pccw.ehunter.utility;

import java.util.Date;

import com.pccw.ehunter.constant.TransactionIndicator;
import com.pccw.ehunter.dto.BaseDTO;

public class BaseDtoUtility {
	
	public static void setCommonProperties (BaseDTO baseDto) {
		if (baseDto == null) return;
		
		baseDto.setCreateBy(SecurityUtils.getUserRecId());
		baseDto.setCreateDateTime(new Date());
		baseDto.setLastUpdateBy(SecurityUtils.getUserRecId());
		baseDto.setLastUpdateDateTime(new Date());
		baseDto.setLastTransactionIndicator(TransactionIndicator.INSERT );
	}
	
	public static void updateCommonProperties (BaseDTO baseDto, String lastTransactionIndicator) {
		if (baseDto == null) return;		

		baseDto.setLastUpdateBy(SecurityUtils.getUserRecId());
		baseDto.setLastUpdateDateTime(new Date());
		baseDto.setLastTransactionIndicator( lastTransactionIndicator );
	}
	
	public static void setCommonProperties (BaseDTO baseDto, String indicator) {
		
		if ( StringUtils.isEmpty(baseDto.getCreateBy()) ) {
			BaseDtoUtility.setCommonProperties( baseDto );
			return;
		}
		
		if ( TransactionIndicator.INSERT.equals(indicator) ) {
			BaseDtoUtility.setCommonProperties( baseDto );
			return;
		}
		
		if ( TransactionIndicator.UPDATE.equals(indicator) ) {
			BaseDtoUtility.updateCommonProperties( baseDto, TransactionIndicator.UPDATE );
			return;
		} 
		
		if ( TransactionIndicator.DELETE.equals(indicator) ) {
			BaseDtoUtility.updateCommonProperties( baseDto, TransactionIndicator.DELETE );
			return;
		}
		
		BaseDtoUtility.updateCommonProperties( baseDto, baseDto.getLastTransactionIndicator() );
	}
}

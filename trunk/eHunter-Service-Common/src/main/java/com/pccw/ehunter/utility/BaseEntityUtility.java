package com.pccw.ehunter.utility;

import java.util.Date;

import com.pccw.ehunter.constant.TransactionIndicator;
import com.pccw.ehunter.domain.BaseEntity;

public class BaseEntityUtility {
	
	public static void setCommonProperties (BaseEntity baseEntity) {
		if (baseEntity == null) return;
		
		baseEntity.setCreateBy(SecurityUtils.getUserRecId());
		baseEntity.setCreateDateTime(new Date());
		baseEntity.setLastUpdateBy(SecurityUtils.getUserRecId());
		baseEntity.setLastUpdateDateTime(new Date());
		baseEntity.setLastTransactionIndicator(TransactionIndicator.INSERT );
	}
	
	public static void updateCommonProperties (BaseEntity baseEntity, String lastTransactionIndicator) {
		if (baseEntity == null) return;		

		baseEntity.setLastUpdateBy(SecurityUtils.getUserRecId());
		baseEntity.setLastUpdateDateTime(new Date());
		baseEntity.setLastTransactionIndicator( lastTransactionIndicator );
	}
	
	public static void setCommonProperties (BaseEntity baseEntity, String indicator) {
		
		if ( StringUtils.isEmpty(baseEntity.getCreateBy()) ) {
			BaseEntityUtility.setCommonProperties( baseEntity );
			return;
		}
		
		if ( TransactionIndicator.INSERT.equals(indicator) ) {
			BaseEntityUtility.setCommonProperties( baseEntity );
			return;
		}
		
		if ( TransactionIndicator.UPDATE.equals(indicator) ) {
			BaseEntityUtility.updateCommonProperties( baseEntity, TransactionIndicator.UPDATE );
			return;
		} 
		
		if ( TransactionIndicator.DELETE.equals(indicator) ) {
			BaseEntityUtility.updateCommonProperties( baseEntity, TransactionIndicator.DELETE );
			return;
		}
		
		BaseEntityUtility.updateCommonProperties( baseEntity, baseEntity.getLastTransactionIndicator() );
	}
}

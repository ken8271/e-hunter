package com.pccw.ehunter.dao.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.pccw.ehunter.dao.DataBackupHistoryDAO;
import com.pccw.ehunter.dto.PagedCriteria;

@Component("dataBackupHistoryDao")
public class HibernateDataBackupHistoryDAO  implements DataBackupHistoryDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public int getBackupHistoryCountByCriteria(PagedCriteria pagedCriteria) {
		BigInteger count = (BigInteger)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT COUNT(*) ");
				buffer.append(" FROM T_BCUP_HST ");
				
				Query query = session.createSQLQuery(buffer.toString());
				
				return query.uniqueResult();
			}
		});
		
		return count.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getBackupHistoriesByCriteria(final PagedCriteria pagedCriteria) {
		List<Object> list = (List<Object>)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT hst.BCUP_HST_ID , hst.BCUP_NM , hst.BCUP_TM , hst.BCUP_CHNL , usr.CNM ");
				buffer.append(" FROM T_BCUP_HST hst LEFT JOIN T_INT_USR usr ON hst.BCUP_BY = usr.USR_REC_ID ");
				buffer.append(" ORDER BY hst.BCUP_TM DESC ");
				
				Query query = session.createSQLQuery(buffer.toString());
				
				if(pagedCriteria.getPageFilter().getRowEnd() > 0){
					query.setFirstResult(pagedCriteria.getPageFilter().getRowStart());
					query.setMaxResults(pagedCriteria.getPageFilter().getRowEnd()-pagedCriteria.getPageFilter().getRowStart());
				}
				
				return query.list();
			}
		});
		
		return list;
	}

}

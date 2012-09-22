package com.pccw.ehunter.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.pccw.ehunter.dao.CustomerContactHistoryDAO;
import com.pccw.ehunter.domain.internal.CustomerContactHistory;

@Component("customerContactHistoryDao")
public class HibernateCustomerContactHistoryDAO implements CustomerContactHistoryDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void saveContactHistory(CustomerContactHistory po) {
		hibernateTemplate.save(po);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getContactHistories(final String customerID) {
		List<Object> list = (List<Object>)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT cch.SYS_REF_CONT_HST , rp.SYS_REF_RP , rp.RP_NM , usr.USR_REC_ID , usr.CNM , cch.CR_DTTM ");
				buffer.append(" FROM T_CUST_CONT_HST cch , T_CUST_RP rp , T_INT_USR usr , T_CUST_CO cc ");
				buffer.append(" WHERE 1 = 1 ");
				buffer.append(" AND cch.SYS_REF_CUST =  cc.SYS_REF_CUST ");
				buffer.append(" AND cch.SYS_REF_RP = rp.SYS_REF_RP ");
				buffer.append(" AND cch.CONT_ADVSR = usr.USR_REC_ID ");
				buffer.append(" AND cch.SYS_REF_CUST = :customerID ");
				
				Query query = session.createSQLQuery(buffer.toString());
				query.setString("customerID", customerID);
				
				return query.list();
			}
		});
		
		return list;
	}

	@Override
	public Object getContactHistoryByID(final String id) {
		Object o = hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				
				buffer.append(" SELECT cch.SYS_REF_CONT_HST , cc.SYS_REF_CUST , cc.CO_NM , cc.CO_SHRT_NM , rp.SYS_REF_RP , rp.RP_NM , usr.USR_REC_ID , usr.CNM , cch.CR_DTTM , cch.CONT_REC , cch.CONT_RMRK ");
				buffer.append(" FROM T_CUST_CONT_HST cch , T_CUST_RP rp , T_INT_USR usr , T_CUST_CO cc ");
				buffer.append(" WHERE 1 = 1 ");
				buffer.append(" AND cch.SYS_REF_CUST =  cc.SYS_REF_CUST ");
				buffer.append(" AND cch.SYS_REF_RP = rp.SYS_REF_RP ");
				buffer.append(" AND cch.CONT_ADVSR = usr.USR_REC_ID ");
				buffer.append(" AND cch.SYS_REF_CONT_HST = :id ");
				
				Query query = session.createSQLQuery(buffer.toString());
				query.setString("id", id);
				
				return query.uniqueResult();
			}
		});
		return o;
	}
	
}

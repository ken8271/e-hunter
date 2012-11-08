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

import com.pccw.ehunter.dao.TransactionLogDAO;
import com.pccw.ehunter.dto.TransactionLogPagedCriteria;
import com.pccw.ehunter.utility.StringUtils;

@Component("transactionlogDao")
public class HibernateTransactionLogDAO implements TransactionLogDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public int getTransactionlogCountByCriteria(final TransactionLogPagedCriteria pagedCriteria) {
		BigInteger count = (BigInteger)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT COUNT(*) ");
				buffer.append(" FROM T_USR_TX_LOG log ");
				buffer.append(" WHERE 1 = 1 ");
				buffer.append(getSQLFilter(pagedCriteria));
				
				Query query = session.createSQLQuery(buffer.toString());
				
				setParameters(query, pagedCriteria);
				
				return query.uniqueResult();
			}
		});
		return count.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getTransactionlogByCriteria(final TransactionLogPagedCriteria pagedCriteria) {
		List<Object> list = (List<Object>)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT log.TX_LOG_ID , log.USR_REC_ID , usr.CNM , log.TX_DTTM , log.FUNC , log.TX_MSG ");
				buffer.append(" FROM T_USR_TX_LOG log INNER JOIN T_INT_USR usr ON log.USR_REC_ID = usr.USR_REC_ID ");
				buffer.append(" WHERE 1 = 1 ");
				buffer.append(getSQLFilter(pagedCriteria));
				buffer.append(" ORDER BY log.TX_LOG_ID ");
				
				Query query = session.createSQLQuery(buffer.toString());
				setParameters(query, pagedCriteria);
				
				if(pagedCriteria.getPageFilter().getRowEnd() > 0){
					query.setFirstResult(pagedCriteria.getPageFilter().getRowStart());
					query.setMaxResults(pagedCriteria.getPageFilter().getRowEnd()-pagedCriteria.getPageFilter().getRowStart());
				}
				
				return query.list();
			}
		});
		return list;
	}
	
	private StringBuffer getSQLFilter(TransactionLogPagedCriteria pagedCriteria){
		StringBuffer filter = new StringBuffer();
		
		if(pagedCriteria.getFromDttm()!= null){
			filter.append(" AND log.TX_DTTM >= :from ");
		}
		
		if(pagedCriteria.getToDttm() != null){
			filter.append(" AND log.TX_DTTM < ADDDATE(:to, INTERVAL 1 MINUTE) ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getModule())){
			filter.append(" AND log.FUNC = :module ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getUser())){
			filter.append(" AND log.USR_REC_ID = :usr ");
		}
		
		return filter;
	}
	
	private void setParameters(Query query , TransactionLogPagedCriteria pagedCriteria){
		if(pagedCriteria.getFromDttm()!= null){
			query.setTimestamp("from", pagedCriteria.getFromDttm());
		}
		
		if(pagedCriteria.getToDttm() != null){
			query.setTimestamp("to", pagedCriteria.getToDttm());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getModule())){
			query.setString("module", pagedCriteria.getModule());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getUser())){
			query.setString("usr", pagedCriteria.getUser());
		}
	}
	
}

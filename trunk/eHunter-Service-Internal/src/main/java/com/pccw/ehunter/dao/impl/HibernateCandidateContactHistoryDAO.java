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

import com.pccw.ehunter.dao.CandidateContactHistoryDAO;

@Component("candidateContactHistoryDao")
public class HibernateCandidateContactHistoryDAO implements CandidateContactHistoryDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getContactHistories(final String talentID ,final String projectID) {
		List<Object> list = (List<Object>)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT tch.SYS_REF_CONT_HST , tch.CONT_TY , usr.CNM , tch.CR_DTTM ");
				buffer.append(" FROM T_TLNT_CONT_HST tch , T_INT_USR usr ");
				buffer.append(" WHERE 1 = 1 ");
				buffer.append(" AND tch.CONT_ADVSR = usr.USR_REC_ID ");
				buffer.append(" AND tch.SYS_REF_TLNT = :talentID ");
				buffer.append(" AND tch.SYS_REF_PRJ = :projectID ");
				buffer.append(" AND tch.LST_TX_ACTN <> 'D' ");
				
				Query query = session.createSQLQuery(buffer.toString());
				
				query.setString("talentID", talentID);
				query.setString("projectID", projectID);
				
				return query.list();
			}
		});
		
		return list;
	}
	
}

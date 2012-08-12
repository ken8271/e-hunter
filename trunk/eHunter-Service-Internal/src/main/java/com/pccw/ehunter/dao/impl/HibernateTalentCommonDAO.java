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

import com.pccw.ehunter.dao.TalentCommonDAO;

@Component("talentCommonDao")
public class HibernateTalentCommonDAO implements TalentCommonDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getTalentSource() {
		List<Object> srcs = (List<Object>)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT SRC_ID , DISP_NM ");
				buffer.append(" FROM T_TLNT_SRC ");
				buffer.append(" WHERE 1 = 1 ");
				buffer.append(" AND ACTV_FLG = 'Y' ");
				buffer.append(" AND LST_TX_ACTN <> 'D' ");
				buffer.append(" ORDER BY DISP_SEQ_NBR ");
				
				Query query = session.createSQLQuery(buffer.toString());
				
				return query.list();
			}
		});
		return srcs;
	}

}

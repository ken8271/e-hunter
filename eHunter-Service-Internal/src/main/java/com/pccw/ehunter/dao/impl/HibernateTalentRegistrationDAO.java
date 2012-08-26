package com.pccw.ehunter.dao.impl;

import java.math.BigInteger;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.pccw.ehunter.dao.TalentRegistrationDAO;
import com.pccw.ehunter.domain.internal.Talent;

@Component("talentRegtDao")
public class HibernateTalentRegistrationDAO implements TalentRegistrationDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public int getTalentCountByPhoneNumber(final String phone) {
		BigInteger count = (BigInteger)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT COUNT(SYS_REF_TLNT) ");
				buffer.append(" FROM T_TLNT_BS_INF ");
				buffer.append(" WHERE 1 = 1 ");
				buffer.append(" AND (PRI_TEL_NBR1 = :phone1 OR PRI_TEL_NBR2 = :phone2) ");
				buffer.append(" AND LST_TX_ACTN <> 'D' ");
				
				Query query = session.createSQLQuery(buffer.toString());
				
				query.setString("phone1", phone);
				query.setString("phone2", phone);
				
				return query.uniqueResult();
			}
		});
		return count.intValue();
	}
	
	@Override
	public int getTalentCountByEmail(final String email) {
		BigInteger count = (BigInteger)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT COUNT(SYS_REF_TLNT) ");
				buffer.append(" FROM T_TLNT_BS_INF ");
				buffer.append(" WHERE EMAIL = :email ");
				buffer.append(" AND LST_TX_ACTN <> 'D' ");
				
				Query query = session.createSQLQuery(buffer.toString());
				
				query.setString("email" , email);
				
				return query.uniqueResult();
			}
		});
		return count.intValue();
	}
	
	@Override
	public void saveTalent(Talent talent) {
		hibernateTemplate.save(talent);
	}

}

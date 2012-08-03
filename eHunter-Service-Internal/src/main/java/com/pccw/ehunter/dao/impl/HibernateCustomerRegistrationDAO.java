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

import com.pccw.ehunter.dao.CustomerRegistrationDAO;
import com.pccw.ehunter.domain.internal.CustomerGroup;

@Component("custRegtDao")
public class HibernateCustomerRegistrationDAO implements CustomerRegistrationDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerGroup> loadCustGroups() {
		List<CustomerGroup> cps = (List<CustomerGroup>)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql = " FROM CustomerGroup cg WHERE cg.lastTransactionIndicator != 'D' ";
				
				Query query = session.createQuery(hql);
				return query.list();
			}
		});
		return cps;
	}
	
	@Override
	public CustomerGroup loadCustGroupByID(final String systemGroupRefNum) {
		CustomerGroup cg = (CustomerGroup)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql = " FROM CustomerGroup cg " 
						   + " WHERE cg.systemGroupRefNum = :systemGroupRefNum "
						   + " AND cg.lastTransactionIndicator != 'D' ";
				
				Query query = session.createQuery(hql);
				query.setString("systemGroupRefNum", systemGroupRefNum);
				
				return query.uniqueResult();
			}
		});
		return cg;
	}

}

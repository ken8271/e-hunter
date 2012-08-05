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

import com.pccw.ehunter.dao.CustomerRegistrationDAO;
import com.pccw.ehunter.domain.internal.CustomerCompany;
import com.pccw.ehunter.domain.internal.CustomerGroup;
import com.pccw.ehunter.domain.internal.CustomerResponsablePerson;

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

	@Override
	public int countGroupsByFullName(final String fullName) {
		BigInteger count = (BigInteger)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT COUNT(*) ");
				buffer.append(" FROM T_CUST_GP ");
				buffer.append(" WHERE GP_NM = :fullName ");
				buffer.append(" AND LST_TX_ACTN <> 'D' ");
				
				Query query = session.createSQLQuery(buffer.toString());
				query.setString("fullName", fullName);
				
				return (BigInteger)query.uniqueResult();
			}
		});
		return count.intValue();
	}

	@Override
	public void saveCustomerCompany(CustomerCompany customerCompany) {
		this.hibernateTemplate.save(customerCompany);
	}

	@Override
	public void saveCustomerGroup(CustomerGroup customerGroup) {
		this.hibernateTemplate.save(customerGroup);
	}

	@Override
	public void saveCustomerResponsablePerson(CustomerResponsablePerson customerResponsablePerson) {
		this.hibernateTemplate.save(customerResponsablePerson);		
	}

}

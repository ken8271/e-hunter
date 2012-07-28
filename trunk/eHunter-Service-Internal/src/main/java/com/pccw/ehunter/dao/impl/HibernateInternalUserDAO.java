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
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.dao.InternalUserDAO;
import com.pccw.ehunter.domain.internal.InternalUser;

@Component("internalUserDao")
public class HibernateInternalUserDAO implements InternalUserDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public InternalUser getInternalUserByLoginId(final String loginId) {
		InternalUser usr = (InternalUser) hibernateTemplate.execute(new HibernateCallback() {
			@Override
			public InternalUser doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = " FROM InternalUser usr WHERE usr.loginId = :loginId ";
				Query query = session.createQuery(hql);
				
				query.setString("loginId", loginId);
				
				@SuppressWarnings("unchecked")
				List<InternalUser> usrs = (List<InternalUser>)query.list();
				
				if(!CollectionUtils.isEmpty(usrs)){
					return usrs.get(0);
				}
				return null;
			}
		});
		return usr;
	}
}

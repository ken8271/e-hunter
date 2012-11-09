package com.pccw.ehunter.dao.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
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
import com.pccw.ehunter.dto.InternalUserPagedCriteria;
import com.pccw.ehunter.utility.StringUtils;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getInternalUsersByCriteria(final InternalUserPagedCriteria pagedCriteria) {
		List<Object> list = (List<Object>)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT usr.USR_REC_ID , usr.LOGIN_ID , usr.ENM , usr.CNM , usr.STAF_NBR , usr.ACCT_ST , usr.CR_DTTM ");
				buffer.append(" FROM T_INT_USR usr , T_INT_USR_ROLE_ASGN asgn ");
				buffer.append(" WHERE 1 = 1 ");
				buffer.append(getSQLFilter(pagedCriteria));
				buffer.append(" ORDER BY usr.USR_REC_ID ");
				
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
	
	private StringBuffer getSQLFilter(InternalUserPagedCriteria pagedCriteria){
		StringBuffer filter = new StringBuffer();
		
		filter.append(" AND usr.USR_REC_ID = asgn.USR_REC_ID ");
		
		if(!StringUtils.isEmpty(pagedCriteria.getLoginId())){
			filter.append(" AND UPPER(usr.LOGIN_ID) LIKE CONCAT('%',UPPER(:loginId),'%') ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getStaffId())){
			filter.append(" AND UPPER(usr.STAF_NBR) LIKE CONCAT('%',UPPER(:staffId),'%') ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getName())){
			filter.append(" AND (UPPER(usr.ENM) LIKE CONCAT('%',UPPER(:name),'%') OR UPPER(usr.CNM) LIKE CONCAT('%',UPPER(:name),'%')) ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getAccountStatus())){
			filter.append(" AND usr.ACCT_ST = :status ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getRole())){
			filter.append(" AND asgn.SYS_REF_ROLE = :role ");
		}
		
		return filter;
	}
	
	private void setParameters(Query query , InternalUserPagedCriteria pagedCriteria){
		if(!StringUtils.isEmpty(pagedCriteria.getLoginId())){
			query.setString("loginId", pagedCriteria.getLoginId());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getStaffId())){
			query.setString("staffId", pagedCriteria.getStaffId());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getName())){
			query.setString("name", pagedCriteria.getName());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getAccountStatus())){
			query.setString("status", pagedCriteria.getAccountStatus());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getRole())){
			query.setString("role", pagedCriteria.getRole());
		}
	}

	@Override
	public int getInternalUsersCountByCriteria(final InternalUserPagedCriteria pagedCriteria) {
		BigInteger count = (BigInteger)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT COUNT(usr.USR_REC_ID) ");
				buffer.append(" FROM T_INT_USR usr , T_INT_USR_ROLE_ASGN asgn ");
				buffer.append(" WHERE 1 = 1 ");
				buffer.append(getSQLFilter(pagedCriteria));
				
				Query query = session.createSQLQuery(buffer.toString());
				setParameters(query, pagedCriteria);
				
				return query.uniqueResult();
			}
		});
		return count.intValue();
	}

	@Override
	public void saveInternalUser(InternalUser internalUser) {
		hibernateTemplate.save(internalUser);
	}

	@Override
	public void updateInternalUser(InternalUser internalUser) {
		hibernateTemplate.update(internalUser);
	}

	@Override
	public void resetPassword(final InternalUser internalUser) {
		hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" UPDATE T_INT_USR ");
				buffer.append(" SET PWD = :password , ");
				buffer.append(" LST_UPD_BY = :updateBy , ");
				buffer.append(" LST_UPD_DTTM = :updateDate , ");
				buffer.append(" LST_TX_ACTN = :action ");
				buffer.append(" WHERE USR_REC_ID = :id ");
				
				Query query = session.createSQLQuery(buffer.toString());
				
				query.setString("password", internalUser.getPassword());
				query.setString("updateBy", internalUser.getLastUpdateBy());
				query.setDate("updateDate", internalUser.getLastUpdateDateTime());
				query.setString("action", internalUser.getLastTransactionIndicator());
				query.setString("id", internalUser.getUserRecId());
				
				query.executeUpdate();
				return null;
			}
		});
	}

	@Override
	public void deleteInternalUser(InternalUser internalUser) {
		hibernateTemplate.delete(internalUser);
	}

	@Override
	public void changePassword(final String userID,final  String newPassword) {
		hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" UPDATE T_INT_USR ");
				buffer.append(" SET PWD = :pwd ,  ");
				buffer.append(" LST_UPD_BY = :userID , ");
				buffer.append(" LST_UPD_DTTM = :dttm ,  ");
				buffer.append(" LST_TX_ACTN = :tx ");
				buffer.append(" WHERE USR_REC_ID = :userID ");
				
				Query query = session.createSQLQuery(buffer.toString());
				
				query.setString("pwd", newPassword);
				query.setString("userID", userID);
				query.setDate("dttm", new Date());
				query.setString("tx", "U");
				
				query.executeUpdate();
				return null;
			}
		});
	}
}

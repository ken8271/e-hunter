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

import com.pccw.ehunter.dao.ProjectCommonDAO;
import com.pccw.ehunter.dto.ProjectPagedCriteria;
import com.pccw.ehunter.utility.StringUtils;

@Component("projectCommonDao")
public class HibernateProjectCommonDAO implements ProjectCommonDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public int getProjectsCountByCriteria(final ProjectPagedCriteria pagedCriteria) {
		BigInteger count = (BigInteger)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT COUNT(pr.SYS_REF_PRJ) ");
				buffer.append(" FROM T_PRJ pr ");
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
	public List<Object> getProjectsByCriteria(final ProjectPagedCriteria pagedCriteria) {
		List<Object> list = (List<Object>)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT pr.SYS_REF_PRJ , pr.PRJ_NM ");
				buffer.append(" FROM T_PRJ pr ");
				buffer.append(" WHERE 1=1 ");
				buffer.append(getSQLFilter(pagedCriteria));
				buffer.append(" ORDER BY pr.SYS_REF_PRJ ");
				
				Query query = session.createSQLQuery(buffer.toString());
				setParameters(query , pagedCriteria);
				return null;
			}
		});
		return list;
	}
	
	private StringBuffer getSQLFilter(ProjectPagedCriteria pagedCriteria) {
		StringBuffer filter = new StringBuffer();
		
		if(!StringUtils.isEmpty(pagedCriteria.getSystemProjectRefNum())){
			filter.append(" AND UPPER(pr.SYS_REF_PRJ) LIKE CONCAT('%',UPPER(:systemProjectRefNum),'%') ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getProjectName())){
			filter.append(" AND UPPER(pr.PRJ_NM) LIKE CONCAT('%',UPPER(:projectName),'%') ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getSystemCustRefNum())){
			filter.append(" AND UPPER(pr.SYS_REF_CUST) LIKE CONCAT('%',UPPER(:systemCustRefNum),'%') ");
		}
		
		return filter;
	}
	
	private void setParameters(Query query, ProjectPagedCriteria pagedCriteria) {
		if(!StringUtils.isEmpty(pagedCriteria.getSystemProjectRefNum())){
			query.setString("systemProjectRefNum", pagedCriteria.getSystemProjectRefNum());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getProjectName())){
			query.setString("projectName", pagedCriteria.getProjectName());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getSystemCustRefNum())){
			query.setString("systemCustRefNum", pagedCriteria.getSystemCustRefNum());
		}
		
	}


}

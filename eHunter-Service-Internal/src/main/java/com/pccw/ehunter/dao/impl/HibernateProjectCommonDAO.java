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
				buffer.append(" FROM T_PRJ pr , T_CUST_CO cc , T_INT_USR usr ");
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
				buffer.append(" SELECT pr.SYS_REF_PRJ , pr.PRJ_NM , cc.CO_NM , pr.CR_DTTM , usr.CNM , pr.PRJ_ST ");
				buffer.append(" FROM T_PRJ pr , T_CUST_CO cc , T_INT_USR usr ");
				buffer.append(" WHERE 1=1 ");
				buffer.append(getSQLFilter(pagedCriteria));
				buffer.append(" ORDER BY pr.SYS_REF_PRJ ");
				
				Query query = session.createSQLQuery(buffer.toString());
				setParameters(query , pagedCriteria);
				return query.list();
			}
		});
		return list;
	}
	
	private StringBuffer getSQLFilter(ProjectPagedCriteria pagedCriteria) {
		StringBuffer filter = new StringBuffer();
		
		filter.append(" AND usr.USR_REC_ID = pr.PRJ_ADVSR ");
		filter.append(" AND cc.SYS_REF_CUST = pr.SYS_REF_CUST ");
		
		if(pagedCriteria.getFromDate() != null){
			filter.append(" AND pr.CR_DTTM >= :fromDate ");
		}
		
		if(pagedCriteria.getToDate() != null){
			filter.append(" AND pr.CR_DTTM < ADDDATE(:toDate, INTERVAL 1 DAY) ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getSystemProjectRefNum())){
			filter.append(" AND UPPER(pr.SYS_REF_PRJ) LIKE CONCAT('%',UPPER(:systemProjectRefNum),'%') ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getProjectName())){
			filter.append(" AND UPPER(pr.PRJ_NM) LIKE CONCAT('%',UPPER(:projectName),'%') ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getSystemCustRefNum())){
			filter.append(" AND UPPER(pr.SYS_REF_CUST) LIKE CONCAT('%',UPPER(:systemCustRefNum),'%') ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getCustomerName())){
			filter.append(" AND (UPPER(cc.CO_NM) LIKE CONCAT('%',UPPER(:custName),'%') OR UPPER(cc.CO_SHRT_NM) LIKE CONCAT('%',UPPER(:custName),'%')) ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getProjectStatus())){
			filter.append(" AND UPPER(pr.PRJ_ST) = UPPER(:status) ");
		}
		
		return filter;
	}
	
	private void setParameters(Query query, ProjectPagedCriteria pagedCriteria) {
		if(pagedCriteria.getFromDate() != null){
			query.setDate("fromDate", pagedCriteria.getFromDate());
		}
		
		if(pagedCriteria.getToDate() != null){
			query.setDate("toDate", pagedCriteria.getToDate());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getSystemProjectRefNum())){
			query.setString("systemProjectRefNum", pagedCriteria.getSystemProjectRefNum().trim());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getProjectName())){
			query.setString("projectName", pagedCriteria.getProjectName().trim());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getSystemCustRefNum())){
			query.setString("systemCustRefNum", pagedCriteria.getSystemCustRefNum().trim());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getCustomerName())){
			query.setString("custName", pagedCriteria.getCustomerName().trim());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getProjectStatus())){
			query.setString("status", pagedCriteria.getProjectStatus().trim());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getSystemTalentRefNum())){
			query.setString("tlnt", pagedCriteria.getSystemTalentRefNum());
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getUnassignedProjectsByCriteria(final ProjectPagedCriteria pagedCriteria) {
		List<Object> list = (List<Object>)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT pr.SYS_REF_PRJ , pr.PRJ_NM , cc.CO_NM , pr.CR_DTTM , usr.CNM , pr.PRJ_ST ");
				buffer.append(" FROM T_PRJ pr , T_CUST_CO cc , T_INT_USR usr ");
				buffer.append(" WHERE 1=1 ");
				buffer.append(getSQLFilter(pagedCriteria));
				buffer.append(" AND pr.SYS_REF_PRJ NOT IN ( ");
				buffer.append(" SELECT sub.SYS_REF_PRJ FROM T_PRJ sub , T_PRJ_TLNT_LIB ptl WHERE sub.SYS_REF_PRJ = ptl.SYS_REF_PRJ AND ptl.SYS_REF_TLNT = :tlnt ");
				buffer.append(" ) ");
				buffer.append(" ORDER BY pr.SYS_REF_PRJ ");

				Query query = session.createSQLQuery(buffer.toString());
				setParameters(query, pagedCriteria);
				
				return query.list();
			}
		});
		return list;
	}


}

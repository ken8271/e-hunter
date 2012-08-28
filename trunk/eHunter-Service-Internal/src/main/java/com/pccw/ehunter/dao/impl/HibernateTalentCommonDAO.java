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

import com.pccw.ehunter.dao.TalentCommonDAO;
import com.pccw.ehunter.dto.TalentPagedCriteria;
import com.pccw.ehunter.utility.StringUtils;

@Component("talentCommonDao")
public class HibernateTalentCommonDAO implements TalentCommonDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public int getTalentsCountByCriteria(final TalentPagedCriteria pagedCriteria) {
		BigInteger count = (BigInteger)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT COUNT(tlnt.SYS_REF_TLNT) ");
				buffer.append(" FROM T_TLNT_BS_INF tlnt ");
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
	public List<Object> getTalentsByCriteria(final TalentPagedCriteria pagedCriteria) {
		List<Object> result = (List<Object>)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT tlnt.SYS_REF_TLNT , tlnt.CNM , tlnt.ENM ,tlnt.HGST_DGRE , tlnt.NW_LV_PLCE ");
				buffer.append(" FROM T_TLNT_BS_INF tlnt ");
				buffer.append(" WHERE 1 = 1 ");
				buffer.append(getSQLFilter(pagedCriteria));
				buffer.append(" ORDER BY tlnt.SYS_REF_TLNT ");
				
				Query query = session.createSQLQuery(buffer.toString());
				setParameters(query, pagedCriteria);
				
				if(pagedCriteria.getPageFilter().getRowEnd() > 0){
					query.setFirstResult(pagedCriteria.getPageFilter().getRowStart());
					query.setMaxResults(pagedCriteria.getPageFilter().getRowEnd()-pagedCriteria.getPageFilter().getRowStart());
				}
				
				return query.list();
			}
		});
		
		return result;
	}
	
	private StringBuffer getSQLFilter(TalentPagedCriteria pagedCriteria){
		StringBuffer filter = new StringBuffer();
		
		if(!StringUtils.isEmpty(pagedCriteria.getTalentID())){
			filter.append(" AND UPPER(tlnt.SYS_REF_TLNT) LIKE CONCAT('%',UPPER(:talentId),'%') ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getName())){
			filter.append(" AND (UPPER(tlnt.CNM) LIKE CONCAT('%',UPPER(:name),'%') OR UPPER(tlnt.ENM) LIKE CONCAT('%',UPPER(:name),'%')) ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getGender())){
			filter.append(" AND UPPER(tlnt.SEX) = UPPER(:gender) ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getTalentSrc())){
			filter.append(" AND UPPER(tlnt.TLNT_SRC) = UPPER(:src) ");
		}
		
		return filter;
	}

	private void setParameters(Query query , TalentPagedCriteria pagedCriteria){
		if(!StringUtils.isEmpty(pagedCriteria.getTalentID())){
			query.setString("talentId", pagedCriteria.getTalentID());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getName())){
			query.setString("name", pagedCriteria.getName());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getGender())){
			query.setString("gender", pagedCriteria.getGender());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getTalentSrc())){
			query.setString("src", pagedCriteria.getTalentSrc());
		}
	}
}

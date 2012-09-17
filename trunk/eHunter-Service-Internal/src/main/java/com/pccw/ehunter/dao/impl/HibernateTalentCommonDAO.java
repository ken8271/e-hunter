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

import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.constant.DateFormatConstant;
import com.pccw.ehunter.constant.DegreeOrderConstant;
import com.pccw.ehunter.dao.TalentCommonDAO;
import com.pccw.ehunter.dto.TalentPagedCriteria;
import com.pccw.ehunter.utility.DateUtils;
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

		if(!StringUtils.isEmpty(pagedCriteria.getAgeTo())){
			filter.append(" AND tlnt.BRTH_DTTM >= :birthDateFrom ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getAgeFrom())){
			filter.append(" AND tlnt.BRTH_DTTM < :birthDateTo ");
		}
		
		StringBuffer subFilter = null;
		if(!StringUtils.isEmpty(pagedCriteria.getMajorCategory())){
			subFilter = new StringBuffer();
			subFilter.append(" SELECT 1 ");
			subFilter.append(" FROM T_TLNT_RSUM tr , T_TLNT_EDU_EXP tee ");
			subFilter.append(" WHERE tr.SYS_REF_TLNT = tlnt.SYS_REF_TLNT ");
			subFilter.append(" AND tee.SYS_REF_RSUM = tr.SYS_REF_RSUM ");
			subFilter.append(" AND tee.MAJOR = :majorCategory ");
			filter.append(" AND EXISTS ( " + subFilter.toString() + " ) ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getDegree())){
			subFilter = new StringBuffer();
			subFilter.append(" SELECT 1 ");
			subFilter.append(" FROM T_TLNT_RSUM tr , T_TLNT_EDU_EXP tee ");
			subFilter.append(" WHERE tr.SYS_REF_TLNT = tlnt.SYS_REF_TLNT ");
			subFilter.append(" AND tee.SYS_REF_RSUM = tr.SYS_REF_RSUM ");
			if(DegreeOrderConstant.OTHER.equals(pagedCriteria.getDegree())){
				subFilter.append(" AND tee.DEGREE NOT IN ( ");
				for(int i=0; i<DegreeOrderConstant.DEGREE_ORDER_LIST.size() ; i++){
					subFilter.append( "'" + DegreeOrderConstant.DEGREE_ORDER_LIST.get(i) + "'");
					if(i != DegreeOrderConstant.DEGREE_ORDER_LIST.size()-1){
						subFilter.append(" , ");
					}
				}
				subFilter.append(" ) ");
			}else {
				subFilter.append(" AND tee.DEGREE IN ( ");
				for(int i=DegreeOrderConstant.DEGREE_ORDER_LIST.indexOf(pagedCriteria.getDegree()); i<DegreeOrderConstant.DEGREE_ORDER_LIST.size() ; i++){
					subFilter.append("'" + DegreeOrderConstant.DEGREE_ORDER_LIST.get(i) + "'");
					if(i != DegreeOrderConstant.DEGREE_ORDER_LIST.size()-1){
						subFilter.append(" , ");
					}
				}
				subFilter.append(" ) ");
			}
			filter.append(" AND EXISTS ( " + subFilter.toString() + " ) ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getExpectIndustries())){
			subFilter = new StringBuffer();
			String[] industries = pagedCriteria.getExpectIndustries().split(CommonConstant.COMMA);
			subFilter.append(" SELECT 1 ");
			subFilter.append(" FROM T_TLNT_RSUM tr , T_TLNT_JOB_EXP tje ");
			subFilter.append(" WHERE tr.SYS_REF_TLNT = tlnt.SYS_REF_TLNT ");
			subFilter.append(" AND tje.SYS_REF_RSUM = tr.SYS_REF_RSUM ");
			subFilter.append(" AND tje.IND_TY IN ( ");
			for(int i=0 ; i<industries.length ; i++){
				subFilter.append("'" + industries[i] + "'");
				if(i != industries.length-1){
					subFilter.append(" , ");
				}
			}
			subFilter.append(" ) ");
			filter.append(" AND EXISTS ( " + subFilter.toString() + " ) ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getSystemProjectRefNum())){
			filter.append(" AND NOT EXISTS ( ");
			filter.append(" SELECT 1 ");
			filter.append(" FROM T_PRJ prj , T_PRJ_TLNT_LIB ptl ");
			filter.append(" WHERE prj.SYS_REF_PRJ = ptl.SYS_REF_PRJ ");
			filter.append(" AND tlnt.SYS_REF_TLNT = ptl.SYS_REF_TLNT ");
			filter.append(" AND prj.SYS_REF_PRJ = :projectId ");
			filter.append(" ) ");
		}
		
		return filter;
	}
	
	private void setParameters(Query query , TalentPagedCriteria pagedCriteria){
		try {	
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
			
			if(!StringUtils.isEmpty(pagedCriteria.getAgeTo())){
				int beginYear = DateUtils.getCurrentYear()-Integer.parseInt(pagedCriteria.getAgeTo())+1;
				query.setDate("birthDateFrom", DateUtils.parse(DateFormatConstant.DATE_YYYYMMDD	, String.valueOf(beginYear)+"0101"));
			}
			
			if(!StringUtils.isEmpty(pagedCriteria.getAgeFrom())){
				int endYear = DateUtils.getCurrentYear()-Integer.parseInt(pagedCriteria.getAgeFrom())+2;
				query.setDate("birthDateTo", DateUtils.parse(DateFormatConstant.DATE_YYYYMMDD	, String.valueOf(endYear)+"0101"));
			}
			
			if(!StringUtils.isEmpty(pagedCriteria.getMajorCategory())){
				query.setString("majorCategory", pagedCriteria.getMajorCategory());
			}
			
			if(!StringUtils.isEmpty(pagedCriteria.getSystemProjectRefNum())){
				query.setString("projectId", pagedCriteria.getSystemProjectRefNum());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getCandidatesCountByCriteria(TalentPagedCriteria pagedCriteria) {
		return 0;
	}

	@Override
	public List<Object> getCandidatesByCriteria(
			TalentPagedCriteria pagedCriteria) {
		return null;
	}
}

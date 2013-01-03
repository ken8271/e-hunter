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
import org.springframework.util.CollectionUtils;

import com.lowagie.text.pdf.hyphenation.TernaryTree.Iterator;
import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.constant.DateFormatConstant;
import com.pccw.ehunter.constant.DegreeOrderConstant;
import com.pccw.ehunter.dao.TalentCommonDAO;

import com.pccw.ehunter.domain.internal.EmploymentHistory;
import com.pccw.ehunter.domain.internal.EmploymentHistoryPK;
import com.pccw.ehunter.dto.EmploymentHistoryDTO;
import com.pccw.ehunter.dto.PagedCriteria;
import com.pccw.ehunter.dto.TalentPagedCriteria;
import com.pccw.ehunter.utility.DateUtils;
import com.pccw.ehunter.utility.StringUtils;

@SuppressWarnings("unused")
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
				buffer.append(" SELECT tlnt.SYS_REF_TLNT , tlnt.CNM , tlnt.ENM ,tlnt.HGST_DGRE , tlnt.NW_LV_PLCE ,tlnt.CR_DTTM ");
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
		
		//#7 2013-01-03
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
		
		//#7 2013-01-03
		if(!StringUtils.isEmpty(pagedCriteria.getSystemProjectRefNum())){
			query.setString("projectId", pagedCriteria.getSystemProjectRefNum());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getParticipatedProjectByTalentID(final String talentID) {
		List<Object> list = (List<Object>)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT DISTINCT prj.SYS_REF_PRJ , prj.PRJ_NM , prj.PRJ_ST , ptl.TLNT_ST , ptl.CR_DTTM ");
				buffer.append(" FROM T_PRJ prj , T_PRJ_TLNT_LIB ptl ");
				buffer.append(" WHERE prj.SYS_REF_PRJ = ptl.SYS_REF_PRJ ");
				buffer.append(" AND ptl.SYS_REF_TLNT = :talentID ");
				
				Query query = session.createSQLQuery(buffer.toString());
				query.setString("talentID", talentID);
				
				return query.list();
			}
		});
		
		return list;
	}

	@Override
	public Object getTalentByID(final String talentID) {
		Object o = hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT tlnt.SYS_REF_TLNT , tlnt.CNM , tlnt.ENM ");
				buffer.append(" FROM T_TLNT_BS_INF tlnt ");
				buffer.append(" WHERE tlnt.SYS_REF_TLNT = :id ");
				
				Query query = session.createSQLQuery(buffer.toString());
				query.setString("id", talentID);
				
				return query.uniqueResult();
			}
		});
		
		return o;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getEmploymentHistoriesByTalentID(final String talentID) {
		List<Object> list = (List<Object>)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT BEGN_DTTM , LEV_DTTM , CO_NM , POST_TY ");
				buffer.append(" FROM T_TLNT_EMP_HST ");
				buffer.append(" WHERE SYS_REF_TLNT = :talentID ");
				buffer.append(" ORDER BY SEQ_NBR ");
				
				Query query = session.createSQLQuery(buffer.toString());
				query.setString("talentID", talentID);
				
				return query.list();
			}
		});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTalentsByPhoneNumber(final String phone) {
		List<String> list = (List<String>)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT SYS_REF_TLNT ");
				buffer.append(" FROM T_TLNT_BS_INF ");
				buffer.append(" WHERE 1 = 1 ");
				buffer.append(" AND ( ");
				buffer.append(" PRI_TEL_NBR1 = :phone ");
				buffer.append(" OR PRI_TEL_NBR2 = :phone ");
				buffer.append(" OR PRI_TEL_NBR3 = :phone ");
				buffer.append(" ) ");
				
				Query query = session.createSQLQuery(buffer.toString());
				
				query.setString("phone", phone);
				
				return query.list();
			}
		});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTalentsByEmail(final String email) {
		List<String> list = (List<String>)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT SYS_REF_TLNT ");
				buffer.append(" FROM T_TLNT_BS_INF ");
				buffer.append(" WHERE EMAIL = :email ");
				
				Query query = session.createSQLQuery(buffer.toString());
				
				query.setString("email", email);
				
				return query.list();
			}
		});
		return list;
	}

	@Override
	public Object getEmploymentHistory(final String id,final String taledId) {
		Object emp=(Object)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {				
				StringBuffer buffer=new StringBuffer();
				buffer.append("SELECT BEGN_DTTM,LEV_DTTM, CO_NM,POST_TY,IND_TY ");
				buffer.append(" FROM   T_TLNT_EMP_HST" );
				buffer.append(" WHERE  SEQ_NBR=:id AND SYS_REF_TLNT=:taledId ");
				
				Query query=session.createSQLQuery(buffer.toString());
				
				query.setString("id", id);
				query.setString("taledId", taledId);
				
				return query.uniqueResult();
			}
		});
		return emp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getTalentsByIds(final List<String> ids, final TalentPagedCriteria pagedCriteria) {
		List<Object> list = (List<Object>)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT tlnt.SYS_REF_TLNT , tlnt.CNM , tlnt.ENM ,tlnt.HGST_DGRE , tlnt.NW_LV_PLCE ,tlnt.CR_DTTM ");
				buffer.append(" FROM T_TLNT_BS_INF tlnt ");
				buffer.append(" WHERE 1 = 1 ");
				
				if(!CollectionUtils.isEmpty(ids)){
					buffer.append(" AND tlnt.SYS_REF_TLNT IN ( ");
					for(int i=0 ; i<ids.size() ; i++){
						if(i != ids.size() -1 ){
							buffer.append(" '" + ids.get(i) + "' , ");
						}else {
							buffer.append(" '" + ids.get(i) + "' ");
						}
					}
					buffer.append(" ) ");
				}

				if(!StringUtils.isEmpty(pagedCriteria.getSystemProjectRefNum())){
					buffer.append(" AND NOT EXISTS ( ");
					buffer.append(" SELECT 1 ");
					buffer.append(" FROM T_PRJ prj , T_PRJ_TLNT_LIB ptl ");
					buffer.append(" WHERE prj.SYS_REF_PRJ = ptl.SYS_REF_PRJ ");
					buffer.append(" AND tlnt.SYS_REF_TLNT = ptl.SYS_REF_TLNT ");
					buffer.append(" AND prj.SYS_REF_PRJ = :projectId ");
					buffer.append(" ) ");
				}
				
				buffer.append(" ORDER BY tlnt.SYS_REF_TLNT ");
				
				Query query = session.createSQLQuery(buffer.toString());
			
				if(!StringUtils.isEmpty(pagedCriteria.getSystemProjectRefNum())){
					query.setString("projectId", pagedCriteria.getSystemProjectRefNum());
				}
				
				if(pagedCriteria.getPageFilter().getRowEnd() > 0){
					query.setFirstResult(pagedCriteria.getPageFilter().getRowStart());
					query.setMaxResults(pagedCriteria.getPageFilter().getRowEnd()-pagedCriteria.getPageFilter().getRowStart());
				}
				
				return query.list();
			}
		});
		return list;
	}

	@Override
	public int getTalentsCountByIds(final List<String> ids,final TalentPagedCriteria pagedCriteria) {
		BigInteger count = (BigInteger)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT count(*) ");
				buffer.append(" FROM T_TLNT_BS_INF tlnt ");
				buffer.append(" WHERE 1 = 1 ");
				
				if(!CollectionUtils.isEmpty(ids)){
					buffer.append(" AND tlnt.SYS_REF_TLNT IN ( ");
					for(int i=0 ; i<ids.size() ; i++){
						if(i != ids.size() -1 ){
							buffer.append(" '" + ids.get(i) + "' , ");
						}else {
							buffer.append(" '" + ids.get(i) + "' ");
						}
					}
					buffer.append(" ) ");
				}

				if(!StringUtils.isEmpty(pagedCriteria.getSystemProjectRefNum())){
					buffer.append(" AND NOT EXISTS ( ");
					buffer.append(" SELECT 1 ");
					buffer.append(" FROM T_PRJ prj , T_PRJ_TLNT_LIB ptl ");
					buffer.append(" WHERE prj.SYS_REF_PRJ = ptl.SYS_REF_PRJ ");
					buffer.append(" AND tlnt.SYS_REF_TLNT = ptl.SYS_REF_TLNT ");
					buffer.append(" AND prj.SYS_REF_PRJ = :projectId ");
					buffer.append(" ) ");
				}
				
				Query query = session.createSQLQuery(buffer.toString());
			
				if(!StringUtils.isEmpty(pagedCriteria.getSystemProjectRefNum())){
					query.setString("projectId", pagedCriteria.getSystemProjectRefNum());
				}
								
				return query.uniqueResult();
			}
		});
		return count.intValue();
	}
}

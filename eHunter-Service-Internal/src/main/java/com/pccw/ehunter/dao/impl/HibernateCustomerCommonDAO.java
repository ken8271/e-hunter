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

import com.pccw.ehunter.dao.CustomerCommonDAO;
import com.pccw.ehunter.dto.CustomerPagedCriteria;
import com.pccw.ehunter.utility.StringUtils;

@Component("customerDao")
public class HibernateCustomerCommonDAO implements CustomerCommonDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public int getCustomersCountByCriteria(final CustomerPagedCriteria pagedCriteria) {
		BigInteger count = (BigInteger)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT COUNT(cc.SYS_REF_CUST) ");
				buffer.append(" FROM T_CUST_CO cc , T_CUST_GP cg , T_CUST_GRDE gd , T_CUST_STAT cs ");
				buffer.append(" WHERE 1 = 1 ");
				buffer.append(getSQLFiter(pagedCriteria));
				
				Query query = session.createSQLQuery(buffer.toString());
				setParameters(query, pagedCriteria);
				
				return query.uniqueResult();
			}
		});
		return count.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getCustomersByCriteria(final CustomerPagedCriteria pagedCriteria) {
		List<Object> result = (List<Object>)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT cc.SYS_REF_CUST , cc.CO_NM , cc.CO_SHRT_NM , gd.DISP_NM as GD_DISP_NM , cs.DISP_NM as STAT_DISP_NM ");
				buffer.append(" FROM T_CUST_CO cc , T_CUST_GP cg , T_CUST_GRDE gd , T_CUST_STAT cs ");
				buffer.append(" WHERE 1=1 ");
				buffer.append(getSQLFiter(pagedCriteria));
				buffer.append(" ORDER BY cc.SYS_REF_CUST ");
				
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
	
	private StringBuffer getSQLFiter(CustomerPagedCriteria pagedCriteria) {
		StringBuffer filter = new StringBuffer();
		filter.append(" AND cc.CUST_GRDE = gd.GEDE_CD ");
		filter.append(" AND cc.CUST_STAT = cs.STAT_CD ");
		filter.append(" AND cc.SYS_REF_GP = cg.SYS_REF_GP ");
		
		if(!StringUtils.isEmpty(pagedCriteria.getSystemCustRefNum())){
			filter.append(" AND UPPER(cc.SYS_REF_CUST) LIKE CONCAT('%',UPPER(:sysCustRefNum),'%') ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getGroupName())){
			filter.append(" AND (UPPER(cg.GP_NM) LIKE CONCAT('%',UPPER(:groupName),'%') OR UPPER(cg.GP_SHRT_NM) LIKE CONCAT('%', UPPER(:groupName) ,'%')) ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getName())){
			filter.append(" AND (UPPER(cc.CO_NM) LIKE CONCAT('%',UPPER(:custName),'%') OR UPPER(cc.CO_SHRT_NM) LIKE CONCAT('%',UPPER(:custName),'%')) ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getType())){
			filter.append(" AND cc.CO_TY = :type ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getSize())){
			filter.append(" AND cc.CO_SZ = :size ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getGrade())){
			filter.append(" AND cc.CUST_GRDE = :grade ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getStatus())){
			filter.append(" AND cc.CUST_STAT = :status ");
		}
		
		return filter;
	}
	
	private void setParameters(Query query , CustomerPagedCriteria pagedCriteria){		
		if(!StringUtils.isEmpty(pagedCriteria.getSystemCustRefNum())){
			query.setString("sysCustRefNum", pagedCriteria.getSystemCustRefNum());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getGroupName())){
			query.setString("groupName", pagedCriteria.getGroupName());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getName())){
			query.setString("custName", pagedCriteria.getName());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getType())){
			query.setString("type", pagedCriteria.getType());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getSize())){
			query.setString("size", pagedCriteria.getSize());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getGrade())){
			query.setString("grade", pagedCriteria.getGrade());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getStatus())){
			query.setString("status", pagedCriteria.getStatus());
		}
	}

	@Override
	public Object getCustomerByID(final String systemCustRefNum) {
		Object cust = hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT cc.SYS_REF_CUST , cc.CO_NM , cc.CO_SHRT_NM , cc.OFCL_SITE , cc.CO_TEL_EXCHG , cc.GP_FLAG , cc.SYS_REF_GP , ct.DISP_NM AS TY_NM , cs.DISP_NM AS SZ_NM , cg.DISP_NM AS GRD_NM , st.DISP_NM AS ST_NM ");
				buffer.append(" FROM T_CUST_CO cc , T_CO_TY ct , T_CO_SZ cs , T_CUST_GRDE cg , T_CUST_STAT st ");
				buffer.append(" WHERE 1 = 1 ");
				buffer.append(" AND cc.CO_TY = ct.TY_CD ");
				buffer.append(" AND cc.CO_SZ = cs.CO_SZ_ID ");
				buffer.append(" AND cc.CUST_GRDE = cg.GEDE_CD ");
				buffer.append(" AND cc.CUST_STAT = st.STAT_CD ");
				buffer.append(" AND cc.SYS_REF_CUST = :custId ");
				buffer.append(" AND cc.LST_TX_ACTN <> 'D' ");
				
				Query query = session.createSQLQuery(buffer.toString());
				query.setString("custId", systemCustRefNum);
				
				return query.uniqueResult();
			}
		});
		return cust;
	}

	@Override
	public Object getCustomerGroupByID(final String groupId) {
		Object group = hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT cg.SYS_REF_GP , cg.GP_NM , cg.GP_SHRT_NM ");
				buffer.append(" FROM T_CUST_GP cg ");
				buffer.append(" WHERE cg.SYS_REF_GP = :groupId ");
				buffer.append(" AND cg.LST_TX_ACTN <> 'D' ");
				
				Query query = session.createSQLQuery(buffer.toString());
				query.setString("groupId", groupId);
				
				return query.uniqueResult();
			}
		});
		return group;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getCustomerRespPersonsByCustID(final String custId) {
		List<Object> rps = (List<Object>)hibernateTemplate.executeFind(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT cr.SYS_REF_RP , cr.RP_NM , cr.RP_TEL , cr.RP_EMAIL , cr.RP_STAT , pt.DISP_NM AS TOP_POST , ps.DISP_NM AS SUB_POST , cr.RP_POST ");
				buffer.append(" FROM T_CUST_RP cr , T_POST_TY pt , T_POST_TY_SUB ps ");
				buffer.append(" WHERE 1 = 1 ");
				buffer.append(" AND cr.RP_POST_TY = ps.TY_CD ");
				buffer.append(" AND ps.TOP_TY = pt.TY_CD ");
				buffer.append(" AND cr.SYS_REF_CUST = :custId ");
				buffer.append(" AND cr.LST_TX_ACTN <> 'D' ");
				buffer.append(" ORDER BY cr.SYS_REF_RP ");
				
				Query query = session.createSQLQuery(buffer.toString());
				query.setString("custId", custId);
				
				return query.list();
			}
		});
		return rps;
	}

}

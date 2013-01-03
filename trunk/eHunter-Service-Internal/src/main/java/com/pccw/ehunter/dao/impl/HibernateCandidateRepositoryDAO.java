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

import com.pccw.ehunter.dao.CandidateRepositoryDAO;
import com.pccw.ehunter.domain.internal.Candidate;
import com.pccw.ehunter.dto.TalentPagedCriteria;

@Component("cddtRepoDao")
public class HibernateCandidateRepositoryDAO implements CandidateRepositoryDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void saveCandidateRepository(List<Candidate> repos) {
		if(!CollectionUtils.isEmpty(repos)){
			for(Candidate repo : repos){
				hibernateTemplate.save(repo);
			}
		}
	}

	@Override
	public int getCandidateRepositoryCountByProjectID(final TalentPagedCriteria pagedCriteria) {
		BigInteger count = (BigInteger)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT COUNT(*) ");
				buffer.append(" FROM T_PRJ_TLNT_LIB ptl , T_TLNT_BS_INF tlnt ");
				buffer.append(" WHERE 1 = 1 ");
				buffer.append(" AND ptl.SYS_REF_TLNT = tlnt.SYS_REF_TLNT ");
				buffer.append(" AND ptl.SYS_REF_PRJ = :projectID ");
				buffer.append(" AND ptl.LST_TX_ACTN <> 'D' ");
				buffer.append(" AND tlnt.LST_TX_ACTN <> 'D' ");
				
				Query query = session.createSQLQuery(buffer.toString());
				query.setString("projectID", pagedCriteria.getSystemProjectRefNum());
				
				return query.uniqueResult();
			}
		});
		
		return count.intValue();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getCandidateRepositoryByProjectID(final TalentPagedCriteria pagedCriteria) {
		List<Object> list = (List<Object>)hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" SELECT tlnt.SYS_REF_TLNT , tlnt.CNM , tlnt.ENM , tlnt.HGST_DGRE , dg.DISP_NM , tlnt.NW_LV_PLCE , ptl.TLNT_ST ");
				//#7 2013-01-03
				buffer.append(" FROM T_TLNT_BS_INF tlnt LEFT JOIN T_DGRE dg ON tlnt.HGST_DGRE = dg.DGRE_CD , T_PRJ_TLNT_LIB ptl  ");
				buffer.append(" WHERE 1 = 1 ");
				buffer.append(" AND tlnt.SYS_REF_TLNT = ptl.SYS_REF_TLNT ");
				buffer.append(" AND ptl.SYS_REF_PRJ = :projectID ");
				buffer.append(" AND tlnt.LST_TX_ACTN <> 'D' ");
				buffer.append(" AND ptl.LST_TX_ACTN <> 'D' ");
				
				Query query = session.createSQLQuery(buffer.toString());
				query.setString("projectID", pagedCriteria.getSystemProjectRefNum());
				
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
	public void updateCandidateStatus(final Candidate po) {
		hibernateTemplate.execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" UPDATE T_PRJ_TLNT_LIB ");
				buffer.append(" SET TLNT_ST = :status , LST_UPD_BY = :updateBy , LST_UPD_DTTM = :updateDate , LST_TX_ACTN = :action ");
				buffer.append(" WHERE 1 = 1 ");
				buffer.append(" AND SYS_REF_TLNT = :talentID ");
				buffer.append(" AND SYS_REF_PRJ = :projectID ");
				
				Query query = session.createSQLQuery(buffer.toString());
				
				query.setString("status", po.getCandidateStatus());
				query.setString("updateBy", po.getLastUpdateBy());
				query.setDate("updateDate", po.getLastUpdateDateTime());
				query.setString("action", po.getLastTransactionIndicator());
				query.setString("talentID", po.getPk().getTalent().getTalentID());
				query.setString("projectID", po.getPk().getProject().getSystemProjectRefNum());
				
				query.executeUpdate();
				
				return null;
			}
		});
	}


}

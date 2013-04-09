package cn.org.polaris.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import cn.org.polaris.dao.InfoCenterDAO;
import cn.org.polaris.dto.biz.InformationPagedCriteria;
import cn.org.polaris.repo.Information;

@Component("infoCenterDao")
public class HibernateInfoCenterDAO  implements InfoCenterDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public int getInfosCountByCategory(final InformationPagedCriteria pagedCriteria) {
		Long count = (Long)hibernateTemplate.execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer buffer = new StringBuffer();
				
				buffer.append(" SELECT count(info.systemRefInfo) ");
				buffer.append(" FROM Information info ");
				buffer.append(" WHERE info.lastTransactionIndicator != 'D' ");
				
				if(pagedCriteria.getCategory() != null && pagedCriteria.getCategory().length() != 0){
					buffer.append(" AND info.category = :category ");
				}
				
				Query query = session.createQuery(buffer.toString());
				
				if(pagedCriteria.getCategory() != null && pagedCriteria.getCategory().length() != 0){
					query.setString("category", pagedCriteria.getCategory());
				}
				
				return (Long)query.uniqueResult();
			}
		});
		
		return count.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Information> getInfosByCategory(final InformationPagedCriteria pagedCriteria) {
		List<Information> infos = (List<Information>)hibernateTemplate.execute(new HibernateCallback<List<Information>>() {
			@Override
			public List<Information> doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer buffer = new StringBuffer();
				
				buffer.append(" FROM Information info ");
				buffer.append(" WHERE info.lastTransactionIndicator != 'D' ");
				
				if(pagedCriteria.getCategory() != null && pagedCriteria.getCategory().length() != 0){
					buffer.append(" AND info.category = :category ");
				}
				
				Query query = session.createQuery(buffer.toString());
				
				if(pagedCriteria.getCategory() != null && pagedCriteria.getCategory().length() != 0){
					query.setString("category", pagedCriteria.getCategory());
				}
				
				if(pagedCriteria.getRowEnd() > 0 ){
					query.setFirstResult(pagedCriteria.getRowStart());
					query.setMaxResults(pagedCriteria.getRowEnd() - pagedCriteria.getRowStart());
				}
				
				return query.list();
			}
		});
		
		return infos;
	}

	@Override
	public Information getInformationByID(final String id) {
		Information info = (Information)hibernateTemplate.execute(new HibernateCallback<Information>() {
			@Override
			public Information doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer buffer = new StringBuffer();
				
				buffer.append(" FROM Information info ");
				buffer.append(" WHERE info.lastTransactionIndicator != 'D' ");
				buffer.append(" AND info.systemRefInfo = :id ");
				
				Query query = session.createQuery(buffer.toString());
				
				query.setString("id", id);
				
				return (Information)query.uniqueResult();
			}
		});
		return info;
	}
	
}

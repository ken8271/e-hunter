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
import cn.org.polaris.utility.StringUtils;

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
				buffer.append(getSQLFilter(pagedCriteria));
				
				Query query = session.createQuery(buffer.toString());
				
				setParameters(query, pagedCriteria);
				
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
				buffer.append(getSQLFilter(pagedCriteria));
				
				Query query = session.createQuery(buffer.toString());
				
				setParameters(query, pagedCriteria);
				
				if(pagedCriteria.getRowEnd() > 0 ){
					query.setFirstResult(pagedCriteria.getRowStart());
					query.setMaxResults(pagedCriteria.getRowEnd() - pagedCriteria.getRowStart());
				}
				
				return query.list();
			}
		});
		
		return infos;
	}
	
	private StringBuffer getSQLFilter(InformationPagedCriteria pagedCriteria){
		StringBuffer filter = new StringBuffer();
		
		if(!StringUtils.isEmpty(pagedCriteria.getId())){
			filter.append(" AND info.systemRefInfo = :id ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getTitle())){
			filter.append(" AND UPPER(info.title) LIKE CONCAT('%',UPPER(:title),'%')  ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getCategory())){
			filter.append(" AND info.category = :category ");
		}
		
		if(pagedCriteria.getFromDate() != null){
			filter.append(" AND info.createDateTime >= :fromDate ");
		}
		
		if(pagedCriteria.getToDate() != null){
			filter.append(" AND info.createDateTime < ADDDATE(:toDate , INTERVAL 1 DAY) ");
		}
		
		return filter;
	}
	
	private void setParameters(Query query , InformationPagedCriteria pagedCriteria){
		if(!StringUtils.isEmpty(pagedCriteria.getId())){
			query.setString("id", pagedCriteria.getId());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getTitle())){
			query.setString("title", pagedCriteria.getTitle());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getCategory())){
			query.setString("category", pagedCriteria.getCategory());
		}
		
		if(pagedCriteria.getFromDate() != null){
			query.setDate("fromDate", pagedCriteria.getFromDate());
		}
		
		if(pagedCriteria.getToDate() != null){
			query.setDate("toDate", pagedCriteria.getToDate());
		}
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

	@Override
	public void releaseInformation(Information info) {
		hibernateTemplate.save(info);
	}

	@Override
	public void updateInformation(Information info) {
		hibernateTemplate.update(info);
	}

	@Override
	public void deleteInformation(Information info) {
		hibernateTemplate.delete(info);
	}
	
}

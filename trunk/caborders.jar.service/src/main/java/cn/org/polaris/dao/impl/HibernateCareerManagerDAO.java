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

import cn.org.polaris.dao.CareerManagerDAO;
import cn.org.polaris.dto.biz.PositionPagedCriteria;
import cn.org.polaris.repo.ReleasedPosition;
import cn.org.polaris.utility.StringUtils;

@Component("careerManagerDao")
public class HibernateCareerManagerDAO implements CareerManagerDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public int getPositionsCountByCriteria(final PositionPagedCriteria pagedCriteria) {
		Long count = (Long)hibernateTemplate.execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				
				buffer.append(" SELECT count(rp.systemRefPosition) ");
				buffer.append(" FROM ReleasedPosition rp ");
				buffer.append(" WHERE rp.lastTransactionIndicator != 'D' ");
				buffer.append(getHQLFilter(pagedCriteria));
				
				Query query = session.createQuery(buffer.toString());
				
				setParameters(query, pagedCriteria);
				
				return (Long)query.uniqueResult();
			}
		});
		return count.intValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ReleasedPosition> getPositionsByCriteria(final PositionPagedCriteria pagedCriteria) {
		List<ReleasedPosition> rps = (List<ReleasedPosition>)hibernateTemplate.execute(new HibernateCallback<List<ReleasedPosition>>() {
			@Override
			public List<ReleasedPosition> doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer buffer = new StringBuffer();
				
				buffer.append(" FROM ReleasedPosition rp ");
				buffer.append(" WHERE rp.lastTransactionIndicator != 'D' ");
				buffer.append(getHQLFilter(pagedCriteria));
				
				Query query = session.createQuery(buffer.toString());
				
				setParameters(query, pagedCriteria);
				
				if(pagedCriteria.getRowEnd() > 0 ){
					query.setFirstResult(pagedCriteria.getRowStart());
					query.setMaxResults(pagedCriteria.getRowEnd() - pagedCriteria.getRowStart());
				}
				
				return query.list();
			}
		});
		return rps;
	}
	
	private StringBuffer getHQLFilter(PositionPagedCriteria pagedCriteria){
		StringBuffer filter = new StringBuffer();
		
		if(!StringUtils.isEmpty(pagedCriteria.getId())){
			filter.append("  AND rp.systemRefPosition = :id ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getTitle())){
			filter.append(" AND UPPER(rp.title) LIKE CONCAT('%',UPPER(:title),'%') ");
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getWorkCity())){
			filter.append(" AND UPPER(rp.workCity) LIKE CONCAT('%',UPPER(:city),'%') ");
		}
		
		if(pagedCriteria.getFromDate() != null){
			filter.append(" AND rp.createDateTime >= :fromDate ");
		}
		
		if(pagedCriteria.getToDate() != null){
			filter.append(" AND rp.createDateTime < ADDDATE(:toDate , INTERVAL 1 DAY) ");
		}
		
		return filter;
	}
	
	private void setParameters(Query query , PositionPagedCriteria pagedCriteria){
		if(!StringUtils.isEmpty(pagedCriteria.getId())){
			query.setString("id", pagedCriteria.getId());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getTitle())){
			query.setString("title", pagedCriteria.getTitle());
		}
		
		if(!StringUtils.isEmpty(pagedCriteria.getWorkCity())){
			query.setString("city", pagedCriteria.getWorkCity());
		}
		
		if(pagedCriteria.getFromDate() != null){
			query.setDate("fromDate", pagedCriteria.getFromDate());
		}
		
		if(pagedCriteria.getToDate() != null){
			query.setDate("toDate", pagedCriteria.getToDate());
		}
	}

	@Override
	public ReleasedPosition getPositionByID(final String id) {
		ReleasedPosition rp = (ReleasedPosition)hibernateTemplate.execute(new HibernateCallback<ReleasedPosition>() {
			@Override
			public ReleasedPosition doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer buffer = new StringBuffer();
				
				buffer.append(" FROM ReleasedPosition rp ");
				buffer.append(" WHERE rp.lastTransactionIndicator != 'D' ");
				buffer.append(" AND rp.systemRefPosition = :id ");
				
				Query query = session.createQuery(buffer.toString());
				
				query.setString("id", id);
				
				return (ReleasedPosition)query.uniqueResult();
			}
		});
		return rp;
	}

	@Override
	public void releasePosition(ReleasedPosition rp) {
		hibernateTemplate.save(rp);
	}

	@Override
	public void updatePosition(ReleasedPosition rp) {
		hibernateTemplate.update(rp);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void deletePositionByID(final String id) {
		hibernateTemplate.execute(new HibernateCallback(){

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer buffer = new StringBuffer();
				
				buffer.append(" DELETE FROM t_rlesd_postn ");
				buffer.append(" WHERE sys_ref_postn = :id ");
				
				Query query = session.createSQLQuery(buffer.toString());
				
				query.setString("id", id);
				
				query.executeUpdate();
				return null;
			}
			
		});
	}

}

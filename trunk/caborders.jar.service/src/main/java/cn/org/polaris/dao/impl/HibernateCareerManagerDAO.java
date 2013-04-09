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
import cn.org.polaris.dto.PagedCriteria;
import cn.org.polaris.repo.ReleasedPosition;

@Component("careerManagerDao")
public class HibernateCareerManagerDAO implements CareerManagerDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public int getPositionsCountByCriteria(PagedCriteria pagedCriteria) {
		Long count = (Long)hibernateTemplate.execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuffer buffer = new StringBuffer();
				
				buffer.append(" SELECT count(rp.systemRefPosition) ");
				buffer.append(" FROM ReleasedPosition rp ");
				buffer.append(" WHERE rp.lastTransactionIndicator != 'D' ");
				
				Query query = session.createQuery(buffer.toString());
				
				return (Long)query.uniqueResult();
			}
		});
		return count.intValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ReleasedPosition> getPositionsByCriteria(final PagedCriteria pagedCriteria) {
		List<ReleasedPosition> rps = (List<ReleasedPosition>)hibernateTemplate.execute(new HibernateCallback<List<ReleasedPosition>>() {
			@Override
			public List<ReleasedPosition> doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer buffer = new StringBuffer();
				
				buffer.append(" FROM ReleasedPosition rp ");
				buffer.append(" WHERE rp.lastTransactionIndicator != 'D' ");
				
				Query query = session.createQuery(buffer.toString());
				
				if(pagedCriteria.getRowEnd() > 0 ){
					query.setFirstResult(pagedCriteria.getRowStart());
					query.setMaxResults(pagedCriteria.getRowEnd() - pagedCriteria.getRowStart());
				}
				
				return query.list();
			}
		});
		return rps;
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

}

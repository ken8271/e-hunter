package com.pccw.ehunter.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.pccw.ehunter.dao.PositionCommonDAO;
import com.pccw.ehunter.domain.common.PositionSubType;
import com.pccw.ehunter.domain.common.PositionType;

@Component("positionDao")
public class HibernatePositionCommonDAO implements PositionCommonDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<PositionType> loadPositionTypes() {
		List<PositionType> types = (List<PositionType>)hibernateTemplate.execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = " FROM PositionType pt WHERE pt.activeFlag != 'N' AND pt.lastTransactionIndicator != 'D' ORDER BY pt.displaySeqNumber ";
				Query query = session.createQuery(hql);
				return query.list();
			}
		});
		return types;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PositionSubType> loadSubTypesByParentCode(final String code) {
		List<PositionSubType> subTypes = (List<PositionSubType>)hibernateTemplate.execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				StringBuffer buffer = new StringBuffer();
				buffer.append(" FROM PositionSubType pst ");
				buffer.append(" WHERE pst.topType = :code ");
				buffer.append(" AND pst.activeFlag != 'N' ");
				buffer.append(" AND pst.lastTransactionIndicator != 'D' ");
				buffer.append(" ORDER BY pst.displaySeqNumber ");
				
				Query query = session.createQuery(buffer.toString());
				query.setString("code", code);
				
				return query.list();
			}
			
		});
		return subTypes;
	}

}

package com.pccw.ehunter.dao.impl;

import java.sql.SQLException;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.pccw.ehunter.constant.TransactionIndicator;
import com.pccw.ehunter.dao.SequenceNumberDAO;
import com.pccw.ehunter.domain.common.Sequence;
import com.pccw.ehunter.utility.SecurityUtils;

@Component("seqNumDao")
public class HibernateSequenceNumberDAO implements SequenceNumberDAO{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public long getNextValue(final String key , final LockMode lockmode) {
		Object o = hibernateTemplate.execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Sequence seq = (Sequence)session.load(Sequence.class, key ,lockmode);
				seq.setValue(seq.getValue() + 1L);
				seq.setLastUpdateDateTime(new Date());
				seq.setLastTransactionIndicator(TransactionIndicator.UPDATE);
				seq.setLastUpdateBy(SecurityUtils.getUserRecId());
				
				session.save(seq);
				
				return seq;
			}
		});
		return ((Sequence)o).getValue().longValue();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public long getCurrValue(final String key , final LockMode lockmode) {
		Long value = hibernateTemplate.execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Sequence seq = (Sequence)session.load(Sequence.class, key , lockmode);
				
				if(seq != null){
					return seq.getValue();
				}
				return new Long(0);
			}
		});
		return value;
	}

}

package com.pccw.ehunter.service.impl;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pccw.ehunter.constant.IDNumberKeyConstant;
import com.pccw.ehunter.convertor.TransactionLogConvertor;
import com.pccw.ehunter.domain.internal.TransactionLog;
import com.pccw.ehunter.dto.TransactionLogDTO;
import com.pccw.ehunter.helper.IDGenerator;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.TransactionLogService;
import com.pccw.ehunter.utility.SecurityUtils;

@Service("transactionLogService")
@Transactional
public class TransactionLogServiceImpl implements TransactionLogService{
	
	private SimpleHibernateTemplate<TransactionLog, String> simpleDao;
	
	@Autowired
	private IDGenerator idGenerator;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		simpleDao = new SimpleHibernateTemplate<TransactionLog, String>(sessionFactory, TransactionLog.class);
	}

	@Override
	@Transactional
	public void logTransaction(String function, String msg) {
		TransactionLogDTO dto = new TransactionLogDTO();
		dto.setId(idGenerator.generateID(IDNumberKeyConstant.TRANSACTION_LOG_SEQUENCE_KEY, null, 9));
		dto.setUserID(SecurityUtils.getUserRecId());
		dto.setTransactionDatetime(new Date());
		dto.setFunctionIndicator(function);
		dto.setTransactionMsg(msg);
		
		simpleDao.save(TransactionLogConvertor.toPo(dto));
	}

	@Override
	@Transactional
	public void logTransaction(String function, String msg, String user) {
		TransactionLogDTO dto = new TransactionLogDTO();
		dto.setId(idGenerator.generateID(IDNumberKeyConstant.TRANSACTION_LOG_SEQUENCE_KEY, null, 9));
		dto.setUserID(user);
		dto.setTransactionDatetime(new Date());
		dto.setFunctionIndicator(function);
		dto.setTransactionMsg(msg);
		
		simpleDao.save(TransactionLogConvertor.toPo(dto));
	}

}

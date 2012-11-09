package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.constant.IDNumberKeyConstant;
import com.pccw.ehunter.convertor.TransactionLogConvertor;
import com.pccw.ehunter.dao.TransactionLogDAO;
import com.pccw.ehunter.domain.internal.TransactionLog;
import com.pccw.ehunter.dto.TransactionLogDTO;
import com.pccw.ehunter.dto.TransactionLogPagedCriteria;
import com.pccw.ehunter.helper.IDGenerator;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.TransactionLogService;
import com.pccw.ehunter.utility.SecurityUtils;
import com.pccw.ehunter.utility.StringUtils;

@Service("transactionLogService")
@Transactional
public class TransactionLogServiceImpl implements TransactionLogService{
	
	private SimpleHibernateTemplate<TransactionLog, String> simpleDao;
	
	@Autowired
	private TransactionLogDAO transactionlogDao;
	
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

	@Override
	@Transactional(readOnly=true)
	public int getTransactionlogCountByCriteria(TransactionLogPagedCriteria pagedCriteria) {
		return transactionlogDao.getTransactionlogCountByCriteria(pagedCriteria);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TransactionLogDTO> getTransactionlogByCriteria(TransactionLogPagedCriteria pagedCriteria) {
		List<Object> list = transactionlogDao.getTransactionlogByCriteria(pagedCriteria);
		List<TransactionLogDTO> dtos = new ArrayList<TransactionLogDTO>();
		
		if(!CollectionUtils.isEmpty(list)){
			TransactionLogDTO dto = null;
			for(Object o : list){
				Object[] os = (Object[])o;
				
				dto = new TransactionLogDTO();
				dto.setId(StringUtils.isEmpty((String)os[0]) ? "" : (String)os[0]);
				dto.setUserID(StringUtils.isEmpty((String)os[1]) ? "" : (String)os[1]);
				String cnm = StringUtils.isEmpty((String)os[2]) ? "" : (String)os[2];
				String enm = StringUtils.isEmpty((String)os[3]) ? "" : (String)os[3];
				dto.setUserName(cnm + (StringUtils.isEmpty(enm) ? "" : "(" + enm + ")"));
				dto.setTransactionDatetime(os[4]==null ? (new Date()) : ((Date)os[4]));
				dto.setFunctionIndicator(StringUtils.isEmpty((String)os[5]) ? "" : (String)os[5]);
				dto.setTransactionMsg(StringUtils.isEmpty((String)os[6]) ? "" : (String)os[6]);
				
				dtos.add(dto);
			}
		}
		
		return dtos;
	}

}

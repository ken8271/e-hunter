package com.pccw.ehunter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pccw.ehunter.dao.IDGeneratorDAO;
import com.pccw.ehunter.service.IDGeneratorService;

@Service("idGeneratorService")
@Transactional
public class IDGeneratorServiceImpl implements IDGeneratorService{
	
	@Autowired
	private IDGeneratorDAO idGeneratorDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public Long getNextValue(String key, boolean nowait) {
		if(nowait){
			return idGeneratorDao.getNextValueNoWait(key);
		}else {			
			return idGeneratorDao.getNextValue(key);
		}
	}

	@Override
	public Long getCurrValue(String key, boolean nowait) {
		if(nowait){
			return idGeneratorDao.getCurrValueNoWait(key);
		}else {			
			return idGeneratorDao.getCurrValue(key);
		}
	}


}

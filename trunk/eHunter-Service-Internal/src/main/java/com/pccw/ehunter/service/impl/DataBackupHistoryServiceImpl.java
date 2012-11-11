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
import com.pccw.ehunter.convertor.DataBackupHistoryConvertor;
import com.pccw.ehunter.dao.DataBackupHistoryDAO;
import com.pccw.ehunter.domain.internal.DataBackupHistory;
import com.pccw.ehunter.dto.DataBackupHistoryDTO;
import com.pccw.ehunter.dto.PagedCriteria;
import com.pccw.ehunter.helper.IDGenerator;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.DataBackupHistoryService;
import com.pccw.ehunter.utility.StringUtils;

@Service("dataBackupHistoryService")
@Transactional
public class DataBackupHistoryServiceImpl  implements  DataBackupHistoryService{
	@Autowired
	private DataBackupHistoryDAO dataBackupHistoryDao;
	
	private SimpleHibernateTemplate<DataBackupHistory, String> simpleDao;
	
	@Autowired
	private IDGenerator idGenerator;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		simpleDao = new SimpleHibernateTemplate<DataBackupHistory, String>(sessionFactory, DataBackupHistory.class);
	}

	@Override
	@Transactional(readOnly=true)
	public int getBackupHistoryCountByCriteria(PagedCriteria pagedCriteria) {
		return dataBackupHistoryDao.getBackupHistoryCountByCriteria(pagedCriteria);
	}

	@Override
	@Transactional(readOnly=true)
	public List<DataBackupHistoryDTO> getBackupHistoriesByCriteria(PagedCriteria pagedCriteria) {
		List<Object> list = dataBackupHistoryDao.getBackupHistoriesByCriteria(pagedCriteria);
		List<DataBackupHistoryDTO> dtos = new ArrayList<DataBackupHistoryDTO>();
		
		if(!CollectionUtils.isEmpty(list)){
			DataBackupHistoryDTO dto = null;
			for(Object o : list){
				Object[] os = (Object[])o;
				
				dto = new DataBackupHistoryDTO();
				dto.setHistoryID(StringUtils.isEmpty((String)os[0]) ? "" : (String)os[0]);
				dto.setFileName(StringUtils.isEmpty((String)os[1]) ? "" : (String)os[1]);
				dto.setBackupDttm(os[2]==null ? (new Date()) : ((Date)os[2]));
				dto.setBackupChannel(StringUtils.isEmpty((String)os[3]) ? "" : (String)os[3]);
				dto.setBackupBy(StringUtils.isEmpty((String)os[4]) ? "" : (String)os[4]);
				
				dtos.add(dto);
			}
		}
		
		return dtos;
	}

	@Override
	@Transactional
	public void saveBackupHistory(DataBackupHistoryDTO dto) {
		dto.setHistoryID(idGenerator.generateID(IDNumberKeyConstant.BACKUP_HISTORY_SEQUENCE_KEY, null, 9));
		
		simpleDao.save(DataBackupHistoryConvertor.toPo(dto));
	}

	@Override
	@Transactional(readOnly=true)
	public DataBackupHistoryDTO getBackupHistoryByID(String id) {
		return DataBackupHistoryConvertor.toDto(simpleDao.findUniqueByProperty("historyID", id));
	}
}

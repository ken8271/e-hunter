package com.pccw.ehunter.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pccw.ehunter.convertor.CodeTableConvertor;
import com.pccw.ehunter.domain.common.CodeTable;
import com.pccw.ehunter.dto.CodeTableDTO;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.CodeTableService;

@Service("codeTableService")
@Transactional
public class CodeTableServiceImpl implements CodeTableService{
	
	private SimpleHibernateTemplate<CodeTable, String> dao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		dao = new SimpleHibernateTemplate<CodeTable, String>(sessionFactory, CodeTable.class);
	}

	@Override
	@Transactional
	public List<CodeTableDTO> getCodeTables() {
		return CodeTableConvertor.toDtos(dao.findAllUndeleted());
	}

}

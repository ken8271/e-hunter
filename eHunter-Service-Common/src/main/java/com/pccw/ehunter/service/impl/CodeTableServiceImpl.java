package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.constant.TransactionIndicator;
import com.pccw.ehunter.convertor.CodeTableConvertor;
import com.pccw.ehunter.dao.CodeTableCommonDAO;
import com.pccw.ehunter.domain.common.CodeTable;
import com.pccw.ehunter.dto.CodeTableDTO;
import com.pccw.ehunter.dto.PagedCriteria;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.CodeTableService;
import com.pccw.ehunter.utility.BaseDtoUtility;
import com.pccw.ehunter.utility.StringUtils;

@Service("codeTableService")
@Transactional
public class CodeTableServiceImpl implements CodeTableService{
	
	private SimpleHibernateTemplate<CodeTable, String> dao;
	
	@Autowired
	private CodeTableCommonDAO codeTableCommonDao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		dao = new SimpleHibernateTemplate<CodeTable, String>(sessionFactory, CodeTable.class);
	}

	@Override
	@Transactional(readOnly=true)
	public List<CodeTableDTO> getCodetablesByCriteria(PagedCriteria pagedCriteria) {
		List<Object> list = codeTableCommonDao.getCodetablesByCriteria(pagedCriteria);
		List<CodeTableDTO> dtos = new ArrayList<CodeTableDTO>();
		
		if(!CollectionUtils.isEmpty(list)){
			CodeTableDTO dto = null;
			for(Object o : list){
				dto = new CodeTableDTO();
				Object[] os = (Object[])o;
				
				dto.setId(StringUtils.isEmpty((String)os[0]) ? "" : (String)os[0]);
				dto.setName(StringUtils.isEmpty((String)os[1]) ? "" : (String)os[1]);
				dto.setDescription(StringUtils.isEmpty((String)os[2]) ? "" : (String)os[2]);
				dto.setReference(StringUtils.isEmpty((String)os[3]) ? "" : (String)os[3]);
				dto.setViewUrl(StringUtils.isEmpty((String)os[4]) ? "" : (String)os[4]);
				
				dtos.add(dto);
			}
		}
		
		return dtos;
	}

	@Override
	@Transactional(readOnly=true)
	public int getCodetablesCountByCriteria(PagedCriteria pagedCriteria) {
		return codeTableCommonDao.getCodetablesCountByCriteria(pagedCriteria);
	}

	@Override
	@Transactional(readOnly=true)
	public CodeTableDTO getCodetableByID(String id) {
		return CodeTableConvertor.toDto(dao.findUniqueByProperty("id", id));
	}

	@Override
	@Transactional
	public void updateCodetable(CodeTableDTO dto) {
		BaseDtoUtility.setCommonProperties(dto, TransactionIndicator.UPDATE);
		dao.update(CodeTableConvertor.toPo(dto));
	}

}

package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.constant.DateFormatConstant;
import com.pccw.ehunter.constant.IDNumberKeyConstant;
import com.pccw.ehunter.constant.TransactionIndicator;
import com.pccw.ehunter.convertor.InternalRoleConvertor;
import com.pccw.ehunter.convertor.InternalUserConvertor;
import com.pccw.ehunter.dao.InternalUserDAO;
import com.pccw.ehunter.domain.internal.InternalRole;
import com.pccw.ehunter.dto.InternalRoleDTO;
import com.pccw.ehunter.dto.InternalUserDTO;
import com.pccw.ehunter.dto.InternalUserPagedCriteria;
import com.pccw.ehunter.helper.IDGenerator;
import com.pccw.ehunter.hibernate.SimpleHibernateTemplate;
import com.pccw.ehunter.service.InternalUserManagementService;
import com.pccw.ehunter.utility.BaseDtoUtility;
import com.pccw.ehunter.utility.DateUtils;
import com.pccw.ehunter.utility.StringUtils;

@Service("internalUserService")
@Transactional
public class InternalUserManagementServiceImpl implements InternalUserManagementService{
	
	private static final Logger logger = LoggerFactory.getLogger(InternalUserManagementServiceImpl.class);
	
	private SimpleHibernateTemplate<InternalRole, String> dao;
	
	@Autowired
	private InternalUserDAO internalUserDao;
	
	@Autowired
	private IDGenerator idGenerator;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		dao = new SimpleHibernateTemplate<InternalRole, String>(sessionFactory, InternalRole.class);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<InternalRoleDTO> getActiveDirectoryRoles() {
		return InternalRoleConvertor.toDtos(dao.findAllUndeleted());
	}

	@Override
	@Transactional(readOnly=true)
	public List<InternalUserDTO> getInternalUsersByCriteria(InternalUserPagedCriteria pagedCriteria) {
		List<Object> list = internalUserDao.getInternalUsersByCriteria(pagedCriteria);
		List<InternalUserDTO> dtos = new ArrayList<InternalUserDTO>();
		
		if(!CollectionUtils.isEmpty(list)){
			InternalUserDTO dto = null;
			for(Object o : list){
				dto = new InternalUserDTO();
				Object[] os = (Object[])o;
				
				dto.setUserRecId(StringUtils.isEmpty((String)os[0]) ? "" : (String)os[0]);
				dto.setLoginId(StringUtils.isEmpty((String)os[1]) ? "" : (String)os[1]);
				dto.setEnName(StringUtils.isEmpty((String)os[2]) ? "" : (String)os[2]);
				dto.setCnName(StringUtils.isEmpty((String)os[3]) ? "" : (String)os[3]);
				dto.setStaffId(StringUtils.isEmpty((String)os[4]) ? "" : (String)os[4]);
				dto.setAccountStatus(StringUtils.isEmpty((String)os[5]) ? "" : (String)os[5]);
				dto.setCreateDateTime(os[6]==null ? (new Date()) : ((Date)os[6]));
				
				dtos.add(dto);
			}
		}
		
		return dtos;
	}

	@Override
	@Transactional(readOnly=true)
	public int getInternalUsersCountByCriteria(InternalUserPagedCriteria pagedCriteria) {
		return internalUserDao.getInternalUsersCountByCriteria(pagedCriteria);
	}

	@Override
	@Transactional
	public void saveInternalUser(InternalUserDTO internalUserDto) {
		try {
			internalUserDto.setUserRecId(idGenerator.generateID(IDNumberKeyConstant.INTERNAL_USER_SEQUENCE_KEY, DateUtils.formatDateTime(DateFormatConstant.DATE_YYMMDD, new Date()), 10));
			internalUserDto.setAccountStatus(CommonConstant.USER_ACCOUNT_ACTIVE);
			internalUserDto.setPasswordExpired(CommonConstant.NO);
			BaseDtoUtility.setCommonProperties(internalUserDto, TransactionIndicator.INSERT);
			
			internalUserDao.saveInternalUser(InternalUserConvertor.toPo(internalUserDto));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(">>>>>Exception Catched(saveInternalUser) : " + e.getMessage());
		}
	}
	
}

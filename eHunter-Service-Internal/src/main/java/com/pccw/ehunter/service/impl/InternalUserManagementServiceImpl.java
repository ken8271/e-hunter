package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
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
import com.pccw.ehunter.domain.internal.InternalUser;
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
	
	private SimpleHibernateTemplate<InternalRole, String> dao;
	
	private SimpleHibernateTemplate<InternalUser, String> simpleUserDao;
	
	@Autowired
	private InternalUserDAO internalUserDao;
	
	@Autowired
	private IDGenerator idGenerator;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		dao = new SimpleHibernateTemplate<InternalRole, String>(sessionFactory, InternalRole.class);
		simpleUserDao = new SimpleHibernateTemplate<InternalUser, String>(sessionFactory, InternalUser.class);
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
	public void saveInternalUser(InternalUserDTO internalUserDto){
		internalUserDto.setUserRecId(idGenerator.generateID(IDNumberKeyConstant.INTERNAL_USER_SEQUENCE_KEY, DateUtils.formatDateTime(DateFormatConstant.DATE_YYMMDD, new Date()), 10));
		internalUserDto.setAccountStatus(CommonConstant.USER_ACCOUNT_ACTIVE);
		internalUserDto.setPasswordExpired(CommonConstant.NO);
		BaseDtoUtility.setCommonProperties(internalUserDto, TransactionIndicator.INSERT);
		
		internalUserDao.saveInternalUser(InternalUserConvertor.toPo(internalUserDto));
	}

	@Override
	@Transactional(readOnly=true)
	public InternalUserDTO getInternalUserByID(String id) {
		return InternalUserConvertor.toDto(simpleUserDao.findUniqueByProperty("userRecId", id));
	}

	@Override
	@Transactional
	public void updateInternalUser(InternalUserDTO internalUserDTO) {
		BaseDtoUtility.setCommonProperties(internalUserDTO, TransactionIndicator.UPDATE);
		internalUserDao.updateInternalUser(InternalUserConvertor.toPo(internalUserDTO));
	}

	@Override
	@Transactional
	public void resetPassword(InternalUserDTO internalUserDTO) {
		BaseDtoUtility.setCommonProperties(internalUserDTO, TransactionIndicator.UPDATE);
		internalUserDao.resetPassword(InternalUserConvertor.toPo(internalUserDTO));
	}

	@Override
	@Transactional
	public void deleteInternalUser(InternalUserDTO internalUserDto) {
		internalUserDao.deleteInternalUser(InternalUserConvertor.toPo(internalUserDto));
	}

	@Override
	@Transactional(readOnly=true)
	public InternalUserDTO getInternalUserByLoginID(String loginID) {
		return InternalUserConvertor.toDto(internalUserDao.getInternalUserByLoginId(loginID));
	}
	
}

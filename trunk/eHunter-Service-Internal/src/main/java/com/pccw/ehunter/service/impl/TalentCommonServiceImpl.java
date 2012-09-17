package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.dao.TalentCommonDAO;
import com.pccw.ehunter.dto.TalentDTO;
import com.pccw.ehunter.dto.TalentPagedCriteria;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.service.TalentCommonService;
import com.pccw.ehunter.utility.StringUtils;

@Service("talentCommonService")
@Transactional
public class TalentCommonServiceImpl implements TalentCommonService{
	
	@Autowired
	private TalentCommonDAO talentCommonDao;
	
	@Autowired
	private CodeTableHelper codeTableHelper;

	@Override
	@Transactional(readOnly=true)
	public int getTalentsCountByCriteria(TalentPagedCriteria pagedCriteria) {
		return talentCommonDao.getTalentsCountByCriteria(pagedCriteria);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TalentDTO> getTalentsByCriteria(HttpServletRequest request , TalentPagedCriteria pagedCriteria) {
		List<Object> list = talentCommonDao.getTalentsByCriteria(pagedCriteria);
		List<TalentDTO> dtos = new ArrayList<TalentDTO>();
		
		if(!CollectionUtils.isEmpty(list)){
			TalentDTO tlnt = null;
			for(Object o : list){
				tlnt = new TalentDTO();
				Object[] os = (Object[])o;
				tlnt.setTalentID(StringUtils.isEmpty((String)os[0]) ? "" : (String)os[0]);
				tlnt.setCnName(StringUtils.isEmpty((String)os[1]) ? "" : (String)os[1]);
				tlnt.setEnName(StringUtils.isEmpty((String)os[2]) ? "" : (String)os[2]);
				tlnt.setDegreeDto(codeTableHelper.getDegreeByCode(request, StringUtils.isEmpty((String)os[3]) ? "" : (String)os[3]));
				tlnt.setNowLivePlace(StringUtils.isEmpty((String)os[4]) ? "" : (String)os[4]);
				
				dtos.add(tlnt);
			}
		}
		return dtos;
	}

	@Override
	@Transactional(readOnly=true)
	public int getCandidatesCountByCriteria(TalentPagedCriteria pagedCriteria) {
		return 0;
	}

	@Override
	@Transactional(readOnly=true)
	public List<TalentDTO> getCandidatesByCriteria(HttpServletRequest request,
			TalentPagedCriteria pagedCriteria) {
		return null;
	}

}

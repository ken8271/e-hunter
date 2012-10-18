package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.convertor.SimpleDateConvertor;
import com.pccw.ehunter.dao.TalentCommonDAO;
import com.pccw.ehunter.dto.EmploymentHistoryDTO;
import com.pccw.ehunter.dto.PositionDTO;
import com.pccw.ehunter.dto.ProjectDTO;
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
				tlnt.setCreateDateTime(os[5]==null ? (new Date()) : ((Date)os[5]));
				
				dtos.add(tlnt);
			}
		}
		
		if(!CollectionUtils.isEmpty(dtos)){
			for(TalentDTO tlnt : dtos){
				tlnt.setEmploymentHistoryDtos(getEmploymentHistoriesByTalentID(tlnt.getTalentID()));
			}
		}
		return dtos;
	}
	
	private List<EmploymentHistoryDTO> getEmploymentHistoriesByTalentID(String talentID){
		List<Object> list = talentCommonDao.getEmploymentHistoriesByTalentID(talentID);
		List<EmploymentHistoryDTO> histories = new ArrayList<EmploymentHistoryDTO>();
		
		if(!CollectionUtils.isEmpty(list)){
			EmploymentHistoryDTO hst = null;
			for(Object o : list){
				hst = new EmploymentHistoryDTO();
				Object[] os = (Object[])o;
				hst.setBeginTimeDto(SimpleDateConvertor.toSimpleDate((Date)os[0]));
				hst.setEndTimeDto(SimpleDateConvertor.toSimpleDate((Date)os[1]));
				hst.setCompanyName(StringUtils.isEmpty((String)os[2]) ? "" : (String)os[2]);
				hst.setPositions(StringUtils.isEmpty((String)os[3]) ? "" : (String)os[3]);
				initialPositionByCode(hst);
				
				histories.add(hst);
			}
		}
		
		return histories;
	}
	
	private void initialPositionByCode(EmploymentHistoryDTO employmentHistoryDto){
		List<PositionDTO> positionDtos = new ArrayList<PositionDTO>();
		if(!StringUtils.isEmpty(employmentHistoryDto.getPositions())){
			String[] positions = StringUtils.tokenize(employmentHistoryDto.getPositions(), CommonConstant.COMMA);
			if(positions != null && positions.length != 0){
				for(String code : positions){
					positionDtos.add(codeTableHelper.getPositionByCode(code));
				}
			}
		}
		employmentHistoryDto.setPositionDtos(positionDtos);
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

	@Override
	@Transactional(readOnly=true)
	public List<ProjectDTO> getParticipatedProjectByTalentID(String talentID) {
		List<ProjectDTO> dtos = new ArrayList<ProjectDTO>();
		List<Object> pos = talentCommonDao.getParticipatedProjectByTalentID(talentID);
		
		if(!CollectionUtils.isEmpty(pos)){
			ProjectDTO dto = null;
			for(Object o : pos){
				dto = new ProjectDTO();
				Object[] os = (Object[])o;
				dto.setSystemProjectRefNum(StringUtils.isEmpty((String)os[0]) ? "" : (String)os[0]);
				dto.setProjectName(StringUtils.isEmpty((String)os[1]) ? "" : (String)os[1]);
				
				dtos.add(dto);
			}
		}
		
		return dtos;
	}

}

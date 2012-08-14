package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.dao.TalentCommonDAO;
import com.pccw.ehunter.dto.BaseLabelValueDTO;
import com.pccw.ehunter.service.TalentCommonService;
import com.pccw.ehunter.utility.StringUtils;

@Service("talentCommonService")
@Transactional
public class TalentCommonServiceImpl implements TalentCommonService{
	
	@Autowired
	private TalentCommonDAO talentCommonDao;

	@Override
	@Transactional(readOnly=true)
	public List<BaseLabelValueDTO> loadTalentSource() {
		List<BaseLabelValueDTO> srcs = new ArrayList<BaseLabelValueDTO>();
		List<Object> list = talentCommonDao.getTalentSource();
		
		if(CollectionUtils.isEmpty(list)){
			return srcs;
		}
		
		for(Object o : list){
			Object[] objs = (Object[])o;
			BaseLabelValueDTO lv = new BaseLabelValueDTO();
			lv.setValue(StringUtils.isEmpty((String)objs[0]) ? "" : (String)objs[0]);
			lv.setLabel(StringUtils.isEmpty((String)objs[1]) ? "" : (String)objs[1]);
			srcs.add(lv);
		}
		
		return srcs;
	}

	@Override
	public List<BaseLabelValueDTO> loadSubjectsByType(String typeCode) {
		List<BaseLabelValueDTO> subjs = new ArrayList<BaseLabelValueDTO>();
		List<Object> list = talentCommonDao.getSubjectsByType(typeCode);
		
		if(CollectionUtils.isEmpty(list)){
			return subjs;
		}
		
		for(Object o : list){
			Object[] objs = (Object[])o;
			BaseLabelValueDTO lv = new BaseLabelValueDTO();
			lv.setValue(StringUtils.isEmpty((String)objs[0]) ? "" : (String)objs[0]);
			lv.setLabel(StringUtils.isEmpty((String)objs[1]) ? "" : (String)objs[1]);
			subjs.add(lv);
		}
		
		return subjs;
	}

}

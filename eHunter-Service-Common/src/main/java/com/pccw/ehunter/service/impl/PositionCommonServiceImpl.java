package com.pccw.ehunter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.convertor.PositionTypeConvertor;
import com.pccw.ehunter.dao.PositionCommonDAO;
import com.pccw.ehunter.domain.common.PositionSubType;
import com.pccw.ehunter.domain.common.PositionType;
import com.pccw.ehunter.dto.BaseLabelValueDTO;
import com.pccw.ehunter.service.PositionCommonService;

@Service("positionCommonService")
@Transactional
public class PositionCommonServiceImpl implements PositionCommonService{
	
	@Autowired
	private PositionCommonDAO positionDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<BaseLabelValueDTO> loadPositionTypes() {
		List<BaseLabelValueDTO> lvs = new ArrayList<BaseLabelValueDTO>();
		List<PositionType> types = positionDao.loadPositionTypes();
		
		if(!CollectionUtils.isEmpty(types)){
			for(PositionType type : types){
				BaseLabelValueDTO dto = PositionTypeConvertor.toSelectOption(type);
				if(null != dto){
					lvs.add(dto);
				}
			}
		}
		return lvs;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<BaseLabelValueDTO> loadPositionTypeByParentCode(String code) {
		List<BaseLabelValueDTO> lvs = new ArrayList<BaseLabelValueDTO>();
		List<PositionSubType> subTypes = positionDao.loadSubTypesByParentCode(code);
		
		if(!CollectionUtils.isEmpty(subTypes)){
			for(PositionSubType type : subTypes){
				BaseLabelValueDTO dto = PositionTypeConvertor.toSelectOption(type);
				if(null != dto){
					lvs.add(dto);
				}
			}
		}
 		return lvs;
	}
}

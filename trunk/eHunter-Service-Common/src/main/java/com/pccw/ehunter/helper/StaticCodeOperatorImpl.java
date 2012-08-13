package com.pccw.ehunter.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.pccw.ehunter.dao.CodeTableCommonDAO;
import com.pccw.ehunter.dto.BaseLabelValueDTO;
import com.pccw.ehunter.utility.StringUtils;

@Component("staticCodeOperator")
public class StaticCodeOperatorImpl implements StaticCodeOperator{
	
	@Autowired
	private CodeTableCommonDAO codeTableCommonDao;

	@Override
	public List<BaseLabelValueDTO> getStaticCodes(String tableKey , String codeKey) {
		List<BaseLabelValueDTO> lvs = new ArrayList<BaseLabelValueDTO>();
		List<Object> list = codeTableCommonDao.getCodes(tableKey, codeKey);
		
		if(CollectionUtils.isEmpty(list)){
			return lvs;
		}
		
		BaseLabelValueDTO lv = null;
		for(Object o : list){
			Object[] objs = (Object[])o;
			lv = new BaseLabelValueDTO();
			lv.setValue(StringUtils.isEmpty((String)objs[0]) ? "" : (String)objs[0]);
			lv.setLabel(StringUtils.isEmpty((String)objs[1]) ? "" : (String)objs[1]);
			lvs.add(lv);
		}
		
		return lvs;
	}

}

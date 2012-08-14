package com.pccw.ehunter.helper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.WebUtils;

import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.dto.DegreeDTO;
import com.pccw.ehunter.dto.SubjectTypeDTO;
import com.pccw.ehunter.service.DegreeService;
import com.pccw.ehunter.service.SubjectTypeService;

@Component("codeTableHelper")
public class CodeTableHelper {
	
	private Logger logger = LoggerFactory.getLogger(CodeTableHelper.class);
	
	@Autowired
	private SubjectTypeService subjectTypeService;
	
	@Autowired
	private DegreeService degreeService;
	
	@SuppressWarnings("unchecked")
	public List<SubjectTypeDTO> getAllSubjectTypes(HttpServletRequest request){
		List<SubjectTypeDTO> types = (List<SubjectTypeDTO>)WebUtils.getSessionAttribute(request, WebConstant.LIST_OF_SUBJECT_TYPE);
		if(CollectionUtils.isEmpty(types)){
			types = subjectTypeService.getAllSubjectTypes();
			if(CollectionUtils.isEmpty(types)){
				types = new ArrayList<SubjectTypeDTO>();
			}
			WebUtils.setSessionAttribute(request, WebConstant.LIST_OF_SUBJECT_TYPE, types);
		}else{
			logger.debug("retrieved from cache.");
		}
		
		return types;
	}
	
	@SuppressWarnings("unchecked")
	public List<DegreeDTO> getAllDegrees(HttpServletRequest request){
		List<DegreeDTO> dgres = (List<DegreeDTO>)WebUtils.getSessionAttribute(request, WebConstant.LIST_OF_DEGREE);
		if(CollectionUtils.isEmpty(dgres)){
			dgres = degreeService.getAllDegrees();
			if(CollectionUtils.isEmpty(dgres)){
				dgres = new ArrayList<DegreeDTO>();
			}
			WebUtils.setSessionAttribute(request, WebConstant.LIST_OF_DEGREE, dgres);
		}else{
			logger.debug("retrieved from cache.");
		}
		
		return dgres;
	}
	
}

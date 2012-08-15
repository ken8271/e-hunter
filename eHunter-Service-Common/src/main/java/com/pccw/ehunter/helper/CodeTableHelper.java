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
import com.pccw.ehunter.dto.SubjectDTO;
import com.pccw.ehunter.dto.SubjectTypeDTO;
import com.pccw.ehunter.dto.TalentSourceDTO;
import com.pccw.ehunter.service.DegreeService;
import com.pccw.ehunter.service.SubjectService;
import com.pccw.ehunter.service.SubjectTypeService;
import com.pccw.ehunter.service.TalentSourceService;

@Component("codeTableHelper")
public class CodeTableHelper {
	
	private Logger logger = LoggerFactory.getLogger(CodeTableHelper.class);
	
	@Autowired
	private SubjectTypeService subjectTypeService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private DegreeService degreeService;
	
	@Autowired
	private TalentSourceService talentSourceService;
	
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
	
	public List<SubjectDTO> getSubjectsByType(String type){
		return subjectService.getSubjectsByType(type);
	}
	
	public SubjectDTO getSubjectByCode(String code){
		return subjectService.getSubjectByCode(code);
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
	
	public DegreeDTO getDegreeByCode(HttpServletRequest request , String code){
		List<DegreeDTO> dgres = getAllDegrees(request);
		
		if(!CollectionUtils.isEmpty(dgres)){
			for(DegreeDTO dto : dgres){
				if(dto != null && code.equals(dto.getDegreeCode())){
					return dto;
				}
			}
		}
		
		DegreeDTO dgre = new DegreeDTO();
		dgre.setDegreeCode(code);
		return dgre;
	}
	
	@SuppressWarnings("unchecked")
	public List<TalentSourceDTO> getAllTalentSources(HttpServletRequest request){
		List<TalentSourceDTO> srcs = (List<TalentSourceDTO>)WebUtils.getSessionAttribute(request, WebConstant.LIST_OF_TALENT_SOURCE);
		if(CollectionUtils.isEmpty(srcs)){
			srcs = talentSourceService.getAllTalentSources();
			if(CollectionUtils.isEmpty(srcs)){
				srcs = new ArrayList<TalentSourceDTO>();
			}
			WebUtils.setSessionAttribute(request, WebConstant.LIST_OF_TALENT_SOURCE, srcs);
		}else{
			logger.debug("retrieved from cache.");
		}
		
		return srcs;
	}
	
}

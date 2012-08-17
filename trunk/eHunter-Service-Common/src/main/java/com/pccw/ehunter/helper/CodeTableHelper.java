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
import com.pccw.ehunter.dto.CompanyCategoryDTO;
import com.pccw.ehunter.dto.CompanySizeDTO;
import com.pccw.ehunter.dto.DegreeDTO;
import com.pccw.ehunter.dto.IndustryCategoryDTO;
import com.pccw.ehunter.dto.IndustryDTO;
import com.pccw.ehunter.dto.PositionCategoryDTO;
import com.pccw.ehunter.dto.PositionDTO;
import com.pccw.ehunter.dto.SubjectDTO;
import com.pccw.ehunter.dto.SubjectCategoryDTO;
import com.pccw.ehunter.dto.TalentSourceDTO;
import com.pccw.ehunter.service.CompanyCategoryService;
import com.pccw.ehunter.service.DegreeService;
import com.pccw.ehunter.service.IndustryCommonService;
import com.pccw.ehunter.service.PositionCommonService;
import com.pccw.ehunter.service.SubjectCommonService;
import com.pccw.ehunter.service.TalentSourceService;

@Component("codeTableHelper")
public class CodeTableHelper {
	
	private Logger logger = LoggerFactory.getLogger(CodeTableHelper.class);
	
	@Autowired
	private SubjectCommonService subjectCommonService;
	
	@Autowired
	private DegreeService degreeService;
	
	@Autowired
	private TalentSourceService talentSourceService;
	
	@Autowired
	private IndustryCommonService industryCommonService;
	
	@Autowired
	private PositionCommonService positionCommonService;
	
	@Autowired
	private CompanyCategoryService companyCategoryService;
	
	@SuppressWarnings("unchecked")
	public List<SubjectCategoryDTO> getAllSubjectTypes(HttpServletRequest request){
		List<SubjectCategoryDTO> types = (List<SubjectCategoryDTO>)WebUtils.getSessionAttribute(request, WebConstant.LIST_OF_SUBJECT_TYPE);
		if(CollectionUtils.isEmpty(types)){
			types = subjectCommonService.getAllSubjectTypes();
			if(CollectionUtils.isEmpty(types)){
				types = new ArrayList<SubjectCategoryDTO>();
			}
			WebUtils.setSessionAttribute(request, WebConstant.LIST_OF_SUBJECT_TYPE, types);
		}else{
			logger.debug("retrieved from cache.");
		}
		
		return types;
	}
	
	public List<SubjectDTO> getSubjectsByType(String type){
		return subjectCommonService.getSubjectsByType(type);
	}
	
	public SubjectDTO getSubjectByCode(String code){
		return subjectCommonService.getSubjectByCode(code);
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
	
	@SuppressWarnings("unchecked")
	public List<IndustryCategoryDTO> getIndustryCategories(HttpServletRequest request){
		List<IndustryCategoryDTO> categories = (List<IndustryCategoryDTO>)WebUtils.getSessionAttribute(request, WebConstant.LIST_OF_INDUSTRY_CATEGORY);
		
		if(CollectionUtils.isEmpty(categories)){
			categories = industryCommonService.getIndustryCategories();
			if(CollectionUtils.isEmpty(categories)){
				categories = new ArrayList<IndustryCategoryDTO>();
			}
			WebUtils.setSessionAttribute(request, WebConstant.LIST_OF_INDUSTRY_CATEGORY, categories);
		}else {
			logger.debug("retrieved from cache.");
		}
		
		return categories;
	}
	
	public List<IndustryDTO> getIndustriesByCategory(String categoryCode){
		return industryCommonService.getIndustriesByCategory(categoryCode);
	}
	
	public IndustryDTO getIndustryByCode(String code){
		return industryCommonService.getIndustryByCode(code);
	}
	
	@SuppressWarnings("unchecked")
	public List<PositionCategoryDTO> getPositionCategories(HttpServletRequest request){
		List<PositionCategoryDTO> categories = (List<PositionCategoryDTO>)WebUtils.getSessionAttribute(request, WebConstant.LIST_OF_POSITION_CATEGORY);
		
		if(CollectionUtils.isEmpty(categories)){
			categories = positionCommonService.getPositionCategories();
			if(CollectionUtils.isEmpty(categories)){
				categories = new ArrayList<PositionCategoryDTO>();
			}
			WebUtils.setSessionAttribute(request, WebConstant.LIST_OF_POSITION_CATEGORY, categories);
		} else {
			logger.debug("retrieved from cache.");
		}
		
		return categories;
	}
	
	public List<PositionDTO> getPositionsByCategory(String categoryCode){
		return positionCommonService.getPositionsByCategory(categoryCode);
	}
	
	public PositionDTO getPositionByCode(String code){
		return positionCommonService.getPositionByCode(code);
	}
	
	@SuppressWarnings("unchecked")
	public List<CompanyCategoryDTO> getCompanyCategories(HttpServletRequest request){
		List<CompanyCategoryDTO> categories = (List<CompanyCategoryDTO>)WebUtils.getSessionAttribute(request, WebConstant.LIST_OF_COMPANY_CATEGORY);
		
		if(CollectionUtils.isEmpty(categories)){
			categories = companyCategoryService.getCompanyCategories();
			
			if(CollectionUtils.isEmpty(categories)){
				categories = new ArrayList<CompanyCategoryDTO>();
			}
			
			WebUtils.setSessionAttribute(request, WebConstant.LIST_OF_COMPANY_CATEGORY, categories);
		}else {
			logger.debug("retrieved from cache.");
		}
		return categories;
	}
	
	@SuppressWarnings("unchecked")
	public List<CompanySizeDTO> getCompanySizes(HttpServletRequest request){
		List<CompanySizeDTO> sizes = (List<CompanySizeDTO>)WebUtils.getSessionAttribute(request, WebConstant.LIST_OF_COMPANY_SIZE);
		
		if(CollectionUtils.isEmpty(sizes)){
			sizes = companyCategoryService.getCompanySizes();
			
			if(CollectionUtils.isEmpty(sizes)){
				sizes = new ArrayList<CompanySizeDTO>();
			}
			
			WebUtils.setSessionAttribute(request, WebConstant.LIST_OF_COMPANY_SIZE, sizes);
		}else {
			logger.debug("retrieved from cache.");
		}
		return sizes;
	}
	
}

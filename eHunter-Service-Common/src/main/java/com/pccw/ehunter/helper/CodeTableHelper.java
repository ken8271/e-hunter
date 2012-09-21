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
import com.pccw.ehunter.dto.AnnualLeaveWelfareDTO;
import com.pccw.ehunter.dto.CandidateStatusDTO;
import com.pccw.ehunter.dto.CityDTO;
import com.pccw.ehunter.dto.CompanyCategoryDTO;
import com.pccw.ehunter.dto.CompanySizeDTO;
import com.pccw.ehunter.dto.DegreeDTO;
import com.pccw.ehunter.dto.IndustryCategoryDTO;
import com.pccw.ehunter.dto.IndustryDTO;
import com.pccw.ehunter.dto.LanguageCategoryDTO;
import com.pccw.ehunter.dto.PositionCategoryDTO;
import com.pccw.ehunter.dto.PositionDTO;
import com.pccw.ehunter.dto.ProjectStatusDTO;
import com.pccw.ehunter.dto.ProvinceDTO;
import com.pccw.ehunter.dto.ResidentialWelfareDTO;
import com.pccw.ehunter.dto.SalaryCategoryDTO;
import com.pccw.ehunter.dto.SkillCategoryDTO;
import com.pccw.ehunter.dto.SkillLevelDTO;
import com.pccw.ehunter.dto.SocietyWelfareDTO;
import com.pccw.ehunter.dto.SubjectDTO;
import com.pccw.ehunter.dto.SubjectCategoryDTO;
import com.pccw.ehunter.dto.TalentSourceDTO;
import com.pccw.ehunter.service.CandidateStatusService;
import com.pccw.ehunter.service.CompanyCategoryService;
import com.pccw.ehunter.service.DegreeService;
import com.pccw.ehunter.service.IndustryCommonService;
import com.pccw.ehunter.service.LanguageCommonService;
import com.pccw.ehunter.service.PositionCommonService;
import com.pccw.ehunter.service.ProjectStatusService;
import com.pccw.ehunter.service.RegionCommonService;
import com.pccw.ehunter.service.SkillCategoryService;
import com.pccw.ehunter.service.SkillLevelService;
import com.pccw.ehunter.service.SubjectCommonService;
import com.pccw.ehunter.service.TalentSourceService;
import com.pccw.ehunter.service.WelfareCommonService;
import com.pccw.ehunter.utility.StringUtils;

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
	
	@Autowired
	private SkillCategoryService skillCategoryService;
	
	@Autowired
	private SkillLevelService skillLevelService;
	
	@Autowired
	private LanguageCommonService languageService;
	
	@Autowired
	private RegionCommonService regionCommonService;
	
	@Autowired
	private WelfareCommonService welfareCommonService;
	
	@Autowired
	private ProjectStatusService projectStatusService;
	
	@Autowired
	private CandidateStatusService candidateStatusService;
	
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
	
	public SubjectCategoryDTO getSubjectCatgoryByCode(HttpServletRequest request , String code){
		List<SubjectCategoryDTO> categories = getAllSubjectTypes(request);
		
		if(!CollectionUtils.isEmpty(categories)){
			for(SubjectCategoryDTO dto : categories){
				if(code.equals(dto.getTypeCode())){
					return dto;
				}
			}
		}
		
		SubjectCategoryDTO dto = new SubjectCategoryDTO();
		dto.setTypeCode(code);
		
		return dto;
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
	public List<TalentSourceDTO> getTalentSources(HttpServletRequest request){
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
	
	public TalentSourceDTO getTalentSource(HttpServletRequest request , String code){
		List<TalentSourceDTO> srcs = getTalentSources(request);
		
		if(!CollectionUtils.isEmpty(srcs)){
			for(TalentSourceDTO src : srcs){
				if(code.equals(src.getSourceId())){
					return src;
				}
			}
		}
		
		TalentSourceDTO dto = new TalentSourceDTO();
		dto.setSourceId(code);
		return dto;
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
	
	public IndustryCategoryDTO getIndustryCategoryByCode(HttpServletRequest request , String code){
		List<IndustryCategoryDTO> categories = getIndustryCategories(request);
		
		if(!CollectionUtils.isEmpty(categories)){
			for(IndustryCategoryDTO dto : categories){
				if(code.equals(dto.getCategoryCode())){
					return dto;
				}
			}
		}
		
		IndustryCategoryDTO ic = new IndustryCategoryDTO();
		ic.setCategoryCode(code);
		return ic;
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
	
	public PositionCategoryDTO getPositionCategoryByCode(HttpServletRequest request , String code){
		List<PositionCategoryDTO> categories = getPositionCategories(request);
		
		if(!CollectionUtils.isEmpty(categories)){
			for(PositionCategoryDTO dto : categories){
				if(code.equals(dto.getTypeCode())){
					return dto;
				}
			}
		}
		
		PositionCategoryDTO pc = new PositionCategoryDTO();
		pc.setTypeCode(code);
		
		return pc;
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
	
	public CompanyCategoryDTO getCompanyCategoryByCode(HttpServletRequest request , String code){
		List<CompanyCategoryDTO> categories = getCompanyCategories(request);
		
		if(!CollectionUtils.isEmpty(categories)){
			for(CompanyCategoryDTO dto : categories){
				if(code.equals(dto.getCategoryCode())){
					return dto;
				}
			}
		}
		
		CompanyCategoryDTO cc = new CompanyCategoryDTO();
		cc.setCategoryCode(code);
		
		return cc;
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
	
	public CompanySizeDTO getCompanySizeByCode(HttpServletRequest request , String code){
		List<CompanySizeDTO> sizes = getCompanySizes(request);
		
		if(!CollectionUtils.isEmpty(sizes)){
			for(CompanySizeDTO dto : sizes){
				if(code.equals(dto.getSizeCode())){
					return dto;
				}
			}
		}
		
		CompanySizeDTO cs = new CompanySizeDTO();
		cs.setSizeCode(code);
		
		return cs;
	}
	
	@SuppressWarnings("unchecked")
	public List<SkillCategoryDTO> getSkillCategories(HttpServletRequest request){
		List<SkillCategoryDTO> categories = (List<SkillCategoryDTO>)WebUtils.getSessionAttribute(request, WebConstant.LIST_OF_SKILL_CATEGORY);
		
		if(CollectionUtils.isEmpty(categories)){
			categories = skillCategoryService.getSkillCategories();
			WebUtils.setSessionAttribute(request, WebConstant.LIST_OF_SKILL_CATEGORY, categories);
		}else {
			logger.debug("retrieved from cache.");
		}
		return categories;
	}
	
	public SkillCategoryDTO getSkillCategoryByCode(HttpServletRequest request , String code){
		List<SkillCategoryDTO> categories = getSkillCategories(request);
		
		if(!CollectionUtils.isEmpty(categories)){
			for(SkillCategoryDTO dto : categories){
				if(code.equals(dto.getCode())){
					return dto;
				}
			}
		}
		
		SkillCategoryDTO sc = new SkillCategoryDTO();
		sc.setCode(code);
		
		return sc;
	}
	
	@SuppressWarnings("unchecked")
	public List<SkillLevelDTO> getSkillLevels(HttpServletRequest request){
		List<SkillLevelDTO> levels = (List<SkillLevelDTO>)WebUtils.getSessionAttribute(request, WebConstant.LIST_OF_SKILL_LEVEL);
		
		if(CollectionUtils.isEmpty(levels)){
			levels = skillLevelService.getSkillLevels();
			WebUtils.setSessionAttribute(request, WebConstant.LIST_OF_SKILL_LEVEL, levels);
		} else {
			logger.debug("retrieved from cache.");
		}
		return levels;
	}
	
	public SkillLevelDTO getSkillLevelByCode(HttpServletRequest request , String code){
		List<SkillLevelDTO> levels = getSkillLevels(request);
		
		if(!CollectionUtils.isEmpty(levels)){
			for(SkillLevelDTO dto : levels){
				if(code.equals(dto.getCode())){
					return dto;
				}
			}
		}
		
		SkillLevelDTO sl = new SkillLevelDTO();
		sl.setCode(code);
		
		return sl;
	}
	
	@SuppressWarnings("unchecked")
	public List<LanguageCategoryDTO> getLanguageCategories(HttpServletRequest request){
		List<LanguageCategoryDTO> categories = (List<LanguageCategoryDTO>)WebUtils.getSessionAttribute(request, WebConstant.LIST_OF_LANGUAGE_CATEGORY);
		
		if(CollectionUtils.isEmpty(categories)){
			categories = languageService.getLanguageCategories();
			WebUtils.setSessionAttribute(request, WebConstant.LIST_OF_LANGUAGE_CATEGORY, categories);
		}else {
			logger.debug("retrieved from cache.");
		}
		return categories;
	}
	
	public LanguageCategoryDTO getLanguageCategoryByCode(HttpServletRequest request , String code){
		List<LanguageCategoryDTO> categories = getLanguageCategories(request);
		
		if(!CollectionUtils.isEmpty(categories)){
			for(LanguageCategoryDTO dto : categories){
				if(code.equals(dto.getCode())){
					return dto;
				}
			}
		}
		
		LanguageCategoryDTO lc = new LanguageCategoryDTO();
		lc.setCode(code);
		
		return lc;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProvinceDTO> getProvinces(HttpServletRequest request){
		List<ProvinceDTO> provinces = (List<ProvinceDTO>)WebUtils.getSessionAttribute(request, WebConstant.LIST_OF_PROVINCE);
		
		if(CollectionUtils.isEmpty(provinces)){
			provinces = regionCommonService.getProvinces();
			WebUtils.setSessionAttribute(request, WebConstant.LIST_OF_PROVINCE, provinces);
		} else {
			logger.debug("retrieved from cache.");
		}
		
		return provinces;
	}
	
	public ProvinceDTO getProvinceByCode(HttpServletRequest request , String code){
		List<ProvinceDTO> provinces = getProvinces(request);
		
		if(!CollectionUtils.isEmpty(provinces)){
			for(ProvinceDTO dto : provinces){
				if(code.equals(dto.getProvinceCode())){
					return dto;
				}
			}
		}
		
		ProvinceDTO dto = new ProvinceDTO();
		dto.setProvinceCode(code);
		
		return dto;
	}
	
	public List<CityDTO> getCitiesByProvinceCode(String provinceCode){
		return regionCommonService.getCitiesByProvinceCode(provinceCode);
	}
	
	public CityDTO getCityByCode(String cityCode){
		return regionCommonService.getCityByCode(cityCode);
	}
	
	@SuppressWarnings("unchecked")
	public List<AnnualLeaveWelfareDTO> getAnnualLeaveWelfares(HttpServletRequest request){
		List<AnnualLeaveWelfareDTO> welfares = (List<AnnualLeaveWelfareDTO>)WebUtils.getSessionAttribute(request, WebConstant.LIST_OF_ANNUAL_LEAVE_WELFARE);
		
		if(CollectionUtils.isEmpty(welfares)){
			welfares = welfareCommonService.getAnnualLeaveWelfares();
			WebUtils.setSessionAttribute(request, WebConstant.LIST_OF_ANNUAL_LEAVE_WELFARE, welfares);
		}else {
			logger.debug("retrieved from cache.");
		}
		
		return welfares;
	}
	
	public AnnualLeaveWelfareDTO getAnnualLeaveWelfareByCode(HttpServletRequest request , String code){
		List<AnnualLeaveWelfareDTO> welfares = getAnnualLeaveWelfares(request);
		
		if(!CollectionUtils.isEmpty(welfares)){
			for(AnnualLeaveWelfareDTO welfareDto : welfares){
				if(code.equals(welfareDto.getWelfareCode())){
					return welfareDto;
				}
			}
		}
		
		AnnualLeaveWelfareDTO dto = new AnnualLeaveWelfareDTO();
		dto.setWelfareCode(code);
		
		return dto;
	}
	
	@SuppressWarnings("unchecked")
	public List<SalaryCategoryDTO> getSalaryCategories(HttpServletRequest request){
		List<SalaryCategoryDTO> categories = (List<SalaryCategoryDTO>)WebUtils.getSessionAttribute(request, WebConstant.LIST_OF_SALARY_CATEGORY);
		
		if(CollectionUtils.isEmpty(categories)){
			categories = welfareCommonService.getSalaryCategories();
			WebUtils.setSessionAttribute(request, WebConstant.LIST_OF_SALARY_CATEGORY, categories);
		}else {
			logger.debug("retrieved from cache.");
		}
		
		return categories;
	}
	
	public SalaryCategoryDTO getSalaryCategoryByCode(HttpServletRequest request , String code){
		List<SalaryCategoryDTO> categories = getSalaryCategories(request);
		
		if(!CollectionUtils.isEmpty(categories)){
			for(SalaryCategoryDTO dto : categories){
				if(code.equals(dto.getCategoryCode())){
					return dto;
				}
			}
		}
		
		SalaryCategoryDTO dto = new SalaryCategoryDTO();
		dto.setCategoryCode(code);
		
		return dto;
	}
	
	@SuppressWarnings("unchecked")
	public List<ResidentialWelfareDTO> getResidentialWelfares(HttpServletRequest request){
		List<ResidentialWelfareDTO> welfares = (List<ResidentialWelfareDTO>)WebUtils.getSessionAttribute(request, WebConstant.LIST_OF_RESIDENTIAL_WELFARE);
		
		if(CollectionUtils.isEmpty(welfares)){
			welfares = welfareCommonService.getResidentialWelfares();
			WebUtils.setSessionAttribute(request, WebConstant.LIST_OF_RESIDENTIAL_WELFARE, welfares);
		}else {
			logger.debug("retrieved from cache.");
		}
		
		return welfares;
	}
	
	public ResidentialWelfareDTO getResidentialWelfareByCode(HttpServletRequest request , String code){
		List<ResidentialWelfareDTO> welfares = getResidentialWelfares(request);
		
		if(!CollectionUtils.isEmpty(welfares)){
			for(ResidentialWelfareDTO dto : welfares){
				if(code.equals(dto.getWelfareCode())){
					return dto;
				}
			}
		}
		
		ResidentialWelfareDTO dto = new ResidentialWelfareDTO();
		dto.setWelfareCode(code);
		
		return dto;
	}
	
	@SuppressWarnings("unchecked")
	public List<SocietyWelfareDTO> getSocietyWelfares(HttpServletRequest request){
		List<SocietyWelfareDTO> welfares = (List<SocietyWelfareDTO>)WebUtils.getSessionAttribute(request, WebConstant.LIST_OF_SOCIETY_WELFARE);
		
		if(CollectionUtils.isEmpty(welfares)){
			welfares = welfareCommonService.getSocietyWelfares();
			WebUtils.setSessionAttribute(request, WebConstant.LIST_OF_SOCIETY_WELFARE, welfares);
		}else {
			logger.debug("retrieved from cache.");
		}
		
		return welfares;
	}
	
	public SocietyWelfareDTO getSocietyWelfareByCode(HttpServletRequest request , String code){
		List<SocietyWelfareDTO> welfares = getSocietyWelfares(request);
		
		if(!CollectionUtils.isEmpty(welfares)){
			for(SocietyWelfareDTO dto : welfares){
				if(code.equals(dto.getWelfareCode())){
					return dto;
				}
			}
		}
		
		SocietyWelfareDTO dto = new SocietyWelfareDTO();
		dto.setWelfareCode(code);
		
		return dto;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectStatusDTO> getProjectStatus(HttpServletRequest request){
		List<ProjectStatusDTO> statuses = (List<ProjectStatusDTO>)WebUtils.getSessionAttribute(request, WebConstant.LIST_OF_PROJECT_STATUS);
		
		if(CollectionUtils.isEmpty(statuses)){
			statuses = projectStatusService.getProjectStatus();
			WebUtils.setSessionAttribute(request, WebConstant.LIST_OF_PROJECT_STATUS, statuses);
		}else{
			logger.debug("retrieved from cache.");
		}
		
		return statuses;
	}
	
	public ProjectStatusDTO getProjectStatusByCode(HttpServletRequest request , String code){
		List<ProjectStatusDTO> statuses = getProjectStatus(request);
		
		if(!CollectionUtils.isEmpty(statuses)){
			for(ProjectStatusDTO dto : statuses){
				if(code.equals(dto.getStatusCode())){
					return dto;
				}
			}
		}
		
		ProjectStatusDTO dto = new ProjectStatusDTO();
		dto.setStatusCode(code);
		return dto;
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateStatusDTO> getCandidateStatuses(HttpServletRequest request){
		List<CandidateStatusDTO> statuses = (List<CandidateStatusDTO>)WebUtils.getSessionAttribute(request, WebConstant.LIST_OF_CANDIDATE_STATUS);
		
		if(CollectionUtils.isEmpty(statuses)){
			statuses = candidateStatusService.getCandidateStatus();
			WebUtils.setSessionAttribute(request, WebConstant.LIST_OF_CANDIDATE_STATUS, statuses);
		}else {
			logger.debug("retrieved from cache.");
		}
		
		return statuses;
	}
	
	public CandidateStatusDTO getCandidateStatusByCode(HttpServletRequest request , String code){
		List<CandidateStatusDTO> statuses = getCandidateStatuses(request);
		
		if(!CollectionUtils.isEmpty(statuses)){
			for(CandidateStatusDTO dto : statuses){
				if(code.equals(dto.getStatusCode())){
					return dto;
				}
			}
		}
		
		CandidateStatusDTO dto = new CandidateStatusDTO();
		dto.setStatusCode(code);
		dto.setDisplayName(StringUtils.EMPTY_STRING);
		return dto;
	}
}

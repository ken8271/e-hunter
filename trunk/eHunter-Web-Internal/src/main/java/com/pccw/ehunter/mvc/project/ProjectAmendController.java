package com.pccw.ehunter.mvc.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.ModuleIndicator;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.dto.AnnualLeaveWelfareDTO;
import com.pccw.ehunter.dto.DegreeDTO;
import com.pccw.ehunter.dto.IndustryCategoryDTO;
import com.pccw.ehunter.dto.PositionCategoryDTO;
import com.pccw.ehunter.dto.PositionDescriptionDTO;
import com.pccw.ehunter.dto.PositionRequirementDTO;
import com.pccw.ehunter.dto.ProjectDTO;
import com.pccw.ehunter.dto.ProvinceDTO;
import com.pccw.ehunter.dto.ResidentialWelfareDTO;
import com.pccw.ehunter.dto.SalaryCategoryDTO;
import com.pccw.ehunter.dto.SocietyWelfareDTO;
import com.pccw.ehunter.dto.SubjectCategoryDTO;
import com.pccw.ehunter.helper.CodeTableHelper;
import com.pccw.ehunter.mvc.BaseController;
import com.pccw.ehunter.service.ProjectRegistrationService;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.validator.PositionDescriptionValidator;
import com.pccw.ehunter.validator.PositionRequirementValidator;

@Controller
@SessionAttributes({
	SessionAttributeConstant.PROJECT_DTO,
	SessionAttributeConstant.POSITION_DESCRIPTION_DTO,
	SessionAttributeConstant.POSITION_REQUIREMENT_DTO
})
public class ProjectAmendController extends BaseController{
	
	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@Autowired
	private PositionDescriptionValidator postDescValidator;
	
	@Autowired
	private PositionRequirementValidator postRequireValidator;
	
	@Autowired
	private ProjectRegistrationService projectRegtService;

	@RequestMapping("/project/preEditPositionDescription.do")
	public ModelAndView preEditPositionDescription(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.PROJECT_DTO)ProjectDTO projectDto){
		ModelAndView mv = new ModelAndView("project/positionDescriptionAmend");
		
		initializePositionDescription(request,mv);
		mv.addObject(SessionAttributeConstant.POSITION_DESCRIPTION_DTO, projectDto.getPostDescDto());
		mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
		return mv;
	}
	
	private void initializePositionDescription(HttpServletRequest request,ModelAndView mv) {
		List<PositionCategoryDTO> categories = codeTableHelper.getPositionCategories(request);
		List<SalaryCategoryDTO> salaryCategories = codeTableHelper.getSalaryCategories(request);
		List<AnnualLeaveWelfareDTO> annualWelfares = codeTableHelper.getAnnualLeaveWelfares(request);
		List<ResidentialWelfareDTO> residentialWelfares = codeTableHelper.getResidentialWelfares(request);
		List<SocietyWelfareDTO> societyWelfares = codeTableHelper.getSocietyWelfares(request);
		List<ProvinceDTO> provinces = codeTableHelper.getProvinces(request);
		
		mv.addObject(WebConstant.LIST_OF_POSITION_CATEGORY, categories);
		mv.addObject(WebConstant.LIST_OF_SALARY_CATEGORY, salaryCategories);
		mv.addObject(WebConstant.LIST_OF_ANNUAL_LEAVE_WELFARE, annualWelfares);
		mv.addObject(WebConstant.LIST_OF_RESIDENTIAL_WELFARE, residentialWelfares);
		mv.addObject(WebConstant.LIST_OF_SOCIETY_WELFARE, societyWelfares);
		mv.addObject(WebConstant.LIST_OF_PROVINCE, provinces);
	}
	
	@RequestMapping("/project/preEditPositionRequirement.do")
	public ModelAndView preEditPositionRequirement(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.PROJECT_DTO)ProjectDTO projectDto){
		ModelAndView mv = new ModelAndView("project/positionRequirementAmend");
		
		initializePositionRequirement(request , mv);
		
		mv.addObject(SessionAttributeConstant.POSITION_REQUIREMENT_DTO, projectDto.getPostRequireDto());
		mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
		return mv;
	}
	
	private void initializePositionRequirement(HttpServletRequest request,ModelAndView mv) {
		List<SubjectCategoryDTO> majorCategories = codeTableHelper.getAllSubjectTypes(request);
		List<DegreeDTO> degreeCategories = codeTableHelper.getAllDegrees(request);
		List<IndustryCategoryDTO> industryCategories = codeTableHelper.getIndustryCategories(request);
		
		mv.addObject(WebConstant.LIST_OF_SUBJECT_TYPE, majorCategories);
		mv.addObject(WebConstant.LIST_OF_DEGREE, degreeCategories);
		mv.addObject(WebConstant.LIST_OF_INDUSTRY_CATEGORY, industryCategories);
	}
	
	@RequestMapping("/project/updatePositionDescription.do")
	public ModelAndView updatePositionDescription(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.PROJECT_DTO)ProjectDTO projectDto,
			@ModelAttribute(SessionAttributeConstant.POSITION_DESCRIPTION_DTO)PositionDescriptionDTO postDescDto,
			BindingResult errors){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/project/viewProjectDetail.do", true));
		
		postDescValidator.validate(projectDto.getPostDescDto(), errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("project/positionDescriptionAmend");
			initializePositionDescription(request,mv);
			mv.addObject(SessionAttributeConstant.POSITION_DESCRIPTION_DTO, projectDto.getPostDescDto());
			mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
		}
		
		projectDto.setPostDescDto(postDescDto);
		
		projectRegtService.updatePositionDescription(projectDto);
		
		transactionLogService.logTransaction(ModuleIndicator.PROJECT, getMessage("tx.log.project.update" , new String[]{projectDto.getSystemProjectRefNum()}));
		
		mv.addObject("_id", projectDto.getSystemProjectRefNum());
		return mv;
	}
	
	@RequestMapping("/project/updatePositionRequirement.do")
	public ModelAndView updatePositionDescription(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.PROJECT_DTO)ProjectDTO projectDto,
			@ModelAttribute(SessionAttributeConstant.POSITION_REQUIREMENT_DTO)PositionRequirementDTO postRequireDto,
			BindingResult errors){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/project/viewProjectDetail.do", true));
		
		postRequireValidator.validate(projectDto.getPostRequireDto(), errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("project/positionRequirementAmend");
			initializePositionRequirement(request , mv);
			mv.addObject(SessionAttributeConstant.POSITION_REQUIREMENT_DTO, projectDto.getPostRequireDto());
			mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
		}
		
		projectDto.setPostRequireDto(postRequireDto);
		
		projectRegtService.updatePositionRequirement(projectDto);
		
		transactionLogService.logTransaction(ModuleIndicator.PROJECT, getMessage("tx.log.project.update" , new String[]{projectDto.getSystemProjectRefNum()}));
		
		mv.addObject("_id", projectDto.getSystemProjectRefNum());
		return mv;
	}
}

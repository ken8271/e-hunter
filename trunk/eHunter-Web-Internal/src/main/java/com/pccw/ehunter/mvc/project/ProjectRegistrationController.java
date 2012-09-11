package com.pccw.ehunter.mvc.project;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.constant.WebConstant;
import com.pccw.ehunter.convertor.CustomerConvertor;
import com.pccw.ehunter.dto.AnnualLeaveWelfareDTO;
import com.pccw.ehunter.dto.CityDTO;
import com.pccw.ehunter.dto.CustomerDTO;
import com.pccw.ehunter.dto.CustomerEnquireDTO;
import com.pccw.ehunter.dto.DegreeDTO;
import com.pccw.ehunter.dto.IndustryCategoryDTO;
import com.pccw.ehunter.dto.IndustryDTO;
import com.pccw.ehunter.dto.InternalUserDTO;
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
import com.pccw.ehunter.service.CustomerCommonService;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.SecurityUtils;
import com.pccw.ehunter.utility.StringUtils;
import com.pccw.ehunter.validator.PositionDescriptionValidator;
import com.pccw.ehunter.validator.PositionRequirementValidator;
import com.pccw.ehunter.validator.ProjectValidator;

@Controller
@SessionAttributes({
	SessionAttributeConstant.PROJECT_DTO,
	SessionAttributeConstant.POSITION_DESCRIPTION_DTO,
	SessionAttributeConstant.POSITION_REQUIREMENT_DTO
})
public class ProjectRegistrationController extends BaseController{
	
	@Autowired
	private CustomerCommonService custCommonService;
	
	@Autowired
	private ProjectValidator projectValidator;
	
	@Autowired
	private CodeTableHelper codeTableHelper;
	
	@Autowired
	private PositionDescriptionValidator postDescValidator;
	
	@Autowired
	private PositionRequirementValidator postRequireValidator;
	
	@RequestMapping("/project/initNewProject.do")
	public ModelAndView initNewProject(HttpServletRequest request){
		return new ModelAndView(new RedirectViewExt("/project/fillProjectInfo.do", true));
	}
	
	@RequestMapping("/project/fillProjectInfo.do")
	public ModelAndView fillProjectInfo(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("project/projectCreate");
		
		initializeProject(mv);
		
		return mv;
	}

	private void initializeProject(ModelAndView mv) {
		InternalUserDTO internalUserDto = (InternalUserDTO)SecurityUtils.getUser();
		
		if(internalUserDto == null){
			internalUserDto = new InternalUserDTO();
		}
		
		ProjectDTO projectDto = new ProjectDTO();
		projectDto.setAdviserDto(internalUserDto);
		projectDto.setCustomerDto(new CustomerDTO());
		
		mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
	}
	
	@RequestMapping("/project/saveProjectInfo.do")
	public ModelAndView saveProjectInfo(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.PROJECT_DTO)ProjectDTO projectDto,BindingResult errors){
		ModelAndView mv = null;
		
		projectValidator.validate(projectDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("project/projectCreate");
			mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
			return mv;
		}
		
		CustomerDTO custDto = custCommonService.getCustomerByID(projectDto.getSystemCustRefNum());
		
		if(custDto != null){
			projectDto.setCustomerDto(custDto);
		}
		
		return new ModelAndView(new RedirectViewExt("/project/fillPositionDescription.do", true));
	}
	
	@RequestMapping("/project/fillPositionDescription.do")
	public ModelAndView fillPositionDescription(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.PROJECT_DTO)ProjectDTO projectDto){
		ModelAndView mv = new ModelAndView("project/positionDescription");
		
		PositionDescriptionDTO postDescDto = new PositionDescriptionDTO();
		
		initializePositionDescription(request , mv );
		
		mv.addObject(SessionAttributeConstant.POSITION_DESCRIPTION_DTO, postDescDto);
		mv.addObject("customerName", projectDto.getCustomerDto().getFullName());
		
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
	
	@RequestMapping("/project/savePositionDescription.do")
	public ModelAndView savePositionDescription(HttpServletRequest request ,
			@ModelAttribute(SessionAttributeConstant.PROJECT_DTO)ProjectDTO projectDto,
			@ModelAttribute(SessionAttributeConstant.POSITION_DESCRIPTION_DTO)PositionDescriptionDTO postDescDto , BindingResult errors){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/project/fillPositionRequirement.do", true));
		
		List<CityDTO> cityDtos = new ArrayList<CityDTO>();
		if(!StringUtils.isEmpty(postDescDto.getCities())){
			String[] cities = StringUtils.tokenize(postDescDto.getCities(), CommonConstant.COMMA);
			if(cities != null && cities.length != 0){
				for(String cityCode : cities){
					cityDtos.add(codeTableHelper.getCityByCode(cityCode));
				}
			}
		}
		postDescDto.setCityDtos(cityDtos);
		
		postDescValidator.validate(postDescDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("project/positionDescription");
			mv.addObject("salaryCategory", StringUtils.concat(postDescDto.getSalaryCategory(), ","));
			mv.addObject("societyWelfare", StringUtils.concat(postDescDto.getSocietyWelfare(), ","));
			mv.addObject("residentialWelfare", StringUtils.concat(postDescDto.getResidentialWelfare(), ","));
			mv.addObject("annualLeaveWelfare", StringUtils.concat(postDescDto.getAnnualLeaveWelfare(), ","));
			mv.addObject("customerName", projectDto.getCustomerDto().getFullName());
			mv.addObject(SessionAttributeConstant.POSITION_DESCRIPTION_DTO, postDescDto);
		}
		
		List<SalaryCategoryDTO> scDtos = postDescDto.getSalaryCategoryDtos();
		if(!StringUtils.isEmpty(postDescDto.getSalaryCategory())){
			for(String sc : postDescDto.getSalaryCategory()){
				scDtos.add(codeTableHelper.getSalaryCategoryByCode(request, sc));
			}
		}
		
		List<SocietyWelfareDTO> swDtos = postDescDto.getScoitetyWelfareDtos();
		if(!StringUtils.isEmpty(postDescDto.getSocietyWelfare())){
			for(String sw : postDescDto.getSocietyWelfare()){
				swDtos.add(codeTableHelper.getSocietyWelfareByCode(request, sw));
			}
		}
		
		List<ResidentialWelfareDTO> rwDtos = postDescDto.getResidentialWelfareDtos();
		if(!StringUtils.isEmpty(postDescDto.getResidentialWelfare())){
			for(String rw : postDescDto.getResidentialWelfare()){
				rwDtos.add(codeTableHelper.getResidentialWelfareByCode(request, rw));
			}
		}
		
		List<AnnualLeaveWelfareDTO> awDtos = postDescDto.getAnnualLeaveWelfareDtos();
		if(!StringUtils.isEmpty(postDescDto.getAnnualLeaveWelfare())){
			for(String aw : postDescDto.getAnnualLeaveWelfare()){
				awDtos.add(codeTableHelper.getAnnualLeaveWelfareByCode(request, aw));
			}
		}
		
		projectDto.setPostDescDto(postDescDto);
		
		return mv;
	}
	
	@RequestMapping("/project/backToProjectInfo.do")
	public ModelAndView backToProjectInfo(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.PROJECT_DTO)ProjectDTO projectDto){
		ModelAndView mv = new ModelAndView("project/projectCreate");
		
		mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
		
		return mv;
	}
	
	@RequestMapping("/project/fillPositionRequirement.do")
	public ModelAndView fillPositionRequirement(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("project/positionRequirement");
		
		initializePositionRequirement(request , mv);
		mv.addObject(SessionAttributeConstant.POSITION_REQUIREMENT_DTO, new PositionRequirementDTO());
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
	
	@RequestMapping("/project/savePositionRequirement.do")
	public ModelAndView savePositionRequirement(HttpServletRequest request , 
			@ModelAttribute(SessionAttributeConstant.PROJECT_DTO)ProjectDTO projectDto,
			@ModelAttribute(SessionAttributeConstant.POSITION_REQUIREMENT_DTO)PositionRequirementDTO postRequireDto , 
			BindingResult errors){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/project/verify.do", true));
		
		List<IndustryDTO> industryDtos = new ArrayList<IndustryDTO>();
		if(!StringUtils.isEmpty(postRequireDto.getExpectIndustries())){
			String[] industries = StringUtils.tokenize(postRequireDto.getExpectIndustries(), CommonConstant.COMMA);
			if(industries != null && industries.length != 0){
				for(String industryCode : industries){
					industryDtos.add(codeTableHelper.getIndustryByCode(industryCode));
				}
			}
		}
		
		postRequireDto.setExpectIndustryDtos(industryDtos);
		
		postRequireValidator.validate(postRequireDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("project/positionRequirement");
			mv.addObject("languages", StringUtils.concat(postRequireDto.getLanguage(), ","));
			initializePositionRequirement(request , mv);
			mv.addObject(SessionAttributeConstant.POSITION_REQUIREMENT_DTO, postRequireDto);
		}
		
		postRequireDto.setMajorCategoryDto(codeTableHelper.getSubjectCatgoryByCode(request, postRequireDto.getMajorCategory()));
		postRequireDto.setDegreeDto(codeTableHelper.getDegreeByCode(request, postRequireDto.getDegree()));
		
		projectDto.setPostRequireDto(postRequireDto);
		
		return mv;
	}
	
	@RequestMapping("/project/verify.do")
	public ModelAndView verify(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.PROJECT_DTO)ProjectDTO projectDto){
		ModelAndView mv = new ModelAndView("project/verifyInfo");
		
		mv.addObject(SessionAttributeConstant.PROJECT_DTO, projectDto);
		return mv;
	}
	
	@RequestMapping("/project/backToPositionDescription.do")
	public ModelAndView backToPositionDescription(HttpServletRequest request ,
			@ModelAttribute(SessionAttributeConstant.PROJECT_DTO)ProjectDTO projectDto,
			@ModelAttribute(SessionAttributeConstant.POSITION_DESCRIPTION_DTO)PositionDescriptionDTO postDescDto){
		ModelAndView mv = new ModelAndView("project/positionDescription");
		
		initializePositionDescription(request , mv);
		
		mv.addObject("salaryCategory", StringUtils.concat(postDescDto.getSalaryCategory(), ","));
		mv.addObject("societyWelfare", StringUtils.concat(postDescDto.getSocietyWelfare(), ","));
		mv.addObject("residentialWelfare", StringUtils.concat(postDescDto.getResidentialWelfare(), ","));
		mv.addObject("annualLeaveWelfare", StringUtils.concat(postDescDto.getAnnualLeaveWelfare(), ","));
		mv.addObject("customerName", projectDto.getCustomerDto().getFullName());
		mv.addObject(SessionAttributeConstant.POSITION_DESCRIPTION_DTO, postDescDto);
		
		return mv;
	}

	@RequestMapping("/project/loadCustomers.do")
	public ModelAndView loadCustomers(HttpServletRequest request , HttpServletResponse response){
		PrintWriter out = null;
		XMLWriter writer = null;
		try {
			response.setContentType("text/xml;charset=UTF-8");
			out = response.getWriter();
			
			String id = request.getParameter("_id");
			String name = request.getParameter("_name");
			
			CustomerEnquireDTO enquireDto = new CustomerEnquireDTO();
			enquireDto.setSystemCustRefNum(id);
			enquireDto.setName(name);
			
			List<CustomerDTO> dtos = custCommonService.getCustomersByCriteria(CustomerConvertor.toPagedCriteria(enquireDto));

			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("xml");
			
			if(!CollectionUtils.isEmpty(dtos)){
				for(CustomerDTO dto : dtos){
					Element cust = root.addElement("customer");
					cust.addElement("id").setText(dto.getSystemCustRefNum());
					cust.addElement("name").setText(dto.getFullName() + "(" + dto.getShortName() + ")");
					cust.addElement("grade").setText(dto.getGrade());
					cust.addElement("status").setText(dto.getStatus());
				}
			}
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			writer = new XMLWriter(out, format);
			writer.write(doc);
			
			if(null != writer){
				writer.close();
			}
		} catch (Exception e) {
			logger.error(">>>>> Exception Catched (loadCustomers) : " + e.getMessage());
		} finally {
			if(null != out){
				out.close();
			}
		}
		return null;
	}
	
	@RequestMapping("/project/loadCities.do")
	public ModelAndView loadCities(HttpServletRequest request , HttpServletResponse response){
		PrintWriter out = null;
		XMLWriter writer = null;
		try {
			response.setContentType("text/xml;charset=UTF-8");
			out = response.getWriter();
			
			String code = request.getParameter("_id");
			
			List<CityDTO> cities = codeTableHelper.getCitiesByProvinceCode(code);
			
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("xml");
			
			if(!CollectionUtils.isEmpty(cities)){
				for(CityDTO cityDto : cities){
					Element city = root.addElement("city");
					city.addElement("label").setText(cityDto.getDisplayName());
					city.addElement("value").setText(cityDto.getCityCode());
				}
			}
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			writer = new XMLWriter(out, format);
			writer.write(doc);
			
			if(null != writer){
				writer.close();
			}
		} catch (Exception e) {
			logger.error(">>>>> Exception Catched (loadCities) : " + e.getMessage());
		} finally {
			if(null != out){
				out.close();
			}
		}
		return null;
	}
}
